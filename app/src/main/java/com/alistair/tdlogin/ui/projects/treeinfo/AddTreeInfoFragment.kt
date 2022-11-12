package com.alistair.tdlogin.ui.projects.treeinfo


import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.alistair.tdlogin.R
import com.alistair.tdlogin.databinding.FragmentAddTreeInfoBinding
import com.alistair.tdlogin.models.BasicTreeInfo


import com.google.android.gms.location.*
import java.io.Serializable

private const val TAG = "AddTreeInfo"

class AddTreeInfoFragment : Fragment(), View.OnClickListener{
    private lateinit var navController: NavController
    private var _binding: FragmentAddTreeInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

//    private var basicTreeInfo: java.io.Serializable? = null
//    private var treeHealthInfo: java.io.Serializable? = null
//    private var imagePath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val basicTreeInfo = arguments?.getSerializable("basicTreeInfo")
        val treeHealthInfo = arguments?.getString("treeHealthInfo")
        val cameraInfo = arguments?.getString("cameraInfo")
        Log.d("addTreeInfo", "basicInfo $basicTreeInfo")
        Log.d("addTreeInfo", "treehealth $treeHealthInfo")
        Log.d("addTreeInfo", "camera $cameraInfo \n")
//        basicTreeInfo = arguments?.getSerializable(BASIC_TREE_INFO) as? BasicTreeInfo
//        treeHealthInfo = arguments?.getSerializable(TREE_HEALTH_INFO) as? TreeHealthInfo
//        imagePath = arguments?.getString(IMAGE_PATH)
//        Log.d(TAG, "$basicTreeInfo $treeHealthInfo $imagePath")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTreeInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.cardTreeInfo.setOnClickListener(this)
        binding.cardTreeHealth.setOnClickListener(this)
        binding.cardTreeImage.setOnClickListener(this)
        binding.cardTreeLocation.setOnClickListener {
            fetchLocation()
        }

    }

    private fun fetchLocation() {

        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), 101)
            return
        }


        LocationServices.getFusedLocationProviderClient(requireContext())
            .getCurrentLocation(100, null).addOnSuccessListener {
            if (it != null) {
                showLatLong(it)
            }
        }

    }

    private fun showLatLong(location: Location) {
        binding.tvLatitude.isGone = false
        binding.tvLongitude.isGone = false
        binding.tvLatitude.text = getString(R.string.latitude, location.latitude)
        binding.tvLongitude.text = getString(R.string.longitude, location.longitude)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.cardTreeInfo -> navController.navigate(R.id.action_addTreeInfoFragment_to_basicTreeInfoFragment)
            R.id.cardTreeHealth -> navController.navigate(R.id.action_addTreeInfoFragment_to_treeHealthFragment)
            R.id.cardTreeImage -> navController.navigate(R.id.action_addTreeInfoFragment_to_cameraFragment)
        }
    }


}