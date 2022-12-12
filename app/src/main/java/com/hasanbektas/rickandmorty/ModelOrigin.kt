package com.hasanbektas.rickandmorty

import com.google.gson.annotations.SerializedName

data class ModelOrigin (

    @SerializedName("name")
    val name : String,
    @SerializedName("url")
    val url : String

)