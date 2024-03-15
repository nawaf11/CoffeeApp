package com.example.coffeeapp.ui.coffee_graph.screen.coffee_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.coffeeapp.R
import com.example.coffeeapp.domain.models.CoffeeDomainModel
import com.example.coffeeapp.ui.component.ShimmerLoadingBox
import com.example.coffeeapp.utils.isRtl

@Composable
fun CoffeeDetailsScreen(viewModel: CoffeeDetailsViewModel = hiltViewModel(),
                        onBackPressed : () -> Unit) {

    val mCoffeeDomainModel : CoffeeDomainModel? by viewModel.coffeeDomainModelState

    val coffeeDomainModel = mCoffeeDomainModel

    if (coffeeDomainModel == null) { // Loading
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else {
        CoffeeScreenContent(coffeeDomainModel, onBackPressed = onBackPressed)
    }

}

@Composable
private fun CoffeeScreenContent(coffeeDomainModel: CoffeeDomainModel,
                                onBackPressed : () -> Unit) {
    var isImageLoading by remember {
        mutableStateOf<Boolean>(true)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(Modifier.fillMaxSize()) {

            Box(modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()) {
                if(! coffeeDomainModel.imageLink.isNullOrEmpty()) {

                    Box() {
                        AsyncImage(modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .alpha(if (isImageLoading) 0f else 1f),
                            model = coffeeDomainModel.imageLink,
                            placeholder = painterResource(R.drawable.placeholder_photo),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                            onLoading = {
                                isImageLoading = true
                            },
                            onError = {
                                isImageLoading = false
                            },
                            onSuccess = {
                                isImageLoading = false
                            }
                        )

                        Image(modifier = Modifier
                            .padding(top = 10.dp, start = 10.dp)
                            .rotate(if (isRtl()) 180f else 0f)
                            .size(30.dp)
                            .clickable {
                                onBackPressed()
                            },
                            imageVector = Icons.Rounded.ArrowBack,
                            colorFilter = ColorFilter.tint(Color.White),
                            contentDescription = null)
                    }

                    if (isImageLoading)
                        ShimmerLoadingBox(modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp))
                }

            }

            Text(modifier = Modifier.padding(start = 10.dp, top = 16.dp),
                text = coffeeDomainModel.title,
                style = MaterialTheme.typography.headlineMedium)

            Text(modifier = Modifier.padding(start = 14.dp, top = 12.dp),
                text = coffeeDomainModel.description,
                style = MaterialTheme.typography.bodyLarge)


            Column() {

                Text(modifier = Modifier.padding(start = 8.dp, top = 12.dp),
                    text = stringResource(id = R.string.ingredient),
                    style = MaterialTheme.typography.headlineSmall)

                coffeeDomainModel.ingredients.forEach { ingredientText ->
                    Text(modifier = Modifier.padding(start = 14.dp, top = 12.dp),
                        text = "- $ingredientText",
                        style = MaterialTheme.typography.bodyMedium)
                }
            }

        }

    }
}