package com.alistair.tdlogin.ui.projects

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alistair.tdlogin.MainActivity
import com.alistair.tdlogin.R
import com.alistair.tdlogin.api.Client
import com.alistair.tdlogin.databinding.FragmentProjectsBinding
import com.alistair.tdlogin.models.AppInfo
import com.alistair.tdlogin.models.ProjectInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "projectsFragment"

class ProjectsFragment : Fragment() {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showAllProjects()

        binding.fabCreateNewProject.setOnClickListener {
            val intent = Intent(requireContext(), CreateNewProjectActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showAllProjects() {
        Client.treeService.getProjects(AppInfo.token!!).enqueue(object : Callback<ProjectInfo> {
            override fun onResponse(call: Call<ProjectInfo>, response: Response<ProjectInfo>) {
                Log.d(TAG, "$response")
                if (response.isSuccessful && response.body() != null) {
                    val projectList = response.body()!!.projectList

                    recyclerView = binding.rvProjectsList
                    recyclerView.adapter = ProjectsListAdapter(projectList)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                }

            }

            override fun onFailure(call: Call<ProjectInfo>, t: Throwable) {
                Toast.makeText(requireContext(), "Error loading projects $t", Toast.LENGTH_SHORT)
                    .show();
            }

        })

    }

}

