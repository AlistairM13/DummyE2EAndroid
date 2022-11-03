package com.alistair.tdlogin.models

data class ProjectInfo(
    val projectList : List<Project>
)

data class Project(
    val createdBy: String,
    val editableForAll: Boolean = false,
    val id: Double,
    val name: String,
    val projectUsers: List<Any> = emptyList(),
    val treeinfos: List<Any> = emptyList(),
    val visibleForAll: Boolean = false
)