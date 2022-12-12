package com.hasanbektas.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.hasanbektas.rickandmorty.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var rickAndMortyModels : ArrayList<ResultsModel>

    private lateinit var  rickAndMortyAdapter : RickAndMortyAdapter

    lateinit var layoutManager : GridLayoutManager

    private val BASE_URL = "https://rickandmortyapi.com/"

    var total = 0
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.TextViewNext.visibility = View.GONE

        rickAndMortyModels = ArrayList<ResultsModel>()

        getData()

        binding.TextViewNext.setOnClickListener {

            binding.TextViewNext.visibility = View.GONE

            page ++

            getData()

        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                if(!recyclerView.canScrollVertically(0)){

                    binding.TextViewNext.visibility = View.VISIBLE
                    if(page == 3){

                        binding.TextViewNext.visibility = View.GONE
                    }
                }

                super.onScrollStateChanged(recyclerView, newState)
            }
        })


        val autoSliderImage= ArrayList<SlideModel>()

        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/1.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/2.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/3.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/4.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/5.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/129.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/134.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/145.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/157.jpeg",""))
        autoSliderImage.add(SlideModel("https://rickandmortyapi.com/api/character/avatar/176.jpeg.jpeg",""))

        val imageSlider = findViewById<ImageSlider>(R.id.ImageSliderr)
        imageSlider.setImageList(autoSliderImage, ScaleTypes.FIT)

        var image = binding.linearbar
        image.setAlpha(0.9F)

        layoutManager = GridLayoutManager(this,1)
        recyclerView.layoutManager = layoutManager

    }
    private fun getData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RickAndMortyAPI::class.java)

        val call = service.getData(page)

        call.enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                if (response.isSuccessful){
                    response.body()?.let {

                        rickAndMortyModels.addAll(ArrayList(it.results))

                        rickAndMortyModels?.let {

                            if (page == 1 ){
                                rickAndMortyAdapter = RickAndMortyAdapter(it)
                                recyclerView.adapter = rickAndMortyAdapter

                            }else{
                                rickAndMortyAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<Model>, t: Throwable) {

                t.printStackTrace()
            }
        })
    }
}