package com.umair.core.network.di

import com.umair.core.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        // Set the desired log level.
        // Level.NONE: No logs
        // Level.BASIC: Logs request and response lines.
        // Level.HEADERS: Logs request and response lines and their respective headers.
        // Level.BODY: Logs request and response lines and their respective headers and bodies (if present).
        /*if(BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY) // Or Level.BASIC, Level.HEADERS
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE) // Or Level.BASIC, Level.HEADERS
        }*/
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY) // Or Level.BASIC, Level.HEADERS
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)// Add the logging interceptor
            // You can add other interceptors here (e.g., for auth tokens)
            // .readTimeout(60, TimeUnit.SECONDS) // Optional: Set timeouts
            // .connectTimeout(60, TimeUnit.SECONDS) // Optional: Set timeouts
            .build()

    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }


}