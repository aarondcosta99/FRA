package com.aarondcosta99.foodreuseapp.ngo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aarondcosta99.foodreuseapp.R
import com.aarondcosta99.foodreuseapp.databinding.ActivityTheRobinHoodArmyBinding

class TheRobinHoodArmy : AppCompatActivity() {
    private lateinit var binding:ActivityTheRobinHoodArmyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTheRobinHoodArmyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.trhaWebView.settings.javaScriptEnabled = true
        binding.trhaWebView.settings.loadWithOverviewMode = true
        binding.trhaWebView.settings.useWideViewPort = true
        binding.trhaWebView.loadUrl("https://robinhoodarmy.com/")
    }
}