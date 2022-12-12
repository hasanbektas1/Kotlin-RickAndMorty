package com.hasanbektas.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hasanbektas.rickandmorty.databinding.ActivityCharactersBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersActivity : AppCompatActivity() {

    private val BASE_URL = "https://rickandmortyapi.com/"

    private lateinit var binding : ActivityCharactersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        val selectedCharecter = intent.getSerializableExtra("characters") as String
        val selectedCharecters = selectedCharecter.toString()
        // String karakterin belirtilen indexten sonrasını almak
        val selectedString = selectedCharecters.subSequence(42,selectedCharecters.length).toString().toInt()

        getDataCharacters(selectedString)
    }

    private fun getDataCharacters(CharId : Int){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RickAndMortyAPI::class.java)

        val call = service.getDataCharaters(CharId)

        call.enqueue(object : Callback<ModelCharacters> {
            override fun onResponse(call: Call<ModelCharacters>, response: Response<ModelCharacters>) {
                if (response.isSuccessful){
                    response.body()?.let {

                        binding.textViewCharactersName.text = "Name: ${it.name} "
                        binding.textViewCharactersStatus.text = "Statüs: ${it.status} "
                        binding.textViewCharactersSpecies.text = "Species: ${it.species} "
                        binding.textViewCharactersGender.text = "Gender: ${it.gender} "
                        binding.textViewCharactersLocation.text = "Location: ${it.location.name} "


                        Picasso.get().load(it.image).into(binding.CharacterActivityImageView)
                        Picasso.get().load(it.image).into(binding.CharacterActivityCircleMageView)
                    }
                }
            }
            override fun onFailure(call: Call<ModelCharacters>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}