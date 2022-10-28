package com.alistair.navpart.projects

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.contextaware.ContextAware
import androidx.recyclerview.widget.RecyclerView
import com.alistair.navpart.data.ProjectsList.projects
import com.alistair.navpart.models.Project

class ProjectsAdapter(private val context: Context, private val projects: List<Project>) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(android.R.id.text1).text = projects[position].name
    }

    override fun getItemCount() = projects.size

}
