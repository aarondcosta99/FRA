package com.aarondcosta99.foodreuseapp.ngo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aarondcosta99.foodreuseapp.R
import com.aarondcosta99.foodreuseapp.databinding.ActivityRotiBankBinding

class RotiBank : AppCompatActivity() {
    private lateinit var binding:ActivityRotiBankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityRotiBankBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rbWebView.settings.javaScriptEnabled = true
        binding.rbWebView.settings.loadWithOverviewMode = true
        binding.rbWebView.settings.useWideViewPort = true
        binding.rbWebView.loadUrl("https://rotibankfoundation.org/")
    }
}