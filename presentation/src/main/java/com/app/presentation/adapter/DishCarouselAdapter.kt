package com.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.presentation.databinding.ViewCarouselItemBinding
import com.domain.model.Dish

class DishCarouselAdapter :
    ListAdapter<Dish, DishCarouselAdapter.CarouselViewHolder>(CatalogDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding =
            ViewCarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        getItem(position).run {
            holder.setData(this.dishImage)
        }
    }

   private class CatalogDiff : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }
    }

    inner class CarouselViewHolder(private val mBinding: ViewCarouselItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun setData(sliderImage: Int) {
            mBinding.ivDish.setBackgroundResource(sliderImage)
        }
    }


}