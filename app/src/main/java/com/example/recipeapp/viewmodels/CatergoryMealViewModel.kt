package com.example.recipeapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.models.CategoryMeals
import com.example.recipeapp.network.recipeService
import kotlinx.coroutines.launch

class CatergoryMealViewModel : ViewModel() {

  val _categoryMealsState = mutableStateOf(CategoryMealsState())
    val categoryMealsState: State<CategoryMealsState> = _categoryMealsState


    fun fetchCategoryMealsState(categoryName:String){
        viewModelScope.launch {

            try {
            val response = recipeService.getCategorymeals(categoryName)
            _categoryMealsState.value = _categoryMealsState.value.copy(list = response.meals,
                loading = false,
                error = null)
            }
            catch (e: Exception){
            _categoryMealsState.value = _categoryMealsState.value.copy(loading = false,
                error = "Error: ${e.message}")
            }
        }
    }
}

data class CategoryMealsState(
    val loading: Boolean = true,
    val list: List<CategoryMeals> = emptyList(),
    val error:String? = null
)