package com.app.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.presentation.R
import com.app.presentation.model.DishAnalysisDetails
import com.app.presentation.theme.LocalCustomColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishAnalysisBottomSheet(
    details: DishAnalysisDetails,
    onDismiss: () -> Unit,
    sheetState: SheetState,
) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = LocalCustomColorPalette.current.screenBackground
    ) {
        Column(
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.large_margin),
                start = dimensionResource(id = R.dimen.medium_horizontal_margin)
            )
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.number_of_ingredients),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_margin)))
                Text(
                    text = details.ingredientCount.toString(),
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Column(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.small_margin))) {
                details.topCharacters.forEach { (char, count) ->
                    Row(Modifier.padding(top = dimensionResource(id = R.dimen.small_margin))) {
                        Text(
                            text = stringResource(id = R.string.bullet).plus(
                                char.toString().plus(stringResource(id = R.string.arrow))
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.extra_small_margin)))
                        Text(
                            text = count.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewDishAnalysisBottomSheet() {
    DishAnalysisBottomSheet(
        details = DishAnalysisDetails(
            ingredientCount = 5,
            topCharacters = listOf(Pair('t', 10),Pair('r',4),Pair('2', 2),)
        ),
        onDismiss = {},
        sheetState = rememberModalBottomSheetState()
    )
}