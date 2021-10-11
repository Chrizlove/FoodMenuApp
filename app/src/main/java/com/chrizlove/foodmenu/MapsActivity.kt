package com.chrizlove.foodmenu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.chrizlove.foodmenu.Model.UserAddress
import com.chrizlove.foodmenu.Services.DataServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.chrizlove.foodmenu.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_maps.bottom_sheet
import kotlinx.android.synthetic.main.bottom_sheet_maps.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        proceedToPayButton.setOnClickListener{
            if(addressLine1.text.isEmpty() || addressLine2.text.isEmpty() || landmark.text.isEmpty())
            {
               Toast.makeText(this,"Fill all the Fields",Toast.LENGTH_SHORT).show()
            }
            else {
                DataServices.userInputAddress.clear()
                DataServices.userInputAddress.add(
                    UserAddress(
                        addressLine1.text.toString(),
                        addressLine2.text.toString(),
                        landmark.text.toString()
                    )
                )
                Log.d("User Address", DataServices.userInputAddress[0].userlandmark)
                val intent = Intent(this, PatmentActivity::class.java)
                startActivity(intent)
            }
        }
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
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
        mMap.isMyLocationEnabled = true
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if (it != null) {
                val userCurrentLocation = LatLng(it.latitude, it.longitude)
               // mMap.addMarker(MarkerOptions().position(userCurrentLocation).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userCurrentLocation, 16f))
                mMap.uiSettings.setAllGesturesEnabled(false)
                val geocoder: Geocoder
                geocoder = Geocoder(this, Locale.getDefault())
                DataServices.address = geocoder.getFromLocation(
                    it.latitude,
                    it.longitude,
                    1
                ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                fixedAddress.text =
                    "${DataServices.address[0].locality}, ${DataServices.address[0].adminArea}, ${DataServices.address[0].countryName}"
                BottomSheetBehavior.from(bottom_sheet).apply {
                    bottom_sheet.viewTreeObserver.addOnGlobalLayoutListener(
                        object: ViewTreeObserver.OnGlobalLayoutListener {
                            override fun onGlobalLayout() {
                                bottomsheetmaps.viewTreeObserver.removeOnGlobalLayoutListener(this)
                                peekHeight = proceedToPayButton.bottom
                            }
                        })
                }
            }
            else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }
    }
}