package com.umair.gettingstartwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.umair.gettingstartwithcompose.ui.container.AppContainer
import com.umair.gettingstartwithcompose.ui.theme.GettingStartWithComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            GettingStartWithComposeTheme {
                AppContainer()
            }
        }
    }
}

