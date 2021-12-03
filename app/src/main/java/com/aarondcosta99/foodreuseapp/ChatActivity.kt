package com.aarondcosta99.foodreuseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.aarondcosta99.foodreuseapp.adapter.FragmentAdapter
import com.aarondcosta99.foodreuseapp.databinding.ActivityChatBinding
import com.aarondcosta99.foodreuseapp.fragment.RulesFragment
import com.aarondcosta99.foodreuseapp.fragment.UsersFragment
import com.google.android.material.tabs.TabLayoutMediator

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = FragmentAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            when(position){
                0->{
                    tab.text = "Rules"
                }
                1->{
                    tab.text = "Users"
                }
            }
        }.attach()
    }
}