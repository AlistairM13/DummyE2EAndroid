package com.alistair.navpart.projects

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alistair.navpart.R
import com.alistair.navpart.data.ProjectsList
import com.alistair.navpart.databinding.FragmentProjectsBinding

class ProjectsFragment : Fragment() {
    private var _binding : FragmentProjectsBinding? = null
    private val binding get() = _binding!!
 private lateinit var recyclerView:RecyclerView
 private val projects = ProjectsList.projects

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.rvProjectsList

        recyclerView.adapter = ProjectsAdapter(requireContext(), projects)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        navController = Navigation.findNavController(view)

        binding.fabCreateNewProject.setOnClickListener{
            navController.navigate(R.id.action_projectsView_to_createNewProjectFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}