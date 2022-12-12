package com.hasanbektas.rickandmorty

import com.google.gson.annotations.SerializedName

data class ModelCharacters (

    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("status")
    val status : String,
    @SerializedName("species")
    val species : String,
    @SerializedName("type")
    val type : String,
    @SerializedName("gender")
    val  gender : String,
    @SerializedName("origin")
    val origin: ModelOrigin,
    @SerializedName("location")
    val location: ModelLocation,
    @SerializedName("image")
    val  image : String


)