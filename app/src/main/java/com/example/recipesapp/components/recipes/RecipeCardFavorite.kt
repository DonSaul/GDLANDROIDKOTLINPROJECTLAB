package com.example.recipesapp.components.recipes


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.recipesapp.model.Recipe
import com.example.recipesapp.model.Result
import com.example.recipesapp.ui.theme.BorderRecipeCard
import com.example.recipesapp.ui.theme.RecipeCard


@Composable
fun RecipeCardFavorite(
    recipe: Recipe,
    action: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(160.dp).clickable { action() },
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .background(RecipeCard)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .crossfade(true)
                        .build()
                ),
                contentDescription = "Recipe Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 60.dp)
                    .border(
                        2.dp,
                        BorderRecipeCard,
                        shape = RoundedCornerShape(8.dp)
                    ), // Minimum height for the text container
                contentAlignment = Alignment.CenterStart // Centers the text vertically and aligns to start horizontally
            ) {
                Text(
                    text = recipe.title ?: "Unknown Recipe",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}