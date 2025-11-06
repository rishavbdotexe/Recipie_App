    package com.example.recipeapp.screens

    import android.R.attr.fontWeight
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import coil.compose.rememberAsyncImagePainter
    import com.example.recipeapp.models.CategoryMeals
    import com.example.recipeapp.viewmodels.CatergoryMealViewModel
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.navigation.NavController


    @Composable
    fun CategoryMealScreen(modifier: Modifier = Modifier,navController: NavController,categoryName: String) {
        val mealsViewModel: CatergoryMealViewModel = viewModel()
        val viewState by mealsViewModel.categoryMealsState
        // 👇 Add this line — triggers data load when screen opens
        androidx.compose.runtime.LaunchedEffect(categoryName) {
            mealsViewModel.fetchCategoryMealsState(categoryName)
        }

        Box(modifier = Modifier.fillMaxSize())
        {
            when{
                viewState.loading ->{
                    CircularProgressIndicator(modifier.align(Alignment.Center))
                }
                viewState.error!=null ->{
                    Text("Error Connecting to Internet")
                }
                else -> {
                    MealScreen(meals = viewState.list , navController = navController)
                }
            }
        }

    }

    @Composable
    fun MealScreen(meals: List<CategoryMeals>,navController: NavController){
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(meals){
                meal ->
                MealItem(meal = meal){idMeal ->
                    navController.navigate("meal/$idMeal")

                }
            }
        }
    }

    @Composable
    fun MealItem(meal: CategoryMeals,onClick:(String) -> Unit)
    {
        Row(modifier = Modifier.fillMaxWidth().padding(5.dp).clickable { onClick(meal.idMeal) },
            verticalAlignment = Alignment.CenterVertically) {

            Image(painter = rememberAsyncImagePainter(meal.strMealThumb),
                contentDescription = meal.strMeal,
                modifier = Modifier.height(80.dp).width(80.dp))

            Text(text = meal.strMeal,
                color = Color.Black,
                 style = TextStyle(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(start =  7.dp))
        }
    }

    //@Preview (showBackground = true)
    //@Composable
    //fun mealItemP(){
    //    val sampleMeal = CategoryMeals(
    //        idMeal = "52772",
    //        strMeal = "Chicken Curry",
    //        strMealThumb = "https://www.themealdb.com/images/media/meals/1520084413.jpg"
    //    )
    //    MealItem(meal = sampleMeal)
    //}