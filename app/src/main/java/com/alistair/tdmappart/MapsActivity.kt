package com.alistair.tdmappart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.alistair.tdmappart.databinding.ActivityMapsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val retrofit = Retrofit.Builder().baseUrl(AppInfo.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mapService = retrofit.create(TreeService::class.java)

        mapService.getTrees(AppInfo.TOKEN).enqueue(object : Callback<Array<TreeInfo>> {
            override fun onResponse(
                call: Call<Array<TreeInfo>>,
                response: Response<Array<TreeInfo>>,
            ) {
                val maps = response.body()
                if (maps != null && maps.isNotEmpty()) {
                    for (map in maps) {
                        val newLocation = LatLng(map.latitude.toDouble(), map.longitude.toDouble())
                        mMap.addMarker(MarkerOptions().position(newLocation).title(map.localName))
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation))
                    }
                }
            }
            override fun onFailure(call: Call<Array<TreeInfo>>, t: Throwable) {
                Log.d("MapPart", "$t")
            }
        })

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
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}