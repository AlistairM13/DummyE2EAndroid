package com.alistair.tdlogin.api

import com.alistair.tdlogin.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TreeService {
    @POST("/authenticate")
    fun authenticate(
        @Body loginInfo: LoginInfo,
    ): Call<Token>

    @POST("/addUser")
    fun addUser(
        @Body registerInfo: RegisterInfo,
    ): Call<Any>

    @GET("/projects/All")
    fun getProjects(
        @Header("Authorization") token: String,
    ): Call<ProjectInfo>

    @POST("/projects")
    fun addProject(
        @Header("Authorization") token: String,
        @Body project: Project,
    ): Call<Any>

}