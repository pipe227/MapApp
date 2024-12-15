package com.felipe.mapapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.presentation.viewmodel.CityViewModel
import com.felipe.mapapp.ui.theme.MapAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: CityViewModel = hiltViewModel()
            var selectedCity by remember { mutableStateOf<City?>(null) }
            MapAppTheme() {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    AppNavHost(
                        navController = navController,
                        viewModel = viewModel,
                        onCitySelected = { city ->
                            selectedCity = city
                            if (isPortrait()) {
                                navController.navigate("city_detail/${city.id}")
                            }
                        },
                        selectedCity = selectedCity
                    )
                }

            }
        }
    }

    private fun isPortrait(): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }
}
