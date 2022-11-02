package com.alistair.tdlogin.models

import android.app.Application

object AppInfo: Application() {
    var currentUser: String? = null
    var token: String? = null
}
