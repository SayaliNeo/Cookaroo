package com.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.data.entities.IngredientEntity
import com.app.presentation.databinding.ViewIngredientItemBinding
import com.domain.model.Ingredient

class IngredientsListAdapter :
    ListAdapter<Ingredient, IngredientsListAdapter.ViewHolder>(CatalogDiff()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientsListAdapter.ViewHolder {
        val binding =
            ViewIngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: IngredientsListAdapter.ViewHolder, position: Int) {
        getItem(position).run {
            holder.setData(this)
        }
    }

    inner class ViewHolder(private val mBinding: ViewIngredientItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun setData(ingredient: Ingredient) {
            mBinding.apply {
                ivIngredient.setBackgroundResource(ingredient.image)
                tvIngredientName.text = ingredient.name
            }
        }
    }

    private class CatalogDiff : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem == newItem
        }
    }

}