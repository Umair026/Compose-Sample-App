package com.umair.gettingstartwithcompose.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.umair.core.common.components.AppContent
import com.umair.core.common.components.AppThemeButton
import com.umair.core.common.components.AppThemeChip
import com.umair.core.common.components.ButtonType
import com.umair.core.common.components.CustomAppBarContent
import com.umair.core.common.components.CustomTextFieldWithDropdown
import com.umair.core.common.components.SwitchWithText
import com.umair.core.common.theme.GettingStartWithComposeTheme
import com.umair.core.common.theme.Pink40
import com.umair.core.common.theme.Purple40

@Composable
fun UIComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    data class Model(val label: String,val onClick: () -> Unit,val color: Color)
    val list = listOf<Model>(
        Model(label = "LoginApp", color = Purple40, onClick = {
            navController.navigate("LoginApp")
        }),
        Model(label = "TweetsApp", color = Pink40, onClick = {
            navController.navigate("TweetsApp")
        }),
        Model(label = "ChangeTheme",color = Pink40, onClick = {
        navController.navigate("ChangeTheme")
    }),
        Model(label = "QuotesApp",color = Purple40, onClick = {
        navController.navigate("QuotesApp")
    })
    )
    AppContent(
        toolBar = CustomAppBarContent(
            pageTitle = "Sample app",
            backAction = { },
            favouriteAction = true,
            navigationIcon = Icons.Default.Home
        ),
        content = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier.fillMaxSize(1f)
                    .padding(8.dp)
            ) {
                itemsIndexed(list) { _, item ->
                    CardView(color = item.color, label = item.label, onClick = item.onClick)
                }
            }

        }
    )
}

@Composable
fun CardView(color: Color, label : String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(5.dp, 5.dp, 30.dp, 5.dp),
        modifier = Modifier.padding(10.dp)
            .size(150.dp, 100.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
    ) {
        Box (
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = label,
                modifier = Modifier.padding(10.dp),
            )
        }
    }
}

@Composable
fun ChangeTheme(modifier: Modifier = Modifier) {
    var theme = remember { mutableStateOf(false) }
    GettingStartWithComposeTheme(darkTheme = theme.value) {
        val verticalScroll = rememberScrollState()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(1f)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(verticalScroll),
        ) {
            var isChecked by remember { mutableStateOf(false) }
            SwitchWithText(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    theme.value = !theme.value
                }
            )
            Text(
                text = "Hello World",
                color = MaterialTheme.colorScheme.primary

            )
            TextFieldComposable()
            ButtonComposable(theme)
            ChipsComposable()
            SwitchComposable()

        }
    }
}

@Composable
fun SwitchComposable() {
    val scrollState = rememberScrollState()
    Box {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState),
        ) {
            val state = remember { mutableStateOf(false) }
            Switch(
                modifier = Modifier.padding(5.dp),
                checked = state.value,
                onCheckedChange = {
                    state.value = it
                    Log.d("TAG", "SwitchComposable: $it")
                }
            )

            Switch(
                modifier = Modifier.padding(5.dp),
                checked = state.value,
                onCheckedChange = {
                    state.value = it
                    Log.d("TAG", "SwitchComposable: $it")
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                    uncheckedIconColor = Color.White,
                    checkedIconColor = Color.White
                ),
                thumbContent = {
                    if (state.value) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                }
            )

            var isChecked by remember { mutableStateOf(false) }
            SwitchWithText(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
        }
    }

}

@Composable
fun ChipsComposable() {
    val scrollState = rememberScrollState()
    Box {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            AppThemeChip(
                text = "Filter",
                onChipClick = { /* Handle click */ }
            )
            AppThemeChip(
                text = "Filter",
                leadingIcon = Icons.Filled.AccountCircle,
                onLeadingIconClick = { /* Handle click */ }
            )
            AppThemeChip(
                text = "Filter",
                trailingIcon = Icons.Filled.Close,
                onTrailingIconClick = { /* Handle click */ }
            )
            AppThemeChip(
                text = "Filter",
                leadingIcon = Icons.Filled.AccountCircle,
                trailingIcon = Icons.Filled.Close,
                onTrailingIconClick = { /* Handle click */ }
            )
        }

        if (scrollState.value != scrollState.maxValue) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "null",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterEnd)
            )
        }
        if (scrollState.value > 10) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "null",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
}

