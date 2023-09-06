package com.cristian.pruebayape.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cristian.pruebayape.databinding.IngredientItemListBinding
import com.cristian.pruebayape.domain.models.IngredientsUI

class IngredientsAdapter : ListAdapter<IngredientsUI, IngredientsAdapter.IngredientsListViewHolder>(
    DiffCallback
) {

    inner class IngredientsListViewHolder(private val binding: IngredientItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredientsUI: IngredientsUI) {
            with(binding) {
                idIngredient.text = "• ${ingredientsUI.strIngredient}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsListViewHolder =
        IngredientsListViewHolder(
            IngredientItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IngredientsListViewHolder, position: Int) =
        holder.bind(getItem(position))

    object DiffCallback : DiffUtil.ItemCallback<IngredientsUI>() {
        override fun areItemsTheSame(oldItem: IngredientsUI, newItem: IngredientsUI): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: IngredientsUI, newItem: IngredientsUI): Boolean {
            return oldItem.strIngredient == newItem.strIngredient
        }

    }
}