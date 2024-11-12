package com.domain.model


import androidx.annotation.DrawableRes

data class Ingredient(
    val name:String,
    @DrawableRes
    val image: Int
)
