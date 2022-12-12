package com.hasanbektas.rickandmorty

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class İnfoModel (

    @SerializedName("count")
    val count : Int,
    @SerializedName("pages")
    val pages : Int,
    @SerializedName("next")
    val next : String,



    ): Serializable