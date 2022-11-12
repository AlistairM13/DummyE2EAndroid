package com.alistair.tdlogin.models

data class BasicTreeInfo(
val treeName:String,
val species:String,
val localName:String,
val DBH:String,
val height:Double,
val spread:Double,
val remarks:String
):java.io.Serializable
