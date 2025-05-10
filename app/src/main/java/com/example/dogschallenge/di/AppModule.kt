package com.example.dogschallenge.di

import com.example.dogschallenge.BuildConfig
import com.example.dogschallenge.core.coroutines.CoroutinesDispatchers
import com.example.dogschallenge.data.datasource.remote.retrofit.DogsServiceRetrofit
import com.google.gson.GsonBuilder
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
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutinesDispatchers() = CoroutinesDispatchers()

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideDogsServiceRetrofit(retrofit: Retrofit): DogsServiceRetrofit =
        retrofit.create(DogsServiceRetrofit::class.java)
}
