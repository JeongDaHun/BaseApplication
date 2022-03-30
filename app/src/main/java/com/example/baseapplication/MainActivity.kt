package com.example.baseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.baseapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.run {
            viewmodel = LottoViewModel()
            drwNo = 896
        }
//        setContentView(R.layout.activity_main)
//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.run {
//            viewmodel = LottoViewModel() //뷰모델 할당
//            drwNo = 896 //조회 할, 로또 회차
//        }
    }
}