package com.alistair.tdlogin.ui.projects.treeinfo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.alistair.tdlogin.R
import com.alistair.tdlogin.databinding.FragmentCameraBinding
import java.io.File


private const val FILE_NAME = "photo.jpg"
private const val REQUEST_CODE = 1
private lateinit var photoFile: File
class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding? = null

    private val binding get() = _binding!!
private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        binding.btnGetImage.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            photoFile =  getPhotoFile(FILE_NAME)

            val fileProvider = FileProvider.getUriForFile(requireContext(), "com.alistair.tdlogin.fileprovider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

            if(takePictureIntent.resolveActivity(requireActivity().packageManager)!=null){
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            }else{
                Toast.makeText(requireContext(), "Unable to open Camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            afterImageCaptured()
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            binding.ivTreeImage.setImageBitmap(takenImage)
            val cameraBundle = bundleOf("cameraInfo" to photoFile.name)
            navController.navigate(R.id.action_cameraFragment_to_addTreeInfoFragment, cameraBundle)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun afterImageCaptured() {
        binding.ivTreeImage.isGone = false
        binding.btnImageCaptured.isGone = false
    
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}