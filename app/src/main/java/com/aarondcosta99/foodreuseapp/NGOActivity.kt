package com.aarondcosta99.foodreuseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aarondcosta99.foodreuseapp.databinding.ActivityNgoactivityBinding
import com.aarondcosta99.foodreuseapp.ngo.FeedingIndia
import com.aarondcosta99.foodreuseapp.ngo.RotiBank
import com.aarondcosta99.foodreuseapp.ngo.TheRobinHoodArmy

class NGOActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNgoactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNgoactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.trha.setOnClickListener {
            startActivity(Intent(applicationContext,TheRobinHoodArmy::class.java))
        }
        binding.fi.setOnClickListener {
            startActivity(Intent(applicationContext,FeedingIndia::class.java))
        }
        binding.rb.setOnClickListener {
            startActivity(Intent(applicationContext,RotiBank::class.java))
        }
    }
}