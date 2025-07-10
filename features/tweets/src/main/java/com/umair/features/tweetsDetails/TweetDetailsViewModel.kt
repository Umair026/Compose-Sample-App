package com.umair.features.tweetsDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.umair.core.common.models.Tweet
import com.umair.core.domain.tweets.TweetsDetailUseCase
import com.umair.features.tweets.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetDetailsViewModel @Inject constructor(
    private val tweetsDetailUseCase: TweetsDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detail = MutableStateFlow<UIState>(UIState.Success(arrayListOf<Tweet>()))
    val detail: StateFlow<UIState> = _detail

    init {
        getTweets()
    }

    fun getSelectedCategory() : String {
        return savedStateHandle.get<String>("category").toString() ?: "Android"
    }
    private fun getTweets() {
        CoroutineScope(Dispatchers.IO).launch {
            tweetsDetailUseCase.invoke(getSelectedCategory()).collectLatest {
                //handle errors and loading here
                _detail.value = UIState.Success(it)
            }
        }
    }

}