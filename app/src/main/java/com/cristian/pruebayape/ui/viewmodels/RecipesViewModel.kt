package com.cristian.pruebayape.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.pruebayape.domain.RecipesUseCase
import com.cristian.pruebayape.domain.models.RecipesUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ERROR_MESSAGE = "Error getting the recipes"

@HiltViewModel
class RecipesViewModel @Inject constructor(private val recipesUseCase: RecipesUseCase) :
    ViewModel() {

    private val _status = MutableLiveData<Status<Any>>()
    val status: LiveData<Status<Any>>
        get() = _status

    private val cacheRecipesList = mutableListOf<RecipesUI>()

    fun getRecipesCollection() {
        _status.value = Status.Loading()
        viewModelScope.launch {
            recipesUseCase.invoke()
                .onSuccess { recipes ->
                    _status.value = Status.Success(recipes)
                    cacheRecipesList.clear()
                    cacheRecipesList.addAll(recipes)
                }
                .onFailure {
                    _status.value = Status.Error(it.message ?: ERROR_MESSAGE)
                }
        }
    }

    fun filterRecipes(query: String?) {
        val filteredList = if (query.isNullOrBlank()) {
            cacheRecipesList
        } else {
            cacheRecipesList.filter { recipe ->
                recipe.name.contains(query, ignoreCase = true) ||
                        recipe.ingredients.any { ingredient ->
                            ingredient.ingredient?.contains(
                                query,
                                ignoreCase = true
                            ) == true
                        }
            }
        }
        _status.value = Status.UpdateData(filteredList)
    }

}
