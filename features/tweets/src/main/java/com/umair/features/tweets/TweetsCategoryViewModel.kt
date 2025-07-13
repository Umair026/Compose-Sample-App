package com.umair.features.tweets

import androidx.lifecycle.ViewModel
import com.umair.core.common.models.Tweet
import com.umair.core.common.extensions.empty
import com.umair.core.domain.tweets.TweetsUseCase
import com.umair.core.network.Resource
import com.umair.core.network.Resource.Loading.asResult
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
class TweetsCategoryViewModel @Inject constructor(
    private val tweetsUseCase: TweetsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TweetsUiState())
    val state: StateFlow<TweetsUiState> = _state.asStateFlow()

    init {
        getTweets()
    }

    private fun getTweets() {
        CoroutineScope(Dispatchers.IO).launch {
            tweetsUseCase().asResult().collectLatest { response ->
                when (response) {
                    Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is Resource.Success -> {
                       _state.update {
                           it.copy(
                               isLoading = false,
                               categories = response.data.distinct()
                           )
                       }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = Error(response.errorDescription)
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class UIState {
    data object Loading : UIState()
    data class Success(val data: ArrayList<Tweet>) : UIState()
    data class Error(val message: String) : UIState()
}

data class TweetsUiState(
    val isLoading: Boolean = false,
    val categories: List<String>? = null,
    val tweet: List<Tweet>? = null,
    val error: Error? = null
)

data class Error(
    val errorDescription: String? = String.empty()
)