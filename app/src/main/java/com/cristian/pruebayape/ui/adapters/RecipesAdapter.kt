package com.cristian.pruebayape.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cristian.pruebayape.R
import com.cristian.pruebayape.databinding.RecipiesListItemsBinding
import com.cristian.pruebayape.domain.models.RecipesUI

class RecipesAdapter(
    private val recipeOnClick: (RecipesUI) -> Unit
) : ListAdapter<RecipesUI, RecipesAdapter.RecipesListViewHolder>(DiffCallback) {

    inner class RecipesListViewHolder(private val binding: RecipiesListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipesUI: RecipesUI) {
            with(binding) {
                idNameCategory.text = recipesUI.category
                idNameMeal.text = recipesUI.name
                idNameArea.text = recipesUI.area
                imgCat.load(recipesUI.thumbnail) {
                    placeholder(R.drawable.baseline_image).error(R.drawable.baseline_error_outline)
                }
                cardRecipe.setOnClickListener {
                    recipeOnClick(recipesUI)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListViewHolder =
        RecipesListViewHolder(
            RecipiesListItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: RecipesListViewHolder, position: Int) =
        holder.bind(getItem(position))

    object DiffCallback : DiffUtil.ItemCallback<RecipesUI>() {
        override fun areItemsTheSame(oldItem: RecipesUI, newItem: RecipesUI): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RecipesUI, newItem: RecipesUI): Boolean {
            return oldItem.id == newItem.id
        }

    }

}