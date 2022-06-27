package com.example.baseapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.baseapplication.viewModel.LottoViewModel
import com.example.baseapplication.R
import com.example.baseapplication.databinding.ActivityMainBinding

/**
 * MainActivity
 *
 * @author Jeong.Da.Hun
 * @version 1.0.0
 * @since 2022-03-27
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.run {
            viewmodel = LottoViewModel()
            drwNo = 1008
        }
    }
}