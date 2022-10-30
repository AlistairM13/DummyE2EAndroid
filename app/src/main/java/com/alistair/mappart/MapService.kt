package com.alistair.mappart

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MapService {

   @POST("trees/treeInfo")
   fun saveTree(
       @Body treeInfo:TreeInfo
   ): Call<Any>
   @GET("trees")
   fun getTrees(
      @Header("Authorization") token:String
   ):Call<Any>
}
