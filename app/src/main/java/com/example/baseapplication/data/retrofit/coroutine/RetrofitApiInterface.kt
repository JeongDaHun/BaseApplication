package com.example.baseapplication.data.retrofit.coroutine

import com.example.baseapplication.data.model.LottoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * RetrofitApiService
 *
 * @author Jeong.Da.Hun
 * @version 1.0.0
 * @since 2022-03-27
 */

interface RetrofitApiInterface {
    companion object {
        const val AES256ContentEncoding= "Content-encoding: AES256"
        const val ContentType = "Content-Type: application/json;charset=utf-8"

        const val LOTTO_INFO_URL = "/common.do?method=getLottoNumber"
        const val LOGIN_URL = "/common.do?method=getLottoNumber"
    }

    //로또 정보 검색
    @Headers(ContentType)
    @GET(LOTTO_INFO_URL)
    suspend fun getLottoInfo(@Query("drwNo") drwNo: Int): Response<LottoModel>

    @Headers(ContentType)
    @GET(LOGIN_URL)
    suspend fun requestLogin(@Query("id") id: String): Response<String>
}