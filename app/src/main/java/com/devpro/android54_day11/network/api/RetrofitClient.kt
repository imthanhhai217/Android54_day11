package com.devpro.android54_day11.network.api

import com.devpro.android54_day11.constants.ConstantApi
import com.devpro.android54_day11.network.api.request.DummyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    companion object{
        private val instance by lazy{
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG){
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }else{
                loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            Retrofit.Builder().baseUrl(ConstantApi.BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
        }

        val getDummyApi:DummyService by lazy{
            instance.create(DummyService::class.java)
        }
    }
}