package com.felipe.mapapp.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.util.GeoPoint

@Composable
fun CityMap(
    latitude: Double,
    longitude: Double,
    cityName: String
) {
    AndroidView(
        factory = { ctx ->
            MapView(ctx).apply {
                setMultiTouchControls(true)
                controller.setZoom(12.0)
                controller.setCenter(GeoPoint(latitude, longitude))

                val marker = Marker(this)
                marker.position = GeoPoint(latitude, longitude)
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.title = cityName
                overlays.add(marker)
            }
        },
        update = { mapView ->
            mapView.controller.setCenter(GeoPoint(latitude, longitude))
        }
    )
}
