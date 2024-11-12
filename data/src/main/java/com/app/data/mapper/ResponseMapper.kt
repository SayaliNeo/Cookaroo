package com.app.data.mapper

import com.app.data.entities.DishCollectionEntity
import com.app.data.entities.DishEntity
import com.app.data.entities.IngredientEntity
import com.domain.model.Dish
import com.domain.model.DishCollection
import com.domain.model.Ingredient


fun DishCollectionEntity.toDomainModel():DishCollection {
   return this.run {
        DishCollection(
            dishes.map { it.toDomainModel() }
        )
    }
}


fun DishEntity.toDomainModel():Dish {
   return this.run {
        Dish(name,dishImage,ingredientEntityList.map { it.toDomainModel() })
    }
}

fun IngredientEntity.toDomainModel():Ingredient {
    return this.run {
        Ingredient(name,image)
    }
}