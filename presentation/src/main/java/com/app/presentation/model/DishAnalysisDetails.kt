package com.app.presentation.model

data class DishAnalysisDetails(
    val ingredientCount: Int?,
    val topCharacters: List<Pair<Char, Int>>
)