package com.felipe.mapapp.data.di

import com.felipe.mapapp.domain.model.City

class Node {
    val children: MutableMap<Char, Node> = mutableMapOf() // Hijo de cada nodo
    val cities: MutableList<City> = mutableListOf() // Lista de ciudades asociadas a este nodo
}
