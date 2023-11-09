package com.luminay.tvmazeseries.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun BottomSheet(
    content: @Composable () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        containerColor = Color.Transparent,
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = {
            Spacer(Modifier.height(0.dp))
        },
        modifier = modifier
            .fillMaxHeight()
            .padding(bottom = 0.dp),
    ) {
        content()
    }
}
