package com.umair.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class Resource<out R>{
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(
        val errorDescription: String
    ) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()

    fun <T> Flow<T>.asResult(): Flow<Resource<T>> {
        return this.map<T, Resource<T>> {
            Success(it)
        }.onStart {
            emit(Loading)
        }.catch {
            emit(
                Error(
                    errorDescription = it.message ?: ""
                )
            )
        }
    }
}