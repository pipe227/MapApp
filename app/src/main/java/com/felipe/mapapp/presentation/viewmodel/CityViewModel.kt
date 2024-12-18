package com.felipe.mapapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipe.mapapp.data.di.PrefixTree
import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.domain.model.CityEntity
import com.felipe.mapapp.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val repository: CityRepository
) : ViewModel() {

    private val prefixTree = PrefixTree()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filteredCities = MutableStateFlow<List<City>>(emptyList())
    val filteredCities: StateFlow<List<City>> = _filteredCities

    private val _favorites = MutableStateFlow<List<Int>>(emptyList())
    val favorites: StateFlow<List<Int>> = _favorites

    private val _showFavoritesOnly = MutableStateFlow(false)
    val showFavoritesOnly: StateFlow<Boolean> = _showFavoritesOnly

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteCities = repository.getFavorites()
            _favorites.value = favoriteCities.map { it.id }
        }

        viewModelScope.launch(Dispatchers.IO) {
            val cities = repository.getCities()
            val sortedCities = cities.sortedBy { it.name }
            cities.forEach { prefixTree.insert(it.name, it) }
            _filteredCities.value = sortedCities
        }

        viewModelScope.launch(Dispatchers.IO) {
            combine(searchQuery, _showFavoritesOnly) { query, showFavorites ->
                Pair(query, showFavorites)
            }.collect { (query, showFavorites) ->
                _filteredCities.value = if (showFavorites) {
                    val favoriteCities = _favorites.value.mapNotNull { id ->
                        prefixTree.searchById(id)
                    }.sortedBy { it.name }

                    if (query.isEmpty()) {
                        favoriteCities
                    } else {
                        favoriteCities.filter { it.name.startsWith(query, ignoreCase = true) }
                    }
                } else {
                    if (query.isEmpty()) {
                        prefixTree.getAllCities().sortedBy { it.name }
                    } else {
                        prefixTree.search(query).sortedBy { it.name }
                    }
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavorite(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_favorites.value.contains(city.id)) {
                repository.deleteFavorite(city.id)
                _favorites.value = _favorites.value - city.id
            } else {
                repository.addFavorite(CityEntity(city.id, city.name, city.country, city.coord.lon, city.coord.lat))
                _favorites.value = _favorites.value + city.id
            }
        }
    }
    fun toggleShowFavoritesOnly() {
        _showFavoritesOnly.value = !_showFavoritesOnly.value
    }
}




