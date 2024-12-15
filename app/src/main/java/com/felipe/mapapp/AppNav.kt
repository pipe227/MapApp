package com.felipe.mapapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.presentation.CityDetailScreen
import com.felipe.mapapp.presentation.viewmodel.CityViewModel
import com.felipe.mapapp.presentation.ResponsiveLayout

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: CityViewModel,
    onCitySelected: (City) -> Unit,
    selectedCity: City?
) {
    NavHost(
        navController = navController,
        startDestination = "city_list"
    ) {
        composable("city_list") {
            ResponsiveLayout(
                navController = navController,
                viewModel = viewModel,
                onCitySelected = onCitySelected
            )
        }
        composable("city_detail/{cityId}") { backStackEntry ->
            val cityId = backStackEntry.arguments?.getString("cityId")?.toIntOrNull()
            val city = selectedCity ?: viewModel.filteredCities.value.find { it.id == cityId }
            CityDetailScreen(city = city,navController = navController)
        }
    }
}
