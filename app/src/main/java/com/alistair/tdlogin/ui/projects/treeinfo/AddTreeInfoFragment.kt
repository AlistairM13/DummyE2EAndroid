package com.alistair.tdlogin.ui.projects.treeinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.alistair.tdlogin.R
import com.alistair.tdlogin.databinding.FragmentAddTreeInfoBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class AddTreeInfoFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var _binding: FragmentAddTreeInfoBinding
    private val binding get() = _binding
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

        binding.cardTreeInfo.setOnClickListener(this)
        binding.cardTreeHealth.setOnClickListener(this)
        binding.cardTreeImage.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.cardTreeInfo -> navController.navigate(R.id.action_addTreeInfoFragment_to_basicTreeInfoFragment)
            R.id.cardTreeHealth -> navController.navigate(R.id.action_addTreeInfoFragment_to_treeHealthFragment)
            R.id.cardTreeImage -> navController.navigate(R.id.action_addTreeInfoFragment_to_cameraFragment)
        }
    }


}