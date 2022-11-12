package com.alistair.tdlogin.ui.projects.treeinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.alistair.tdlogin.R
import com.alistair.tdlogin.databinding.FragmentTreeHealthBinding


class TreeHealthFragment : Fragment() {
private var _binding:FragmentTreeHealthBinding? = null
private val binding get()= _binding!!
private lateinit var navcontroller:NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTreeHealthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navcontroller = Navigation.findNavController(view)
        binding.btnTreeHealthDone.setOnClickListener {
            getTreeHealthInfo()
        }
    }

    private fun getTreeHealthInfo() {
        val somedata = binding.partLikelyToFail.text.toString()

        val treeHealthBundle = bundleOf("treeHealthInfo" to somedata)
        navcontroller.navigate(R.id.action_treeHealthFragment_to_addTreeInfoFragment, treeHealthBundle)
    }

}