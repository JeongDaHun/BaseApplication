package com.example.baseapplication.common.retrofit

import com.example.baseapplication.model.LottoModel
import retrofit2.Call
import retrofit2.http.*

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
    }

    //서비스 요청
    @FormUrlEncoded
    @Headers(JexContentType, JexContentEncoding)
    @POST("/{PATH}")
    fun requestService(@Path("PATH") path: String, @Header("User-Agent") userAgent: String, @Header("Cookie") cookie: String, @FieldMap params: HashMap<String, String>): Call<String>

    //로또 정보 검색
    @Headers(ContentType)
    @GET(LOTTO_INFO_URL)
    fun getLottoInfo(@Query("drwNo") drwNo: Int): Call<LottoModel>
}