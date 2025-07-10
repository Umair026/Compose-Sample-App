package com.umair.feature.quotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.umair.feature.quotes.R
import com.umair.feature.quotes.dataManager.DataManager
import com.umair.feature.quotes.dataManager.Pages
import com.umair.feature.quotes.dataManager.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuoteListScreen(onclick: (Quote) -> Unit) {

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            DataManager.loadListOfQuotes()
        }
    }

    if (DataManager.isQuotesLoaded.value) {
        if (DataManager.currentPage.value == Pages.LISTING) {
            QuoteListContent(onclick)
        } else {
            DataManager.currentQuote?.let { QuoteDetails(it) }
        }
    }
}

@Composable
fun QuoteListContent(onclick: (Quote) -> Unit) {
    Column {
        Text(
            text = "Quotes List",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp, 24.dp)
                .fillMaxWidth(1f),
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily(Font(R.font.montserrat_font))
        )
        LazyColumn (
            content = {
                items(DataManager.data) {
                    QuoteListItem(quote = it, onclick)
                }
            }
        )
    }
}
