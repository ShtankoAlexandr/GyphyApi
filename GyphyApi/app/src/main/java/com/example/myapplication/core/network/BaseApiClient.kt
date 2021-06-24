package com.example.myapplication.core.network

import com.example.myapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseApiClient<T>(private val classT: Class<T>) {

    companion object{
        private const val BASE_URL = "http://api.giphy.com"
        private const val TIMEOUT_LIMIT_IN_MILLIS = 30_000L
    }

    open fun getApiClient(): T {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val retrofitBuilder = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(buildOkHttpClient())
            addConverterFactory(GsonConverterFactory.create())
        }


        val retrofit = retrofitBuilder.build()
        return retrofit.create(classT)
    }
    private fun buildOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            requestBuilder.addHeader("api_key", BuildConfig.GIPHY_API_KEY)
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            okHttpClient.connectTimeout(TIMEOUT_LIMIT_IN_MILLIS, TimeUnit.MILLISECONDS)
            okHttpClient.readTimeout(TIMEOUT_LIMIT_IN_MILLIS, TimeUnit.MILLISECONDS)
            okHttpClient.writeTimeout(TIMEOUT_LIMIT_IN_MILLIS, TimeUnit.MILLISECONDS)
            okHttpClient.addInterceptor(loggingInterceptor)
        }
        return okHttpClient.build()
    }
}