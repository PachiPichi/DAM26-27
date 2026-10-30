package com.example.mvvmpatrick.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpatrick.databinding.ItemPoliticoBinding
import com.example.mvvmpatrick.domain.model.Politico

class PoliticoAdapter ()
    : ListAdapter<Politico, PoliticoAdapter.PoliticoViewHolder>(PoliticoDiffCallback()){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PoliticoViewHolder {
        var binding = ItemPoliticoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PoliticoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PoliticoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    class PoliticoViewHolder(
        private val binding: ItemPoliticoBinding
    ): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(politico: Politico){
            binding.tvNombrePolitico.text = politico.nombre
        }
    }
}

class PoliticoDiffCallback : DiffUtil.ItemCallback<Politico>() {
    override fun areItemsTheSame(oldItem: Politico, newItem: Politico): Boolean =
        oldItem.nombre == newItem.nombre
    override fun areContentsTheSame(oldItem: Politico, newItem: Politico): Boolean =
        oldItem == newItem
}