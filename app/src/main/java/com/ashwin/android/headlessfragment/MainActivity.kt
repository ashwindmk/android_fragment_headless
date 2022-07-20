package com.ashwin.android.headlessfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

private const val SUB_TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("${Constant.APP_TAG}: $SUB_TAG: onCreate")

        var counterFragment = supportFragmentManager.findFragmentByTag(CounterHeadlessFragment.TAG) as CounterHeadlessFragment?
        if (counterFragment == null) {
            println("${Constant.APP_TAG}: $SUB_TAG: creating new counterFragment")
            counterFragment = CounterHeadlessFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(counterFragment, CounterHeadlessFragment.TAG)
                .commit()
        } else {
            println("${Constant.APP_TAG}: $SUB_TAG: reusing old counterFragment")
        }
        println("${Constant.APP_TAG}: $SUB_TAG: counterFragment = $counterFragment")

        val textView = findViewById<TextView>(R.id.textView)
        counterFragment.counterLiveData.observe(this, {
            textView.text = it.toString()
        })

        val fetchButton = findViewById<Button>(R.id.fetch_button)
        fetchButton.setOnClickListener {
            counterFragment.fetch()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("${Constant.APP_TAG}: $SUB_TAG: onDestroy")
    }
}
