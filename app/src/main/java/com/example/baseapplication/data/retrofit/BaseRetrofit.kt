package com.example.baseapplication.data.retrofit

import com.example.baseapplication.BuildConfig
import com.example.baseapplication.data.model.LottoModel
import com.example.baseapplication.data.retrofit.RetrofitHelper.enqueueWithRetry
import com.example.baseapplication.util.BLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * BaseRetrofit
 *
 * @author Jeong.Da.Hun
 * @version 1.0.0
 * @since 2022-03-27
 */

class BaseRetrofit(private val listener: RetrofitListener) {
    private var mRetrofit: Retrofit? = null
    private var mRetrofitService: RetrofitApiService? = null

    companion object {
        private const val baseUrl = " https://www.dhlottery.co.kr/"
        private const val dev_baseUrl = " https://www.dhlottery.co.kr/"
        private const val RETRY_COUNT = 3
    }

    init {
        if (BuildConfig.DEBUG) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(dev_baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            mRetrofitService = mRetrofit?.create(RetrofitApiService::class.java)
        } else {
            mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            mRetrofitService = mRetrofit?.create(RetrofitApiService::class.java)
        }
    }

    interface RetrofitListener {
        fun onComplete(data: Any?, url: String?)
        fun onFail(errCode: Int, url: String?)
        fun onFail(message: String?, url: String?)
    }

    //로또 조회
        fun getLotto(drwNo: Int) {
        val call: Call<LottoModel> = mRetrofitService!!.getLottoInfo(drwNo)
        BLog.d("getMsgCntList :: "+ "URL = " + call.request())
        enqueueWithRetry(call, null, object : Callback<LottoModel> {
            override fun onResponse(call: Call<LottoModel>, response: Response<LottoModel>
            ) {
                if (response.isSuccessful) {
                    listener.onComplete(response.body(), call.request().url().toString())
                } else {
                    listener.onFail(response.code(), call.request().url().toString())
                }
            }

            override fun onFailure(call: Call<LottoModel>, t: Throwable) {
                listener.onFail(t.toString(), call.request().url().toString())
            }
        })
    }
}