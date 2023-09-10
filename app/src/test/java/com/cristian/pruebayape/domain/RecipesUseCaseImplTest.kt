package com.cristian.pruebayape.domain

import com.cristian.pruebayape.data.RecipesRepository
import com.cristian.pruebayape.domain.models.OriginUI
import com.cristian.pruebayape.domain.models.RecipesUI
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RecipesUseCaseImplTest {

    private lateinit var useCase: RecipesUseCaseImpl

    @MockK
    private lateinit var repository: RecipesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = RecipesUseCaseImpl(repository)
    }

    @Test
    fun `test invoke success`() = runTest {
        //given
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

        //when
        coEvery { repository.getRecipes() } returns Result.success(fakeRecipes)

        val result = useCase.invoke()

        //then
        result.onSuccess {
            assertEquals(it, fakeRecipes)
        }

    }

    @Test
    fun `test invoke success with emptyList`() = runTest {
        //given
        val fakeRecipes = emptyList<RecipesUI>()

        //when
        coEvery { repository.getRecipes() } returns Result.success(fakeRecipes)

        val result = useCase.invoke()

        //then
        result.onSuccess {
            assertEquals(it, fakeRecipes)
        }
    }


    @Test
    fun `test invoke failure`() = runTest {
        //given
        val errorMessage = "error en la consulta"
        val error = mockk<Error> {
            every { message } returns errorMessage
        }

        //when
        coEvery { repository.getRecipes() } returns Result.failure(error)

        val result = useCase.invoke()

        //then
        result.onFailure {
            assertEquals(it.message, errorMessage)
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

}
