package com.luminay.tvmazeseries.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.constants.EMPTY
import com.luminay.tvmazeseries.R
import com.luminay.tvmazeseries.theme.Blue100
import com.luminay.tvmazeseries.theme.Blue80
import com.luminay.tvmazeseries.theme.Gray80

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBarWithBorder(
    searchTerm: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Blue80,
    isEnabled: Boolean = true,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .scale(scaleY = 0.9F, scaleX = 1F)
            .background(Color.Transparent)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(32.dp),
            ),
    ) {
        TextField(
            value = searchTerm,
            onValueChange = {
                onQueryChange(it)
            },
            enabled = isEnabled,
            modifier = Modifier
                .padding(vertical = 0.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Blue100,
                unfocusedContainerColor = Blue100,
                disabledContainerColor = Gray80,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(32.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    color = Gray80,
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = {
                onSearch()
                keyboardController?.hide()
            }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search,
            ),
        )
    }
}

@Preview
@Composable
private fun PreviewSearchBarWithBorder() {
    SearchBarWithBorder(
        searchTerm = EMPTY,
        onQueryChange = {},
        onSearch = {},
    )
}
