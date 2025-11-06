package com.example.recipeapp

import android.app.FragmentManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeapp.screens.CategoryMealScreen
import com.example.recipeapp.screens.MealInfoScreen
import com.example.recipeapp.screens.MealScreen
import com.example.recipeapp.screens.RecipeScreen
import com.example.recipeapp.ui.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RecipeAppTheme {
                RecipeAppNavHost()
            }
        }
    }
}

@Composable
fun RecipeAppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "recipe"
    ) {
        composable("recipe") {
            RecipeScreen(navController = navController)
        }
        composable(
            route = "categoryMeal/{categoryName}",
            arguments = listOf(navArgument("categoryName") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            CategoryMealScreen(categoryName = categoryName, navController = navController)
        }
        composable(route = "meal/{idMeal}",
            arguments = listOf(navArgument("idMeal"){
                type = NavType.StringType
            })) {
            backStackEntry ->
            val idMeal = backStackEntry.arguments?.getString("idMeal")?:""
            MealInfoScreen(navController = navController,idMeal = idMeal)
        }
    }
}