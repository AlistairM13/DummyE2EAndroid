package com.alistair.tdlogin.ui.projects

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alistair.tdlogin.databinding.ProjectsSingleItemBinding

class ProjectsListAdapter( val projects: List<Char>) : RecyclerView.Adapter<ProjectsListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProjectsSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProjectsSingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvProjectName.text = projects[position].toString()
    }

    override fun getItemCount() = projects.size

}



