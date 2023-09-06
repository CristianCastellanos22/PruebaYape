package com.cristian.pruebayape.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.pruebayape.data.response.ApiResponseStatus
import com.cristian.pruebayape.domain.RecipesUseCase
import com.cristian.pruebayape.domain.models.RecipesUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val recipesUseCase: RecipesUseCase) :
    ViewModel() {

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>> get() = _status

    private val _recipesList = MutableLiveData<List<RecipesUI>>()
    val recipesList: LiveData<List<RecipesUI>> get() = _recipesList



    init {
        getRecipesCollection()
    }

    private fun getRecipesCollection() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            val response = recipesUseCase.invoke()
            handleResponseStatus(response)
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<RecipesUI>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _recipesList.value = apiResponseStatus.data
        }
        _status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

}