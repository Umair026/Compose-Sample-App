package com.umair.feature.quotes.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.umair.core.common.extensions.empty
import com.umair.feature.quotes.dataManager.Quote

@Composable
fun QuoteDetails(quote: Quote?, navController: NavHostController) {
    BackHandler {
        navController.popBackStack()
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(
                Brush.sweepGradient(
                    colors = listOf(
                        Color.White,
                        Color.Gray
                    )
                )
            )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 9.dp),
            modifier = Modifier.padding(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(20.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(Modifier.height(10.dp))
                Text(text = quote?.text ?: String.empty())
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}