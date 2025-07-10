package com.umair.core.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppThemeButton(modifier: Modifier = Modifier, label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(24.dp, 1.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}