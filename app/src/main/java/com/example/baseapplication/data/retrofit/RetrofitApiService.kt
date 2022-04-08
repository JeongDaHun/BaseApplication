package com.example.baseapplication.data.retrofit

import com.example.baseapplication.data.model.LottoModel
import retrofit2.Call
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

interface RetrofitApiService {
    companion object {
        const val JexContentType = "Content-Type: application/x-www-form-urlencoded;jex-encrypt=true"
        const val JexContentEncoding= "Content-encoding: AES256"
        const val ContentType = "Content-Type: application/json;charset=utf-8"

        const val LOTTO_INFO_URL = "/common.do?method=getLottoNumber"
        const val DEVICE_INFO_URL = "/cmm0010300A01.jct"
        const val MAIN_ACCOUNT_INFO_URL = "/mai0010100A02.jct"
    }

    //로도 정보 검색
    @Headers(ContentType)
    @GET(LOTTO_INFO_URL)
    fun getLottoInfo(@Query("drwNo") drwNo: Int): Call<LottoModel>
}