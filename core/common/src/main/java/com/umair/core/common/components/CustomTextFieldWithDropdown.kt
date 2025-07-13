package com.umair.core.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.umair.core.common.R

@Composable
fun CustomTextFieldWithDropdown(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: String = "",
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    isDropdown: Boolean = false,
    dropdownItems: List<String> = emptyList(),
    onDropdownItemSelected: (String) -> Unit = {},
    backgroundColor: Color = Color.Transparent,
    cornerRadius: Dp = 12.dp,
    isPassword: Boolean = false
) {
    var expanded = remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if(!isDropdown) onValueChange(it)
                if (!expanded.value) expanded.value = true
            },
            label = { if (label.isNotEmpty()) Text(label) },
            placeholder = { if (placeholder.isNotEmpty()) Text(placeholder) },
            leadingIcon = leadingIcon?.let { icon ->
                { Icon(imageVector = icon, contentDescription = null) }
            },
            visualTransformation = if(isPassword) {
                if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            } else {
                VisualTransformation.None
            },
            trailingIcon = trailingIcon?.let { icon ->
                {
                    if (isPassword) {
                        val visibilityIcon =
                            if (passwordVisible) R.drawable.ic_visible else R.drawable.ic_visibility_off
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(id = visibilityIcon),
                                contentDescription = "Toggle Password Visibility"
                            )
                        }
                    } else {
                        IconButton(onClick = { if (isDropdown) expanded.value = !expanded.value }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    }
                }
            },
            shape = RoundedCornerShape(cornerRadius),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp)
                .background(backgroundColor, shape = RoundedCornerShape(cornerRadius))
        )

        // Dropdown
        if (isDropdown && expanded.value && dropdownItems.isNotEmpty()) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier
                    .wrapContentWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp,0.dp),
            ) {
                dropdownItems.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            onDropdownItemSelected(item)
                            onValueChange(item)
                            expanded.value = false
                        },
                        text = { Text(text = item) }
                    )
                }
            }
        }
    }
}