package com.alistair.tdlogin.ui.projects

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alistair.tdlogin.databinding.ProjectsSingleItemBinding
import com.alistair.tdlogin.models.Project

class ProjectsListAdapter(private val projects: List<Project>) : RecyclerView.Adapter<ProjectsListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProjectsSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProjectsSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvProjectName.text = projects[position].name
    }

    override fun getItemCount() = projects.size

}



