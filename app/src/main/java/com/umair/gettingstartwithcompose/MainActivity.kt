package com.umair.gettingstartwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.umair.gettingstartwithcompose.ui.components.ChangeTheme
import com.umair.core.common.theme.GettingStartWithComposeTheme
import com.umair.gettingstartwithcompose.ui.container.AppContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            GettingStartWithComposeTheme {
                AppContainer()
//                ChangeTheme()
            }
        }
    }
}

