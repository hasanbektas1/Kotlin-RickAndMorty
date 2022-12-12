package com.hasanbektas.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hasanbektas.rickandmorty.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {

    private val BASE_URL = "https://rickandmortyapi.com/"

    private lateinit var detailList : ArrayList<ResultsModel>

    private lateinit var recyclerViewDetilAdapter : RickAndMortyDetailAdapter
    lateinit var layoutManager : LinearLayoutManager

    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailList = ArrayList<ResultsModel>()


        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerViewDetay.layoutManager = layoutManager

        val intent = intent

        val selectedRickMorty = intent.getSerializableExtra("detail") as ResultsModel

        getDataDetay(selectedRickMorty.id)
    }

    private fun getDataDetay(idd : Int){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RickAndMortyAPI::class.java)

        val call = service.getDataDetay(idd)

        call.enqueue(object : Callback<ResultsModel> {
            override fun onResponse(call: Call<ResultsModel>, response: Response<ResultsModel>) {


                if (response.isSuccessful){
                    response.body()?.let {

                        binding.textDetailName.text = "Name : ${it.name}"
                        binding.textDetailDate.text = "Date : ${it.air_date}"
                        binding.textDetailEpisode.text = "Episode : ${it.episode}"

                        recyclerViewDetilAdapter = RickAndMortyDetailAdapter(it.characters)
                        recyclerViewDetay.adapter = recyclerViewDetilAdapter

                    }
                }
            }
            override fun onFailure(call: Call<ResultsModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}