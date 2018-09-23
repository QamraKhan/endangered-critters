package my.com.toru.critter.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.HeatmapTileProvider
import my.com.toru.critter.R


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(3.2086684,101.7579973)
        mMap.addMarker(MarkerOptions().position(sydney).title("Happened in Zoo Negara"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        heatMap()
        drawCircle()
    }
/*
*
* {"lat" : -37.1886, "lng" : 145.708 } ,
{"lat" : -37.8361, "lng" : 144.845 } ,
{"lat" : -38.4034, "lng" : 144.192 } ,
{"lat" : -38.7597, "lng" : 143.67 } ,
{"lat" : -36.9672, "lng" : 141.083 }
*
* */

    private fun heatMap(){

        val list = ArrayList<LatLng>()
        list.add(LatLng(6.057363, 116.5612713))
        list.add(LatLng(5.773924, 116.2359973))
        list.add(LatLng(5.592323, 116.7326883))
        list.add(LatLng(5.533994, 117.4150593))
        list.add(LatLng(5.313182, 117.8961773))

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        val mProvider = HeatmapTileProvider.Builder()
                .data(list)
                .build()
        // Add a tile overlay to the map, using the heat map tile provider.
        val mOverlay = mMap.addTileOverlay(TileOverlayOptions().tileProvider(mProvider))
    }

    private fun drawCircle(){
        mMap.addCircle(CircleOptions()
                .center(LatLng(6.057363, 116.5612713))
                .radius(10000.0))


        mMap.addCircle(CircleOptions()
                .center(LatLng(5.592323, 116.7326883))
                .radius(30000.0))
    }
}