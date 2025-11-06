package com.example.recipeapp.network

import com.example.recipeapp.models.CategoryMealsResponse
import com.example.recipeapp.models.CategoryResponse
import com.example.recipeapp.models.MealDetailResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    //for CategoryMeals
    @GET("filter.php")
    suspend fun getCategorymeals(@Query("c") categoryName: String): CategoryMealsResponse
//    for future
    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") mealId: String): MealDetailResponse

}