package com.app.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.presentation.theme.LocalCustomColorPalette
import com.domain.model.Ingredient
import com.app.presentation.R


@Composable
fun IngredientItem(ingredient: Ingredient) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius)))
            .background(
                LocalCustomColorPalette.current.tertiaryColor
            )
            .padding(vertical = dimensionResource(id = R.dimen.padding_5))
    ) {
        Row(
            Modifier
                .padding(dimensionResource(id = R.dimen.padding_8))
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.padding_60))
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_radius))),
                painter = painterResource(id = ingredient.image),
                contentDescription = "ingredient Image",
                contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.medium_margin))
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = ingredient.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewIngredientItem() {
    IngredientItem(ingredient = Ingredient("Panneer", R.drawable.bg_list_item))
}
