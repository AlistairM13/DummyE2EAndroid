package com.alistair.tdlogin.ui.projects

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alistair.tdlogin.R
import com.alistair.tdlogin.databinding.FragmentProjectsBinding

class ProjectsFragment : Fragment() {

    private var _binding :FragmentProjectsBinding? = null
    private val binding get() = _binding!!
private lateinit var recyclerView :RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvProjectsList
        val projects = ('A').rangeTo('Z').toList()
        recyclerView.adapter = ProjectsListAdapter(projects)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}