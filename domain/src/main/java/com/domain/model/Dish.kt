package com.domain.model

import androidx.annotation.DrawableRes

data class Dish(
    val name:String,
    @DrawableRes
    val dishImage:Int,
    val ingredientList: List<Ingredient>
)