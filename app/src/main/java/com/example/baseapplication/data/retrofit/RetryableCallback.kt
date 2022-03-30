package com.example.baseapplication.data.retrofit

import com.example.baseapplication.data.retrofit.RetrofitHelper.isCallSuccess
import com.example.baseapplication.util.BLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class RetryableCallback<T>(private val call: Call<T>, totalRetry: Int) : Callback<T> {
    private var totalRetry = 3
    private var retryCount = 0
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!isCallSuccess(response)) {
            if (retryCount++ < totalRetry) {
                retry()
            } else {
                onFinalResponse(call, response)
            }
        } else {
            onFinalResponse(call, response)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        BLog.d("call = " + call + "t = " + t)
        if (retryCount++ < totalRetry) {
            retry()
        } else {
            onFinalFailure(call, t)
        }
    }

    open fun onFinalResponse(call: Call<T>?, response: Response<T>?) {}
    open fun onFinalFailure(call: Call<T>?, t: Throwable?) {}
    private fun retry() {
        //Log.d("RetryableCallback", "retry : " + retryCount);
        call.clone().enqueue(this)
    }

    init {
        this.totalRetry = totalRetry
    }
}