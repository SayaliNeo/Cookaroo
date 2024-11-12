package com.app.data.repository


import com.app.data.R
import com.app.data.entities.DishEntity
import com.app.data.entities.IngredientEntity
import com.app.data.mapper.toDomainModel
import com.domain.model.DishCollection
import com.domain.repository.DishRepository

class DishRepositoryImpl : DishRepository {
    override suspend fun fetchDishCollection(): DishCollection? {
        val dishCollection = listOf(
            DishEntity(
                name = "Paneer Tikka",
                dishImage = R.drawable.paneer_tikka,
                ingredientEntityList = listOf(
                    IngredientEntity(
                        "Paneer",
                        R.drawable.paneer,
                    ),
                    IngredientEntity(
                        "yogurt",
                        R.drawable.yogurt
                    ),
                    IngredientEntity(
                        "ginger-garlic paste",
                        R.drawable.ginger_garlic_paste,
                    ),
                    IngredientEntity(
                        "red chili powder",
                        R.drawable.red_chili_powder
                    ),
                    IngredientEntity(
                        "garam masala",
                        R.drawable.garam_masala,
                    ),
                    IngredientEntity(
                        "turmeric",
                        R.drawable.turmeric,
                    ),
                    IngredientEntity(
                        "bell peppers",
                        R.drawable.bell_peppers,
                    ),
                    IngredientEntity(
                        "onions",
                        R.drawable.onions
                    ),
                    IngredientEntity(
                        "lemon juice",
                        R.drawable.lemon_juice,

                        ),
                    IngredientEntity(
                        "skewers",
                        R.drawable.skewers,

                        ),
                )
            ),
            DishEntity(
                name = "Falafel",
                dishImage = R.drawable.falafel,
                ingredientEntityList = listOf(
                    IngredientEntity(
                        "Chickpeas",
                        R.drawable.chickpeas,
                    ),
                    IngredientEntity(
                        "Onion",
                        R.drawable.onions,
                    ),
                    IngredientEntity(
                        "garlic",
                        R.drawable.garlic,
                    ),
                    IngredientEntity(
                        "parsley",
                        R.drawable.parsley,

                        ),
                    IngredientEntity(
                        "cilantro",
                        R.drawable.cilantro,

                        ),

                    IngredientEntity(
                        "cumin",
                        R.drawable.cumin,
                    ),
                    IngredientEntity(
                        "coriander",
                        R.drawable.coriander,
                    ),
                    IngredientEntity(
                        "salt",
                        R.drawable.salt,
                    ),
                    IngredientEntity(
                        "flour",
                        R.drawable.flour,
                    ),

                    IngredientEntity(
                        "Baking powder",
                        R.drawable.baking_powder,
                    ),
                    IngredientEntity(
                        "Olive oil",
                        R.drawable.olive_oil,
                    )
                )
            ),
            DishEntity(
                name = "Ratatouille",
                dishImage = R.drawable.ratatouille,
                ingredientEntityList = listOf(
                    IngredientEntity(
                        "Eggplant",
                        R.drawable.egg_plant,
                    ),
                    IngredientEntity(
                        "Zucchini",
                        R.drawable.zucchini,
                    ),
                    IngredientEntity(
                        "Bell peppers",
                        R.drawable.bell_peppers,
                    ),
                    IngredientEntity(
                        "Tomatoes",
                        R.drawable.tomatoes,
                    ),
                    IngredientEntity(
                        "Onions",
                        R.drawable.onions,
                    ),
                    IngredientEntity(
                        "Olive oil",
                        R.drawable.olive_oil,
                    ),
                    IngredientEntity(
                        "Thyme",
                        R.drawable.thyme,
                    ),
                    IngredientEntity(
                        "Basil",
                        R.drawable.basil,
                    ),
                    IngredientEntity(
                        "Salt",
                        R.drawable.salt,
                    ),
                    IngredientEntity(
                        "Black pepper",
                        R.drawable.black_pepper,
                    )
                )
            ),
            DishEntity(
                name = "Stuffed Peppers",
                dishImage = R.drawable.stuffed_peppers,
                ingredientEntityList = listOf(
                    IngredientEntity(
                        "Bell peppers",
                        R.drawable.bell_peppers,
                    ),
                    IngredientEntity(
                        "Rice",
                        R.drawable.rice,
                    ),
                    IngredientEntity(
                        "Tomatoes",
                        R.drawable.tomatoes
                    ),
                    IngredientEntity(
                        "Onion",
                        R.drawable.onions,
                    ),
                    IngredientEntity(
                        "Garlic",
                        R.drawable.garlic,
                    ),

                    IngredientEntity(
                        "Black beans ",
                        R.drawable.black_beans,
                    ),
                    IngredientEntity(
                        "Corn",
                        R.drawable.corn,
                    ),
                    IngredientEntity(
                        "cheese",
                        R.drawable.cheese,
                    ),
                    IngredientEntity(
                        "herbs",
                        R.drawable.herbs,
                    ),
                    IngredientEntity(
                        "spices",
                        R.drawable.spices,
                    ),
                )
            ),

            DishEntity(
                name = "Caprese Salad",
                dishImage = R.drawable.caprese_salad,
                ingredientEntityList = listOf(
                    IngredientEntity(
                        "Fresh tomatoes",
                        R.drawable.tomatoes,
                    ),
                    IngredientEntity(
                        "mozzarella cheese",
                        R.drawable.cheese,
                    ),
                    IngredientEntity(
                        "fresh basil",
                        R.drawable.basil,
                    ),
                    IngredientEntity(
                        "Olive oil",
                        R.drawable.olive_oil,
                    ),
                    IngredientEntity(
                        "Balsamic vinegar",
                        R.drawable.balsamic_vinegar,
                    ),
                    IngredientEntity(
                        "Salt",
                        R.drawable.salt,
                    ),
                    IngredientEntity(
                        "Black pepper",
                        R.drawable.black_pepper,
                    )
                )
            ),

            DishEntity(
                name = "Chana Masala",
                dishImage = R.drawable.chana_masala,
                ingredientEntityList = listOf(
                    IngredientEntity(
                        "Chickpeas",
                        R.drawable.chickpeas,
                    ),
                    IngredientEntity(
                        "Onion",
                        R.drawable.onions,
                    ),
                    IngredientEntity(
                        "Tomatoes",
                        R.drawable.tomatoes
                    ),
                    IngredientEntity(
                        "Garlic",
                        R.drawable.garlic
                    ),
                    IngredientEntity(
                        "Green chili",
                        R.drawable.green_chili,
                    ),

                    IngredientEntity(
                        "garam masala",
                        R.drawable.garam_masala,
                    ),
                    IngredientEntity(
                        "Coriander powder",
                        R.drawable.coriander_powder,
                    ),
                    IngredientEntity(
                        "cumin",
                        R.drawable.cumin,
                    ),
                    IngredientEntity(
                        "Turmeric",
                        R.drawable.turmeric,
                    ),
                    IngredientEntity(
                        "Fresh cilantro",
                        R.drawable.cilantro,
                    )
                )
            ),
            DishEntity(
                name = "Spanakopita",
                dishImage = R.drawable.spanakopita,
                ingredientEntityList = listOf(
                    IngredientEntity(
                        "Phyllo pastry",
                        R.drawable.phyllo_pastry,
                    ),
                    IngredientEntity(
                        "Spinach",
                        R.drawable.spinach,
                    ),
                    IngredientEntity(
                        "Feta cheese",
                        R.drawable.cheese,
                    ),
                    IngredientEntity(
                        "Onions",
                        R.drawable.onions,
                    ),
                    IngredientEntity(
                        "olive oil",
                        R.drawable.olive_oil,
                    ),
                    IngredientEntity(
                        "Dill",
                        R.drawable.dill,
                    ),
                    IngredientEntity(
                        "nutmeg",
                        R.drawable.nutmeg,
                    ), IngredientEntity(
                        "Salt",
                        R.drawable.salt,
                    ),
                    IngredientEntity(
                        "Pepper",
                        R.drawable.black_pepper,
                    )
                )
            ),
            DishEntity(
                name = "Vegetable Stir-Fry",
                dishImage = R.drawable.stir_fry,
                ingredientEntityList = listOf(
                    IngredientEntity(
                        "Mixed vegetables",
                        R.drawable.vegatables,
                    ),
                    IngredientEntity(
                        "Garlic",
                        R.drawable.garlic,
                    ),
                    IngredientEntity(
                        "Soy sauce",
                        R.drawable.soya_sauce,
                    ),
                    IngredientEntity(
                        "ginger",
                        R.drawable.ginger,
                    ),
                    IngredientEntity(
                        "sesame oil",
                        R.drawable.sesame_oil,
                    ),
                    IngredientEntity(
                        "Green onions",
                        R.drawable.green_onion,
                    ),
                    IngredientEntity(
                        "Tofu",
                        R.drawable.tofu,
                    )
                )
            )
        )

        return DishCollection(dishCollection.map { it.toDomainModel() })
    }
}