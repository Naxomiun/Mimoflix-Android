package com.nramos.mimoflix.ui.genres.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nramos.mimoflix.databinding.ItemSelectorBinding
import com.nramos.mimoflix.extension.setCategorySelected
import com.nramos.mimoflix.model.Genre
import kotlin.properties.Delegates

class CategorySelectorAdapter(
    category : Int = 0,
    private val items : List<Genre> = emptyList(),
    private val listener : (Int?) -> Unit
) : RecyclerView.Adapter<CategorySelectorAdapter.ViewHolder>(){

    var categorySelected : Int = category

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val binding = ItemSelectorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemSelectorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre : Genre, position : Int) {
            binding.apply {
                btnCategory.apply {
                    text = genre.name
                    setCategorySelected(position == categorySelected)
                    setOnClickListener {
                        if(categorySelected != position) {
                            notifyItemChanged(position)
                            notifyItemChanged(categorySelected)
                            categorySelected = position
                            listener(genre.id)
                        }
                    }
                }
            }
        }
    }

}