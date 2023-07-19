package com.yourcompany.android.quotes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcompany.android.quotes.data.Quote
import com.yourcompany.android.quotes.data.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesViewModel(
  private val repository: QuotesRepository
) : ViewModel() {

  private val _items = MutableLiveData<List<Quote>>()
  val items: LiveData<List<Quote>> = _items

  var quote = ""
  var personName = ""

  fun insertQuote() {
    val quote = Quote(
      id = 0,
      text = quote,
      author = personName
    )
    viewModelScope.launch(Dispatchers.IO) {
      repository.insert(quote)
    }
  }

  fun getAllQuotes() {
    viewModelScope.launch(Dispatchers.IO) {
      repository.getQuotes().collect { quotes ->
        _items.postValue(quotes)
      }
    }
  }

  fun validateInputs(): Boolean {
    return quote.isNotEmpty() && personName.isNotEmpty()
  }

}