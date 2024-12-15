package com.felipe.mapapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.presentation.components.SearchBar
import com.felipe.mapapp.presentation.viewmodel.CityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(
    viewModel: CityViewModel = hiltViewModel(),
    onCitySelected: (City) -> Unit

) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCities by viewModel.filteredCities.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val showFavoritesOnly by viewModel.showFavoritesOnly.collectAsState()


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChanged = { viewModel.updateSearchQuery(it) }
            )
            Spacer(modifier = Modifier.width(8.dp))

        }
        Row( modifier = Modifier.padding(start = 8.dp),verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Show Favorites",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
            Switch(
                checked = showFavoritesOnly,
                onCheckedChange = { viewModel.toggleShowFavoritesOnly() }
            )
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredCities) { city ->
                val isFavorite = favorites.contains(city.id)
                CityItem(
                    city = city,
                    isFavorite = isFavorite,
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    onClick = { onCitySelected(city) }

                )
            }
        }
    }
}