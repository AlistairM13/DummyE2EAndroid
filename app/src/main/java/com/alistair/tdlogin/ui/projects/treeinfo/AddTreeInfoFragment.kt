package com.alistair.tdlogin.ui.projects.treeinfo


import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.alistair.tdlogin.R
import com.alistair.tdlogin.databinding.FragmentAddTreeInfoBinding
import com.alistair.tdlogin.models.TreeInfo


import com.google.android.gms.location.*
import java.io.File
import java.io.Serializable

private const val TAG = "AddTreeInfo"
private const val FILE_NAME = "photo.jpg"
private const val REQUEST_CODE = 1
private lateinit var photoFile: File

class AddTreeInfoFragment : Fragment() {
    private lateinit var navController: NavController
    private var _binding: FragmentAddTreeInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
private  lateinit var latitude:String
        private lateinit  var longitude:String

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
        binding.btnSetLocation.setOnClickListener {
            fetchLocation()
        }
        binding.clTreeImage.setOnClickListener {
            captureTreeImage()
        }

        binding.btnAddTreeInfo.setOnClickListener {
            getTreeInfo()
        }

    }

    private fun getTreeInfo() {
        val treeName = binding.etNameOfTree.text.toString()
        val species = binding.etSpecies.text.toString()
        val localName = binding.etLocalName.text.toString()
        val dbh = binding.etDbh.text.toString()
        val height = binding.etHeight.text.toString()
        val spread = binding.etSpread.text.toString()
        val remarks = binding.etRemarks.text.toString()
        val imagePath = photoFile.name
        val setLatitude = latitude
        val setLongitude = longitude
        try {
            val treeInfo = TreeInfo(
                imagePath = imagePath,
                latitude= setLatitude,
                longitude= setLongitude,
                treeName = treeName,
                species = species,
                localName = localName,
                DBH = dbh,
                height = height.toDouble(),
                spread = spread.toDouble(),
                remarks = remarks
            )
            Log.d("BasicTreeInfo", "$treeInfo")

            val treeMapsBundle = bundleOf("TreeInfo" to treeInfo)
            navController.navigate(R.id.action_addTreeInfoFragment_to_treeMapsFragment, treeMapsBundle )


        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show()
        } catch (e: NullPointerException) {
            Toast.makeText(requireContext(),"Please make sure all fields are filled", Toast.LENGTH_SHORT).show()
        }
    }

    // to get image
    private fun captureTreeImage() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)

        val fileProvider = FileProvider.getUriForFile(requireContext(),
            "com.alistair.tdlogin.fileprovider",
            photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        } else {
            Toast.makeText(requireContext(), "Unable to open Camera", Toast.LENGTH_SHORT).show();
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            afterImageCaptured()
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            binding.ivTreeImage.setImageBitmap(takenImage)

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun afterImageCaptured() {
        binding.ivPlaceHolderCameraIcon.isGone = true
        binding.ivTreeImage.isGone = false
    }


    //    For location
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
                Log.d("Location", "$it")
                if (it != null) {
                    showLatLong(it)
                    latitude = it.latitude.toString()
                    longitude = it.longitude.toString()
                }
            }


    }

    private fun showLatLong(location: Location) {
        binding.tvLatitude.isGone = false
        binding.tvLongitude.isGone = false
        binding.tvLatitude.text = getString(R.string.latitude, location.latitude)
        binding.tvLongitude.text = getString(R.string.longitude, location.longitude)
    }


}