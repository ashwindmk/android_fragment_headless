package com.ashwin.android.headlessfragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private const val SUB_TAG = "CounterHeadlessFragment"

class CounterHeadlessFragment : Fragment() {
    companion object {
        fun newInstance() = CounterHeadlessFragment()

        const val TAG = "counter-headless-fragment"
    }

    private val _counterLiveData = MutableLiveData<Int>(0)
    val counterLiveData: LiveData<Int> = _counterLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("${Constant.APP_TAG}: $SUB_TAG: onCreate")
        retainInstance = true
    }

    fun fetch() {
        println("${Constant.APP_TAG}: $SUB_TAG: fetch")
        Handler(Looper.getMainLooper())
            .postDelayed(Runnable {
                println("${Constant.APP_TAG}: $SUB_TAG: old counter = ${_counterLiveData.value}")
                _counterLiveData.value = _counterLiveData.value!! + 1
                println("${Constant.APP_TAG}: $SUB_TAG: new counter = ${_counterLiveData.value}")
            }, 5000L)
    }
}
