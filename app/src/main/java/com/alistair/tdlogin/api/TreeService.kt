package com.alistair.tdlogin.api

import com.alistair.tdlogin.models.LoginInfo
import com.alistair.tdlogin.models.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TreeService {
    @POST("authenticate")
    fun authenticate(
        @Body loginInfo : LoginInfo
    ): Call<Token>

}