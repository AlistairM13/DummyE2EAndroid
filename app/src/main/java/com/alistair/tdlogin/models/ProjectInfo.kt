package com.alistair.tdlogin.models

data class ProjectInfo(
    val projectList : List<Project>
)

data class Project(
    val createdBy: String,
    val editableForAll: Boolean,
    val id: Double,
    val name: String,
    val projectUsers: List<Any>,
    val treeinfos: List<Any>,
    val visibleForAll: Boolean
)