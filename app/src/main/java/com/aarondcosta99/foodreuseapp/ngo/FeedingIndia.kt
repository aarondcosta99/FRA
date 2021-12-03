package com.aarondcosta99.foodreuseapp.ngo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aarondcosta99.foodreuseapp.R
import com.aarondcosta99.foodreuseapp.databinding.ActivityFeedingIndiaBinding

class FeedingIndia : AppCompatActivity() {
    private lateinit var binding:ActivityFeedingIndiaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedingIndiaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fiWebView.settings.javaScriptEnabled = true
        binding.fiWebView.settings.loadWithOverviewMode = true
        binding.fiWebView.settings.useWideViewPort = true
        binding.fiWebView.loadUrl("https://www.feedingindia.org/")
    }
}