package com.example.baseapplication

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.example.baseapplication.data.model.LottoModel
import com.example.baseapplication.data.retrofit.BaseRetrofit
import com.example.baseapplication.util.BLog

/**
 * LottoViewModel
 *
 * @author Jeong.Da.Hun
 * @version 1.0.0
 * @since 2022-03-27
 */

class LottoViewModel : BaseObservable() {

    //View에 표현 될, 로또 당첨 정보 문자열
    val lottoInfo = ObservableField<String>()

    /**
     * 로또 정보 조회
     * @param drwNo 회차
     */
    fun getLottoInfo(drwNo: Int) {
//        LottoRepository.getLottoInfo(drwNo, object : LottoRepository.GetDataCallback<LottoEntity> {
//            override fun onSuccess(data: LottoEntity?) {
//                data?.let {
//                    val results = "${it.drwNo}회차 당첨번호 : " +
//                            "${it.drwtNo1}, ${it.drwtNo2}, ${it.drwtNo3}, ${it.drwtNo4}, ${it.drwtNo5}, ${it.drwtNo6} + ${it.bnusNo}"
//                    lottoInfo.set(results)
//                }
//            }
//
//            override fun onFailure(throwable: Throwable) {
//                throwable.printStackTrace()
//            }
//        })
        BLog.d("로또 조회")

        val retrofit = BaseRetrofit(object : BaseRetrofit.RetrofitListener {
            override fun onComplete(data: Any?, url: String?) {
                data?.let {
                    val lottoModel: LottoModel = data as LottoModel
                    val results = "${lottoModel.drwNo}회차 당첨번호 : " +
                            "${lottoModel.drwtNo1}, ${lottoModel.drwtNo2}, ${lottoModel.drwtNo3}, ${lottoModel.drwtNo4}, ${lottoModel.drwtNo5}, ${lottoModel.drwtNo6} + ${lottoModel.bnusNo}"
                    lottoInfo.set(results)
                }
            }

            override fun onFail(errCode: Int, url: String?) {
            }

            override fun onFail(message: String?, url: String?) {
                BLog.d("message = " + message + " url = " + url)
            }
        })

        retrofit.getLotto(drwNo)

    }
}