package com.example.flag.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import com.example.flag.internet.DataItem

class CountryGridAdapter : ListAdapter<DataItem,
        CountryGridAdapter.CountryViewHolder>(DiffCallback) {

    class CountryViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countyData: DataItem) {
            binding.data = countyData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryGridAdapter.CountryViewHolder {
        return CountryViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CountryGridAdapter.CountryViewHolder, position: Int) {
        val countryData = getItem(position)
        holder.bind(countryData)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.flag == newItem.flag || oldItem.name == newItem.name
        }
    }
}