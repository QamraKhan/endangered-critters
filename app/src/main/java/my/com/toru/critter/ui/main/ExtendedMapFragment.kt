package my.com.toru.critter.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ExtendedMapFragment : SupportMapFragment(), OnMapReadyCallback {

    override fun onCreateView(p0: LayoutInflater, p1: ViewGroup?, p2: Bundle?): View? {
        getMapAsync(this@ExtendedMapFragment)
        return super.onCreateView(p0, p1, p2)
    }

    override fun onMapReady(map: GoogleMap?) {
        Log.w("Extended", "ready")
        val sydney = LatLng(-34.0, 151.0)
        map?.apply {
            addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            moveCamera(CameraUpdateFactory.newLatLng(sydney))
            setMinZoomPreference(5f)
            uiSettings.isMapToolbarEnabled = false
            uiSettings.isMyLocationButtonEnabled = true
        }
    }
}