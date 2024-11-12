package com.app.data.entities

import androidx.annotation.DrawableRes

data class DishEntity(
    val name:String,
    @DrawableRes
    val dishImage:Int,
    val ingredientEntityList: List<IngredientEntity>
)