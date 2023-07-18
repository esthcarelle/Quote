/*
 * Copyright (c) 2023 Kodeco LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.mine.android.quotes

import com.mine.android.quotes.data.Quote
import com.mine.android.quotes.data.QuotesRepository
import com.mine.android.quotes.ui.viewmodel.QuotesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class QuotesViewModelTest {

  @Before
  fun setup() {
    Dispatchers.setMain(UnconfinedTestDispatcher())
  }

  @Test
  fun validateInputs() {
    val fakeRepository = object : QuotesRepository {
      override suspend fun insert(quote: Quote) {}

      override suspend fun getQuotes(): Flow<List<Quote>> {
        return flowOf()
      }
    }

    val fakeViewModel = QuotesViewModel(fakeRepository)
    fakeViewModel.quote = "Hello world"
    fakeViewModel.personName = "Ray Wenderlich"
    assertTrue(fakeViewModel.validateInputs())

    fakeViewModel.quote = "Hello world"
    fakeViewModel.personName = ""
    assertFalse(fakeViewModel.validateInputs())

    fakeViewModel.quote = ""
    fakeViewModel.personName = "Ray Wenderlich"
    assertFalse(fakeViewModel.validateInputs())

    fakeViewModel.quote = ""
    fakeViewModel.personName = ""
    assertFalse(fakeViewModel.validateInputs())
  }


  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

}