package com.example.recipeapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImagePainter
import com.example.recipeapp.models.Meal
import com.example.recipeapp.models.MealDetailResponse
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.network.recipeService
import kotlinx.coroutines.launch


class MealDetailViewModel: ViewModel()
{
    private val _mealDetailState = mutableStateOf(MealDetailState())
    val mealDetailState: State<MealDetailState> = _mealDetailState

    fun fetchMealDetailState(idMeal: String) {
        viewModelScope.launch {
            try {
            val response = recipeService.getMealDetail(idMeal)
                val meal = response.meals.firstOrNull()
                _mealDetailState.value = _mealDetailState.value.copy(meal = meal, loading = false, error = null)

            }
            catch (e: Exception) {
                _mealDetailState.value = _mealDetailState.value.copy(loading = false,
                    error ="Error:${e.message}")

            }

        }
    }

}


data class MealDetailState(
    val loading: Boolean = true,
    val meal:Meal? = null,
    val error:String? = null
)