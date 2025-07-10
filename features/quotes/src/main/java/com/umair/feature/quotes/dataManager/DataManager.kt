package com.umair.feature.quotes.dataManager

import androidx.compose.runtime.mutableStateOf

object DataManager {

    var data = emptyArray<Quote>()
    var currentPage = mutableStateOf(Pages.LISTING)
    var currentQuote : Quote? = null
    var isQuotesLoaded = mutableStateOf(false)

    fun loadListOfQuotes() {
            data = arrayOf(
                Quote("The only way to do great work is to love what you do.", "Steve Jobs"),
                Quote("Strive not to be a success, but rather to be of value.", "Albert Einstein"),
                Quote("The mind is everything. What you think you become.", "Buddha"),
                Quote("Your time is limited, so don't waste it living someone else's life.", "Steve Jobs"),
                Quote("The best way to predict the future is to create it.", "Peter Drucker"),
                Quote("Life is what happens when you're busy making other plans.", "John Lennon"),
                Quote("The only impossible journey is the one you never begin.", "Tony Robbins"),
                Quote("Believe you can and you're halfway there.", "Theodore Roosevelt"),
                Quote("Act as if what you do makes a difference. It does.", "William James"),
                Quote("Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill")
            )
            isQuotesLoaded.value = true
    }

    fun switchPages(quote: Quote?) {
        if (currentPage.value == Pages.LISTING) {
            currentQuote = quote
            currentPage.value = Pages.DETAILS
        } else {
            currentPage.value = Pages.LISTING
        }
    }



}

data class Quote(val text: String, val author: String)