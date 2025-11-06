package com.example.recipeapp.screens


import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.recipeapp.models.Category
import com.example.recipeapp.viewmodels.CatergoryMealViewModel
import com.example.recipeapp.viewmodels.MainViewModel

@Composable
fun RecipeScreen(navController: NavController,modifier: Modifier = Modifier)
{
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState



    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null ->{
                Text("Something went wrong.\nPlease try again later.")
            }
            else ->{
                CategoryScreen(categories = viewState.list ,navController = navController)
            }
        }
    }

}


@Composable
fun CategoryScreen(categories: List<Category>,navController: NavController){
    val categoryMealViewModel: CatergoryMealViewModel =viewModel()

    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items (categories){
                category ->
            CategoryItem(category = category,onClick = {
                categoryMealViewModel.fetchCategoryMealsState(category.strCategory)
                navController.navigate("categoryMeal/${category.strCategory}")
            })
        }
    }
}
@Composable
fun CategoryItem(category: Category,onClick:()-> Unit){
    Column(modifier = Modifier.padding(8.dp).fillMaxWidth().clickable{onClick ()},
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = category.strCategory,
            modifier = Modifier.fillMaxWidth().height(150.dp)
                .padding(8.dp)
        )

        Text(text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp))
    }
}

