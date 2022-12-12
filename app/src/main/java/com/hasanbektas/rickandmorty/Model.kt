package com.hasanbektas.rickandmorty

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Model(

    @SerializedName("info")
    val info: İnfoModel,
    @SerializedName("results")
    val results:List<ResultsModel>,
): Serializable