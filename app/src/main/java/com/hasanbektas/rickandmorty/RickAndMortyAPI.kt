package com.hasanbektas.rickandmorty

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyAPI {


    // https://rickandmortyapi.com/api/episode
    // https://rickandmortyapi.com/api/episode/8
    // https://rickandmortyapi.com/api/character/1


    @GET("api/episode")
    fun getData(@Query("page") page : Int) : Call<Model>

    @GET("api/episode/{id}")
    fun getDataDetay(@Path("id") id : Int ) : Call<ResultsModel>

    @GET("api/character/{id}")
    fun getDataCharaters(@Path("id") id : Int ) : Call<ModelCharacters>
}