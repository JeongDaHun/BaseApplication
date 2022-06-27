package com.example.baseapplication

import androidx.lifecycle.ViewModel
import com.example.baseapplication.util.BLog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

open class DisposableViewModel: ViewModel() {

    /**
     * CoroutineScope 내부 Exception 처리 Handler
     */
    protected val coroutineExceptionHanlder = CoroutineExceptionHandler { coroutineContext, throwable ->
        BLog.d("throwable = $throwable")
        throwable.printStackTrace()
    }

    /**
     * Dispatchers 선언 (Normal Dispatchers + CoroutineExceptionHandler)
     */
    protected val ioDispatchers = Dispatchers.IO + coroutineExceptionHanlder
    protected val uiDispatchers = Dispatchers.Main + coroutineExceptionHanlder

//    /**
//     * Clear Rx when called onCleared
//     */
//    private val compositeDisposable = CompositeDisposable()
//
//    fun addDisposable(disposable: Disposable) {
//        compositeDisposable.add(disposable)
//    }

    override fun onCleared() {
//        compositeDisposable.clear()
        super.onCleared()
    }
}