package com.alistair.tdmappart

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TreeService {

    @POST("trees/treeInfo")
    fun addTree(
        @Header("Authorization") token:String,
        @Body treeInfo: TreeInfo
    ): Call<Any>

}