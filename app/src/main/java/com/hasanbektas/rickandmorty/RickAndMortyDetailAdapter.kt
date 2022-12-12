package com.hasanbektas.rickandmorty

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hasanbektas.rickandmorty.databinding.RecyclerRowDetailBinding
import kotlinx.android.synthetic.main.recycler_row.view.*

class RickAndMortyDetailAdapter (val rickAndmortyListDetay: List<String>): RecyclerView.Adapter<RickAndMortyDetailAdapter.RickAndMortyHolderDetay>() {

    class RickAndMortyHolderDetay(val binding: RecyclerRowDetailBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyHolderDetay {
        val binding = RecyclerRowDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RickAndMortyHolderDetay(binding)
    }

    override fun onBindViewHolder(holder: RickAndMortyHolderDetay, position: Int) {

        val selectedCaracters = rickAndmortyListDetay.get(position).subSequence(42,rickAndmortyListDetay.get(position).length).toString()

        holder.binding.textCharacters.text = selectedCaracters

        holder.itemView.cardView.setOnClickListener {

            val intent = Intent(holder.itemView.context,CharactersActivity::class.java)
            intent.putExtra("characters",rickAndmortyListDetay.get(position))
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {

        return rickAndmortyListDetay.size

    }
}