package com.hasanbektas.rickandmorty

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hasanbektas.rickandmorty.databinding.RecyclerRowBinding
import kotlinx.android.synthetic.main.recycler_row.view.*

class RickAndMortyAdapter (val rickAndmortyList:List<ResultsModel>): RecyclerView.Adapter<RickAndMortyAdapter.RickAndMortyHolder>(){

    class RickAndMortyHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RickAndMortyHolder(binding)
    }

    override fun onBindViewHolder(holder: RickAndMortyHolder, position: Int) {

        holder.binding.episodeText.text = "Episode :  ${rickAndmortyList.get(position).episode}"
        holder.binding.nameText.text = "Name  : ${rickAndmortyList.get(position).name}"

        holder.itemView.cardView.setOnClickListener {

            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("detail",rickAndmortyList.get(position))
            holder.itemView.context.startActivity(intent)

        }
    }
    override fun getItemCount(): Int {

        return rickAndmortyList.size
    }
}