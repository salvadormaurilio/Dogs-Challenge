package com.example.dogschallenge.di

import com.example.dogschallenge.BuildConfig
import com.example.dogschallenge.core.coroutines.CoroutinesDispatchers
import com.example.dogschallenge.data.DogsRepository
import com.example.dogschallenge.data.DogsRepositoryImpl
import com.example.dogschallenge.data.datasource.remote.DogsRemoteDataSource
import com.example.dogschallenge.data.datasource.remote.DogsRemoteDataSourceImpl
import com.example.dogschallenge.data.datasource.remote.retrofit.DogsServiceRetrofit
import com.example.dogschallenge.domain.GetDogsUseCase
import com.example.dogschallenge.domain.GetDogsUseCaseImpl
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

    @Provides
    fun provideDogsRemoteDataSource(dogsServiceRetrofit: DogsServiceRetrofit): DogsRemoteDataSource =
        DogsRemoteDataSourceImpl(dogsServiceRetrofit)

    @Provides
    fun provideDogsRepository(dogsRemoteDataSource: DogsRemoteDataSource): DogsRepository =
        DogsRepositoryImpl(dogsRemoteDataSource)

    @Provides
    fun provideGetDogsUseCase(dogsRepository: DogsRepository): GetDogsUseCase =
        GetDogsUseCaseImpl(dogsRepository)
}
