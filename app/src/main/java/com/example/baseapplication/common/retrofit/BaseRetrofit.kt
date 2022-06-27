package com.example.baseapplication.common.retrofit

import android.webkit.CookieManager
import com.example.baseapplication.BuildConfig
import com.example.baseapplication.model.LottoModel
import com.example.baseapplication.common.retrofit.callback.RetrofitHelper.enqueueWithRetry
import com.example.baseapplication.common.util.BLog
import com.example.baseapplication.common.util.DataUtil
import com.example.baseapplication.common.util.WasService
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

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
        val gson = GsonBuilder().setLenient().create()
        if (BuildConfig.DEBUG) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(dev_baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            mRetrofitService = mRetrofit?.create(RetrofitApiService::class.java)
        } else {
            mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            mRetrofitService = mRetrofit?.create(RetrofitApiService::class.java)
        }
    }

    interface RetrofitListener {
        fun onComplete(data: Any?, url: String?)
        fun onFail(errCode: Int, url: String?)
        fun onFail(message: String?, url: String?)
    }

    private fun saveCookie(response: Response<String>, url: String) {
        val cookie: String = response.headers()["Set-Cookie"].toString()
        if (DataUtil.isNotNull(cookie)) {
            val cookieManager = CookieManager.getInstance()
            cookieManager.setCookie(url, cookie)
            cookieManager.flush()
        }
    }

    private fun enqueue(call: Call<String>, url: String, param: HashMap<String, String>?) {
        BLog.d("call = $call / ${call.request()}")
        enqueueWithRetry(call, null, object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    saveCookie(response, url)
//                    val data: String = JexAESEncrypt.decrypt(response.body())
//                    Logs.printJsonLog(url, Gson().toJson(param).toString(), data)
//                    listener.onComplete(data, url)
                    listener.onComplete(response.body(), call.request().url.toString())
                } else {
                    listener.onFail(response.code(), url)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                listener.onFail(t.toString(), url)
            }
        })
    }

    //서비스 요청
    fun requestService(service: WasService, param: HashMap<String, Any>) {
        val path: String = service.serviceId
        val url: String = service.serviceUrl
        BLog.d("path = $path / url = $url")
        val params : HashMap<String, String> = HashMap()
        for((key, value) in param) {
            params[key] = value.toString()
        }
//        val call: Call<String> = mRetrofitService!!.requestService(path, HttpUtils.mUserAgent ?: "", CookieManager.getInstance().getCookie(url) ?: "", params)
        val call: Call<String> = mRetrofitService!!.requestService(path, "", CookieManager.getInstance().getCookie(url) ?: "", params)
        enqueue(call, url, params)
    }

    //로또 조회
    fun getLotto(drwNo: Int) {
        val call: Call<LottoModel> = mRetrofitService!!.getLottoInfo(drwNo=drwNo)
        BLog.d("getMsgCntList :: "+ "URL = " + call.request())
        DataUtil.checkNull("dd")
        enqueueWithRetry(call, null, object : Callback<LottoModel> {
            override fun onResponse(call: Call<LottoModel>, response: Response<LottoModel>
            ) {
                if (response.isSuccessful) {
                    listener.onComplete(response.body(), call.request().url.toString())
                } else {
                    listener.onFail(response.code(), call.request().url.toString())
                }
            }

            override fun onFailure(call: Call<LottoModel>, t: Throwable) {
                listener.onFail(t.toString(), call.request().url.toString())
            }
        })
    }
}