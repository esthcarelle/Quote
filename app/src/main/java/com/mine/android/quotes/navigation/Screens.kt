package com.mine.android.quotes.navigation

sealed class Screens(val route: String) {
  object ListScreen : Screens("listScreen")
  object QuoteInputScreen : Screens("quoteInputScreen")
}