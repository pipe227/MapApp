package com.felipe.mapapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.presentation.viewmodel.CityViewModel

@Composable
fun CityListScreen(
    viewModel: CityViewModel = hiltViewModel(),
    onCitySelected: (City) -> Unit

) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCities by viewModel.filteredCities.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredCities) { city ->
                val isFavorite = favorites.contains(city.id)
                CityItem(
                    city = city,
                    isFavorite = viewModel.favorites.collectAsState().value.contains(city.id),
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    onClick = { onCitySelected(city) }

                )
            }
        }
    }
}