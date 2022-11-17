package com.alistair.tdlogin.ui.projects.treeinfo

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alistair.tdlogin.R
import com.alistair.tdlogin.models.TreeInfo

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class TreeMapsFragment : Fragment() {
private var treeInfo:TreeInfo? = null
    private val callback = OnMapReadyCallback { googleMap ->
        if(treeInfo!= null){
            val treeLatLng = LatLng(treeInfo!!.latitude.toDouble(), treeInfo!!.longitude.toDouble())
            googleMap.addMarker(MarkerOptions().position(treeLatLng).title(treeInfo!!.localName))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(treeLatLng))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        treeInfo = arguments?.getSerializable("TreeInfo") as? TreeInfo
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_tree_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}