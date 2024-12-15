package com.felipe.mapapp

import com.felipe.mapapp.domain.repository.CityRepository
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.felipe.mapapp.data.di.PrefixTree
import com.felipe.mapapp.domain.model.City
import com.felipe.mapapp.domain.model.CityEntity
import com.felipe.mapapp.domain.model.Coord
import com.felipe.mapapp.presentation.viewmodel.CityViewModel


@OptIn(ExperimentalCoroutinesApi::class)
class CityViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository: CityRepository = mockk()
    private lateinit var viewModel: CityViewModel
    private val testDispatcher = StandardTestDispatcher()




}
