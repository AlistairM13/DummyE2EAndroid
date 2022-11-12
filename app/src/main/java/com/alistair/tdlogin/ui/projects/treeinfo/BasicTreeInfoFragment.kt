package com.alistair.tdlogin.ui.projects.treeinfo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.alistair.tdlogin.R
import com.alistair.tdlogin.databinding.FragmentBasicTreeInfoBinding
import com.alistair.tdlogin.models.BasicTreeInfo


class BasicTreeInfoFragment : Fragment() {
private var _binding : FragmentBasicTreeInfoBinding? =null
    private val binding get()= _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBasicTreeInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.btnAddBasicTreeInfo.setOnClickListener {
            getBasicTreeInfo()
        }
    }

    private fun getBasicTreeInfo() {
        val treeName = binding.etNameOfTree.text.toString()
        val species = binding.etSpecies.text.toString()
        val localName = binding.etLocalName.text.toString()
        val dbh = binding.etDbh.text.toString()
        val height = binding.etHeight.text.toString()
        val spread = binding.etSpread.text.toString()
        val remarks = binding.etRemarks.text.toString()

        try {
            val basicTreeInfo = BasicTreeInfo(
                treeName = treeName,
                species=species,
                localName=localName,
                DBH = dbh,
                height= height.toDouble(),
                spread=spread.toDouble(),
                remarks=remarks
            )
            Log.d("BasicTreeInfo", "$basicTreeInfo")

            val bundle = bundleOf("basicTreeInfo" to basicTreeInfo)
           navController.navigate(R.id.action_basicTreeInfoFragment_to_addTreeInfoFragment, bundle)

        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show()
        }
    }
}