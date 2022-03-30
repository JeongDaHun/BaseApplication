package com.example.baseapplication.data.retrofit

import com.example.baseapplication.data.model.LottoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * RetrofitApiService
 *
 * @author Jeong.Da.Hun
 * @version 1.0.0
 * @since 2022-03-27
 */

interface RetrofitApiService {
    //로도 정보 검색
    @GET("/common.do?method=getLottoNumber")
    fun getLottoInfo(@Query("drwNo") drwNo: Int): Call<LottoModel>
}