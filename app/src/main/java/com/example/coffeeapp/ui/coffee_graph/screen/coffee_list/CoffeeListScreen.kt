package com.example.coffeeapp.ui.coffee_graph.screen.coffee_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coffeeapp.R
import com.example.coffeeapp.domain.models.CoffeeDomainModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeListScreen(onCoffeeClicked : (CoffeeDomainModel) -> Unit,
    viewModel: CoffeeListViewModel = hiltViewModel()) {


    val coffeeList by viewModel.coffeeListFlow.collectAsState(initial = emptyList())
    val isLoadingList by viewModel.isLoadingList

    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            viewModel.loadCoffeeList()
        }
    }

    LaunchedEffect(isLoadingList) {
        if(! isLoadingList)
            state.endRefresh()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .nestedScroll(state.nestedScrollConnection)
    ) {

        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            PageHeader()

            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp))

            // Since if coffeeList is not empty, we will show loading of swipe-to-refresh
            if (isLoadingList && coffeeList.isEmpty()) {
                Box(modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center))
                }
            }
            else {

                if(coffeeList.isEmpty()) {
                    EmptyView(reloadList = {
                        viewModel.loadCoffeeList()
                    })
                }
                else {
                    CoffeeList(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(top = 12.dp),
                        list = coffeeList,
                        onCoffeeClicked = onCoffeeClicked
                    )
                }
            }

        }

        PullToRefreshContainer(
            modifier = Modifier
                .align(Alignment.TopCenter)
//               .graphicsLayer(scaleX = scaleFraction, scaleY = scaleFraction)
            ,
            state = state
        )

    }

}

@Composable
private fun EmptyView(reloadList : () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = stringResource(id = R.string.empty_coffee_list),
                color = Color.LightGray,
                style = MaterialTheme.typography.headlineSmall)

            Button(modifier = Modifier.padding(top = 8.dp),
                onClick = {
                reloadList()
            }) {
                Text(text = stringResource(id = R.string.refresh))
            }

        }

    }
}

@Composable
private fun PageHeader() {

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.coffee_menu),
            contentDescription = null)

        Text(modifier = Modifier.padding(start = 10.dp),
            text = stringResource(R.string.coffee_list),
            style = MaterialTheme.typography.headlineMedium)
    }

}