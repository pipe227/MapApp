package com.felipe.mapapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.presentation.viewmodel.CityViewModel

@Composable
fun ResponsiveLayout(
    navController: NavController,
    viewModel: CityViewModel = hiltViewModel(),
    onCitySelected: (City) -> Unit
) {
    var selectedCity by remember { mutableStateOf<City?>(null) }

    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            CityListScreen(
                viewModel = viewModel,
                onCitySelected = { city ->
                    onCitySelected(city)
                }
            )
        } else {
            Row {
                Box(modifier = Modifier.weight(1f)) {
                    CityListScreen(
                        viewModel = viewModel,
                        onCitySelected = { city ->
                            selectedCity = city
                        }
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    CityDetailScreen(city = selectedCity, navController = navController)
                }
            }
        }
    }
}







