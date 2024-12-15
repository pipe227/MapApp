package com.felipe.mapapp.data.di

import com.felipe.mapapp.domain.model.City

class PrefixTree {

    private val root = Node()
    private val allCities = mutableListOf<City>()

    fun insert(city: String, data: City) {
        var current = root
        city.lowercase().forEach { char ->
            current = current.children.computeIfAbsent(char) { Node() }
        }
        current.cities.add(data)
        allCities.add(data)
    }

    fun search(prefix: String): List<City> {
        var current = root
        prefix.lowercase().forEach { char ->
            current = current.children[char] ?: return emptyList()
        }
        return current.cities
    }
    fun searchById(id: Int): City? {
        return allCities.find { it.id == id }
    }

    fun getAllCities(): List<City> = allCities
}



