package com.app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.presentation.R
import com.app.presentation.theme.LocalCustomColorPalette


@Composable
fun PageIndicator(
    isActive: Boolean
) {
    val color = if (isActive) {
        MaterialTheme.colorScheme.primary
    } else {
        LocalCustomColorPalette.current.darkGrey
    }
    Box(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.padding_4))
            .size(dimensionResource(id = R.dimen.padding_10))
            .background(color = color, shape = CircleShape)
    ) {

    }
}

@Preview
@Composable
fun PreviewPageIndicator() {
    PageIndicator(isActive = true)
}