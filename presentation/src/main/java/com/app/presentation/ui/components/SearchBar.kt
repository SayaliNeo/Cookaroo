package com.app.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.app.presentation.R
import com.app.presentation.theme.LocalCustomColorPalette

@Composable
fun SearchBar(
    queryState: String,
    onSearchTriggered: (String) -> Unit
) {
    val keyboardManager = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .background(LocalCustomColorPalette.current.screenBackground)
            .padding(vertical = dimensionResource(id = R.dimen.padding_12))
    ) {
        OutlinedTextField(
            value = queryState,
            textStyle = MaterialTheme.typography.titleMedium,
            onValueChange = { newText ->
                onSearchTriggered(newText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_4)))
                .height(dimensionResource(id = R.dimen.padding_50)),
            placeholder = {
                Text(
                    text = "Search...",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal,
                        color = LocalCustomColorPalette.current.darkGrey,
                    ),
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = LocalCustomColorPalette.current.darkGrey
                )
            },
            trailingIcon = {
                if (queryState.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Input",
                        modifier = Modifier.clickable {
                            onSearchTriggered("")
                            keyboardManager?.hide()
                        },
                        tint = LocalCustomColorPalette.current.darkGrey
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardManager?.hide()
                    onSearchTriggered(queryState.lowercase().trim())
                }
            ),
            maxLines = 1,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = LocalCustomColorPalette.current.accentColor
            )
        )
    }
}

@Preview
@Composable
fun PreviewSearchBar() {
    SearchBar(
        queryState = "cheese",
        onSearchTriggered = {}
    )
}