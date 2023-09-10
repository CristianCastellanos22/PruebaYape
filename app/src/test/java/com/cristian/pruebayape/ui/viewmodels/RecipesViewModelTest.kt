package com.cristian.pruebayape.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cristian.pruebayape.domain.RecipesUseCase
import com.cristian.pruebayape.domain.models.OriginUI
import com.cristian.pruebayape.domain.models.RecipesUI
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var recipesUseCase: RecipesUseCase

    @RelaxedMockK
    private lateinit var statusObserver: Observer<Status>

    private lateinit var viewModel: RecipesViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = RecipesViewModel(recipesUseCase)
        viewModel.status.observeForever(statusObserver)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test getRecipesCollection success`() = runTest {
        // Given
        val fakeRecipes = listOf(
            RecipesUI(
                id = "1",
                name = "",
                category = "",
                area = "",
                instructions = "",
                thumbnail = "",
                tags = "",
                ingredients = arrayListOf(),
                originUI = OriginUI(12.098, 20.02)
            )
        )

        // When
        coEvery { recipesUseCase.invoke() } returns Result.success(fakeRecipes)
        viewModel.getRecipesCollection()

        // Then
        verify {
            statusObserver.onChanged(Status.Loading)
            statusObserver.onChanged(Status.Success(fakeRecipes))
        }

    }

    @Test
    fun `test invoke success with emptyList`() = runTest {
        // Given
        val fakeRecipes = emptyList<RecipesUI>()

        // When
        coEvery { recipesUseCase.invoke() } returns Result.success(fakeRecipes)
        viewModel.getRecipesCollection()

        // Then
        verify {
            statusObserver.onChanged(Status.Loading)
            statusObserver.onChanged(Status.Success(fakeRecipes))
        }

    }

    @Test
    fun `test invoke failure`() = runTest {
        // Given
        val errorMessage = "error en la consulta"
        val error = mockk<Error> {
            every { message } returns errorMessage
        }

        // When
        coEvery { recipesUseCase.invoke() } returns Result.failure(error)
        viewModel.getRecipesCollection()

        // Then
        verify {
            statusObserver.onChanged(Status.Loading)
            statusObserver.onChanged(Status.Error(errorMessage))
        }

    }

    @After
    fun tearDown() {
        viewModel.status.removeObserver(statusObserver)
        unmockkAll()
    }

}
