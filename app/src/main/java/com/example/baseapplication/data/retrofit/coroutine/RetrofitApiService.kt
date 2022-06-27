package com.example.baseapplication.data.retrofit.coroutine

import com.example.baseapplication.BuildConfig
import com.example.baseapplication.data.retrofit.coroutine.RetrofitApiInterface
import com.google.gson.GsonBuilder
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieManager


/**
 * BaseRetrofit
 *
 * @author Jeong.Da.Hun
 * @version 1.0.0
 * @since 2022-03-27
 */

object RetrofitApiService {

    private const val baseUrl = " https://www.dhlottery.co.kr/"
    private const val dev_baseUrl = " https://www.dhlottery.co.kr/"
    val client = OkHttpClient.Builder().cookieJar(JavaNetCookieJar(CookieManager())).build()

    fun getBaseService(): RetrofitApiInterface {
        val gson = GsonBuilder().setLenient().create()
        return if (BuildConfig.DEBUG) {
            Retrofit.Builder()
                .baseUrl(dev_baseUrl)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RetrofitApiInterface::class.java)
        } else {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RetrofitApiInterface::class.java)
        }

    }
}