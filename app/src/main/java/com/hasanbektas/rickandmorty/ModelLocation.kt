package com.hasanbektas.rickandmorty

import com.google.gson.annotations.SerializedName

data class ModelLocation (

    @SerializedName("name")
    val name : String,
    @SerializedName("url")
    val url : String
)