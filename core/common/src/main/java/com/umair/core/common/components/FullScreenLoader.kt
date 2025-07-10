package com.umair.core.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun FullScreenLoader() {
    val angle = produceState(0) {
        while (true) {
            delay(100)
            value = (value + 20) % 360
        }
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Image(
            imageVector = Icons.Default.Refresh, contentDescription = "loader Image",
            modifier = Modifier
                .size(40.dp)
                .rotate(angle.value.toFloat())
        )
        Text(
            text = "Loading..."
        )
    }
}