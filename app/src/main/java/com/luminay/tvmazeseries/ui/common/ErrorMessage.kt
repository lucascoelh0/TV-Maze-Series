package com.luminay.tvmazeseries.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luminay.tvmazeseries.R
import com.luminay.tvmazeseries.theme.Purple80

@Composable
fun ErrorMessage(
    onRetry: () -> Unit,
    message: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            color = Color.White,
        )

        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple80,
            ),
        ) {
            Text(
                text = stringResource(id = R.string.try_again),
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun ErrorMessagePreview() {
    ErrorMessage(
        onRetry = {},
        stringResource(id = R.string.loading_error),
    )
}
