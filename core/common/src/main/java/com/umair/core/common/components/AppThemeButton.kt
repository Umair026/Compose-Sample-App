package com.umair.core.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ButtonType {
    PRIMARY,
    SECONDARY,
    CHIP_PRIMARY,
    CHIP_SECONDARY,
}

@Composable
fun AppThemeButton(
    modifier: Modifier = Modifier,
    label: String,
    type: ButtonType = ButtonType.PRIMARY,
    trailingIcon: ImageVector? = null,
    onClick: () -> Unit,
    horizontalPadding : Dp = 10.dp,
    verticalPadding : Dp = 5.dp
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(horizontalPadding, verticalPadding),
        shape = if(type == ButtonType.CHIP_PRIMARY || type == ButtonType.CHIP_SECONDARY) {
            RoundedCornerShape(36.dp)
        } else RoundedCornerShape(12.dp),
        colors = if (type == ButtonType.SECONDARY || type == ButtonType.CHIP_SECONDARY) {
            ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary
            )
        } else {
            ButtonDefaults.buttonColors()
        },
        border = if (type == ButtonType.SECONDARY || type == ButtonType.CHIP_SECONDARY)
            BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary)
        else
            BorderStroke(0.dp, Color.Transparent)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
            )
            trailingIcon?.let {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "null",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}