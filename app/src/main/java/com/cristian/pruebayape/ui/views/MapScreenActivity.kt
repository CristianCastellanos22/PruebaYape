package com.cristian.pruebayape.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristian.pruebayape.R
import com.cristian.pruebayape.databinding.ActivityMapScreenBinding
import com.cristian.pruebayape.domain.models.OriginUI
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapScreenActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createFragment()
    }

    private fun createFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("StringFormatMatches")
    override fun onMapReady(map: GoogleMap) {
        getData()?.also { origin ->
            val coordinator = LatLng(origin.lat, origin.lng)
            val marker = MarkerOptions().position(coordinator).title(
                getString(
                    R.string.location_in,
                    origin.lat,
                    origin.lng
                ))
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinator, 10f),
                2000,
                null
            )
        }
    }

    private fun getData(): OriginUI? =
        intent.extras.let { bundle ->
            bundle?.getParcelable(ORIGIN_UI)
        }

    companion object {
        const val ORIGIN_UI = "ORIGIN_UI"
    }

}
