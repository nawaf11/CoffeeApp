package com.example.coffeeapp.ui.coffee_graph.screen.coffee_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coffeeapp.R
import com.example.coffeeapp.domain.models.CoffeeDomainModel
import com.example.coffeeapp.ui.component.ShimmerLoadingBox
import com.example.coffeeapp.ui.theme.AppColor


@Composable
fun CoffeeListItem(coffeeDomainModel: CoffeeDomainModel, onClick : () -> Unit) {

    var isImageLoading by remember {
        mutableStateOf<Boolean>(true)
    }

    Box(Modifier.padding(top = 8.dp, bottom = 8.dp, end = 8.dp)
        .fillMaxWidth()
        .background(AppColor.ItemBackground,
            shape = RoundedCornerShape(10.dp))
        .border(width = 0.5.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
        .clickable {
            onClick()
        }
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(modifier = Modifier.size(100.dp)) {
                if(! coffeeDomainModel.imageLink.isNullOrEmpty()) {

                    AsyncImage(modifier = Modifier.size(100.dp)
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

                    if (isImageLoading)
                        ShimmerLoadingBox(modifier = Modifier
                            .width(100.dp)
                            .height(100.dp))

                }

            }

            Text(modifier = Modifier.padding(horizontal = 8.dp),
                text = coffeeDomainModel.title,
                style = MaterialTheme.typography.headlineSmall)

        }

    }

}