package com.umair.feature.quotes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.umair.feature.quotes.R
import com.umair.feature.quotes.dataManager.Quote

@Composable
fun QuoteListItem(quote: Quote, onclick: (Quote) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(10.dp)
            .clickable {
                onclick(quote)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Row (
            Modifier.padding(16.dp)
        ) {
            Image(
                imageVector = Icons.Filled.Check,
                contentDescription = "",
                alignment = Alignment.TopStart,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(30.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(
                    text = quote.text,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = quote.author,
                    fontFamily = FontFamily(Font(R.font.montserrat_font))
                )
            }


        }
    }
}