package com.umair.features.tweetsDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.umair.core.domain.tweets.TweetsDetailUseCase
import com.umair.core.network.Resource
import com.umair.core.network.Resource.Loading.asResult
import com.umair.features.tweets.Error
import com.umair.features.tweets.TweetsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetDetailsViewModel @Inject constructor(
    private val tweetsDetailUseCase: TweetsDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detail = MutableStateFlow(TweetsUiState())
    val detail: StateFlow<TweetsUiState> = _detail.asStateFlow()

    init {
        getTweets()
    }

    fun getSelectedCategory() : String {
        return savedStateHandle.get<String>("category").toString() ?: "Android"
    }
    private fun getTweets() {
        CoroutineScope(Dispatchers.IO).launch {
            tweetsDetailUseCase.invoke(getSelectedCategory())
                .asResult()
                .collectLatest { response ->
                    when (response) {
                        is Resource.Error -> _detail.update {
                            it.copy(
                                isLoading = false,
                                error = Error(response.errorDescription)
                            )
                        }
                        Resource.Loading -> {
                            _detail.update {
                                it.copy(
                                    isLoading = true
                                )
                            }
                        }
                        is Resource.Success -> {
                            _detail.update {
                                it.copy(
                                    isLoading = false,
                                    tweet = response.data
                                )
                            }
                        }
                    }
            }
        }
    }

}