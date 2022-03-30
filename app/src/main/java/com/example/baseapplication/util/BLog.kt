package com.example.baseapplication.util

import android.util.Log
import java.lang.Exception
import java.lang.StringBuilder

/**
 * MLog : 로그확인
 *
 * @author Jung.Da.Hun
 * @version 1.0.0
 * @since 2022-03-27
 */
object BLog {
    val TAG = BLog::class.java.simpleName

    //private static final boolean LOG = false;//BuildConfig.DEBUG;
//    private val LOG: Boolean = BuildConfig.DEBUG
    private val LOG: Boolean = false

    /**
     * Log.d에 대응한다.
     */
    fun d() {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.d(
                TAG, getSimpleClassName(st[3].className) + " :: " + generateMessage(
                    st[3], null
                )
            )
        }
    }

    /**
     * Log.d에 대응한다.
     *
     * @param msg 메시지
     */
    fun d(msg: String) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.d(
                TAG, getSimpleClassName(st[3].className) + " :: " + generateMessage(
                    st[3], null
                ) + msg
            )
        }
    }

    /**
     * Log.v에 대응한다.
     *
     * @param msg 메시지
     */
    fun v(msg: String?) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.v(getSimpleClassName(st[3].className), generateMessage(st[3], msg))
        }
    }

    /**
     * Log.w에 대응한다.
     *
     * @param msg 메시지
     */
    fun w(msg: String?) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.w(getSimpleClassName(st[3].className), generateMessage(st[3], msg))
        }
    }

    /**
     * Log.w에 대응한다.
     *
     * @param msg 메시지
     * @param t   Throwable
     */
    fun w(msg: String?, t: Throwable?) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.w(getSimpleClassName(st[3].className), generateMessage(st[3], msg), t)
        }
    }

    /**
     * Log.w에 대응한다.
     *
     * @param msg 메시지
     * @param e   Exception
     */
    fun w(msg: String?, e: Exception) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.w(getSimpleClassName(st[3].className), generateMessage(st[3], msg), e)
            e.message
        }
    }

    /**
     * Log.e에 대응한다.
     *
     * @param msg 메시지
     */
    fun e(msg: String?) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.e(
                TAG, getSimpleClassName(st[3].className) + " :: " + generateMessage(
                    st[3], msg
                )
            )
        }
    }

    /**
     * Log.e에 대응한다.
     *
     * @param msg 메시지
     * @param t   Throwable
     */
    fun e(msg: String?, t: Throwable?) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.e(getSimpleClassName(st[3].className), generateMessage(st[3], msg), t)
        }
    }

    /**
     * Log.e에 대응
     *
     * @param msg 메시지
     * @param e   Exception
     */
    fun e(msg: String?, e: Exception) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.e(getSimpleClassName(st[3].className), generateMessage(st[3], msg), e)
            e.message
        }
    }

    /**
     * Log.e에 대응한다.
     *
     * @param msg 메시지
     * @param e   Exception 메시지
     */
    fun e(msg: String?, e: String) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.e(getSimpleClassName(st[3].className), generateMessage(st[3], msg) + " >> " + e)
        }
    }

    /**
     * Log.e에 대응한다.
     *
     * @param e Exception 메시지
     */
    fun e(e: Exception) {
        if (LOG) {
            Log.e(TAG, e.message!!)
        }
    }

    /**
     * Log.i에 대응한다.
     *
     * @param msg 메시지
     */
    fun i(msg: String) {
        if (LOG) {
            val st = Thread.currentThread().stackTrace
            Log.i(
                TAG, getSimpleClassName(st[3].className) + " :: " + generateMessage(
                    st[3], " $msg"
                )
            )
        }
    }

    fun info(msg: String?) {
        if (LOG) Log.i(TAG, msg!!)
    }

    fun line() {
        if (LOG) Log.i(TAG, "==================================================")
    }

    /**
     * from full class name to simple class name
     *
     * @param className
     * @return
     */
    private fun getSimpleClassName(className: String): String {
        val idx = className.lastIndexOf(".")
        return if (idx >= 0) className.substring(idx + 1) else className
    }

    /**
     * 메세지 포멧으로 문자열 만든다.
     *
     * @param ste 마지막에 호출된 StackTrace
     * @param msg 작성할 로그메시지
     * @return
     */
    private fun generateMessage(ste: StackTraceElement, msg: String?): String {
        val sb = StringBuilder(ste.methodName)
        sb.append("[").append(ste.lineNumber).append("]")
        sb.append("")
        if (msg != null) sb.append(msg)
        return sb.toString()
    }

    /**
     * 로그 메시지에 기타 정보를 포함하여 반환한다.
     *
     * @param msg 메시지
     * @return
     */
    internal fun getFullMsg(msg: String?): String {
        var msg = msg
        if (msg == null) msg = ""
        val stackTraceElement = Thread.currentThread().stackTrace[4]
        var className = stackTraceElement.className
        className = className.substring(className.lastIndexOf(".") + 1)
        val methodName = stackTraceElement.methodName
        msg = "$className.$methodName : $msg"
        return msg
    }
}