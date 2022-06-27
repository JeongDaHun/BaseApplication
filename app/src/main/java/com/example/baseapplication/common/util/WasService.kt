package com.example.baseapplication.common.util

/**
 * WasServiceUrl : WAS SERVICE URL
 *
 * @author Jung.Seung.Jin(950868)
 * @version 1.0.1
 * @since 2020-02-24
 */
enum class WasService(comment: String, private val mServiceId: String) {
    /** APK 다운로드  */
    SMP0030101A02("APK Server version", "smp0030101A02.jct"),
    SMP0030101A03("APK 다운로드", "smp0030101A03.jct"),
    /** 기기 및 회원상태 확인  */
    CMM0010300A01("공통->기능->서비스가입로그인->기기상태확인", "cmm0010300A01.jct");

    val serviceUrl: String
        get() {
            val url = StringBuilder()
            url.append("baseUrl.com/")
            url.append(Const.SLASH)
            url.append(mServiceId)
            return url.toString()
        }

    val serviceId: String
        get() {
            return mServiceId
        }

}
