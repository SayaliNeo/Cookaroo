package com.app.data.entities


import androidx.annotation.DrawableRes

data class IngredientEntity(
    val name:String,
    @DrawableRes
    val image: Int
)