@Composable
fun ButtonComposable(theme: MutableState<Boolean>) {
    AppThemeButton(
        modifier = Modifier.fillMaxWidth(),
        label = "Change theme",
        onClick = {
            theme.value = !theme.value
        }
    )

    AppThemeButton(
        modifier = Modifier.fillMaxWidth(),
        type = ButtonType.SECONDARY,
        label = "Change theme",
        onClick = {
            //theme.value = !theme.value
        }
    )

    val scrollState = rememberScrollState()
    Box {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            AppThemeButton(
                modifier = Modifier.wrapContentWidth(),
                type = ButtonType.CHIP_PRIMARY,
                label = "Add",
                onClick = {
                    //theme.value = !theme.value
                },
                trailingIcon = Icons.Default.Add,
                horizontalPadding = 5.dp
            )
            AppThemeButton(
                modifier = Modifier.wrapContentWidth(),
                type = ButtonType.CHIP_SECONDARY,
                label = "Add",
                onClick = {
                    //theme.value = !theme.value
                },
                trailingIcon = Icons.Default.Check,
                horizontalPadding = 5.dp
            )
            AppThemeButton(
                modifier = Modifier.wrapContentWidth(),
                type = ButtonType.CHIP_PRIMARY,
                label = "Add",
                onClick = {
                    //theme.value = !theme.value
                },
                trailingIcon = Icons.Default.Add,
                horizontalPadding = 5.dp
            )
            AppThemeButton(
                modifier = Modifier.wrapContentWidth(),
                type = ButtonType.CHIP_SECONDARY,
                label = "Add",
                onClick = {
                    //theme.value = !theme.value
                },
                trailingIcon = Icons.Default.Check,
                horizontalPadding = 5.dp
            )
            AppThemeButton(
                modifier = Modifier.wrapContentWidth(),
                type = ButtonType.CHIP_PRIMARY,
                label = "Add",
                onClick = {
                    //theme.value = !theme.value
                },
                trailingIcon = Icons.Default.LocationOn,
                horizontalPadding = 5.dp
            )
            AppThemeButton(
                modifier = Modifier.wrapContentWidth(),
                type = ButtonType.CHIP_SECONDARY,
                label = "Add",
                onClick = {
                    //theme.value = !theme.value
                },
                trailingIcon = Icons.Default.Home,
                horizontalPadding = 5.dp
            )
        }

        if (scrollState.value != scrollState.maxValue) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "null",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterEnd)
            )
        }
        if (scrollState.value > 10) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "null",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
}

@Composable
fun TextFieldComposable() {
    val stateName = remember { mutableStateOf("") }
    val stateEmail = remember { mutableStateOf("") }
    val statePassword = remember { mutableStateOf("") }
    val stateCountry = remember { mutableStateOf("") }
    CustomTextFieldWithDropdown(
        value = stateCountry.value,
        onValueChange = {
            stateCountry.value = it
        },
        label = "County",
        placeholder = "Select country",
        leadingIcon = Icons.Default.LocationOn,
        trailingIcon = Icons.Default.KeyboardArrowDown,
        isDropdown = true,
        dropdownItems = listOf("Pakistan", "India", "USA"),
        onDropdownItemSelected = {
            stateCountry.value = it
        }
    )
    CustomTextFieldWithDropdown(
        value = stateName.value,
        onValueChange = {
            stateName.value = it
        },
        label = "Name",
        placeholder = "Enter user name",
        leadingIcon = Icons.Default.AccountCircle,
        trailingIcon = Icons.Default.CheckCircle.takeIf { stateName.value.isNotEmpty() && stateName.value.count() >= 5 },

        )
    CustomTextFieldWithDropdown(
        value = stateEmail.value,
        onValueChange = {
            stateEmail.value = it
        },
        label = "Email",
        placeholder = "Enter email address",
        leadingIcon = Icons.Default.Email,
        trailingIcon = Icons.Default.Check.takeIf { stateEmail.value.isNotEmpty() && stateEmail.value.count() >= 5 },

        )
    CustomTextFieldWithDropdown(
        value = statePassword.value,
        onValueChange = {
            statePassword.value = it
        },
        label = "Password",
        placeholder = "Enter password",
        leadingIcon = Icons.Default.Lock,
        trailingIcon = Icons.Default.Check,
        isPassword = true
    )
}
