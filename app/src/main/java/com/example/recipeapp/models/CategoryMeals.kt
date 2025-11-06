package com.example.recipeapp.models

data class CategoryMeals(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String
)

data class CategoryMealsResponse(val meals: List<CategoryMeals>)