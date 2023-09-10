package com.cristian.pruebayape.data

import com.cristian.pruebayape.data.network.RecipesClient
import com.cristian.pruebayape.data.response.RecipesResponse
import com.cristian.pruebayape.data.response.toDomain
import com.cristian.pruebayape.domain.models.RecipesUI
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipesRepositoryImplTest {

    private lateinit var repository: RecipesRepositoryImpl

    @MockK
    private lateinit var recipesServices: RecipesClient

    private val dispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = RecipesRepositoryImpl(recipesServices, dispatcher)
    }

    @Test
    fun `test getRecipes 200 success`() = runTest {
        //given
        val fakeResponse = listOf(
            RecipesResponse(
                id = "1",
                name = "",
                category = "",
                area = "",
                instructions = "",
                thumbnail = "",
                tags = "",
                ingredients = arrayListOf(),
                lat = 12.098,
                lng = 20.02
            )
        )

        //when
        coEvery { recipesServices.doGetRecipes() } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns fakeResponse
        }
        val result = repository.getRecipes()

        //then
        result.onSuccess { recipes ->
            assertEquals(recipes, fakeResponse.map { it.toDomain() })
        }

    }

    @Test
    fun `test getRecipes 204 emptyList`() = runTest {
        //when
        coEvery { recipesServices.doGetRecipes() } returns mockk {
            every { isSuccessful } returns true
            every { code() } returns 204
            every { body() } returns listOf()
        }
        val result = repository.getRecipes()

        //then
        result.onSuccess { recipes ->
            assertEquals(recipes, emptyList<RecipesUI>())
        }
    }

    @Test
    fun `test getRecipes 401 error`() = runTest {
        //given
        val errorMessage =
            "Error en consulta Sugerencias: - Verifique su conexión a internet. - Intente más tarde."
        val errorBody = mockk<ResponseBody>()

        //when
        every { errorBody.toString() } returns errorMessage

        coEvery { recipesServices.doGetRecipes() } returns mockk {
            every { isSuccessful } returns false
            every { code() } returns 401
            every { body() } returns null
            every { errorBody() } returns errorBody
        }

        val result = repository.getRecipes()

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
