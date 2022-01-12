package com.aarondcosta99.foodreuseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aarondcosta99.foodreuseapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val db = Firebase.firestore
        db.collection("users").document(userID).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("tag", "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                binding.userName.text = snapshot.getString("userName")
                binding.userEmail.text = snapshot.getString("userEmail")
            } else {
                Log.d("TAG", "Current data: null")
            }
        }
        binding.guide.setOnClickListener{
            startActivity(Intent(applicationContext,GuideActivity::class.java))
        }
        binding.ngo.setOnClickListener {
            startActivity(Intent(applicationContext,NGOActivity::class.java))
        }
        binding.donate.setOnClickListener {
            startActivity(Intent(applicationContext,DonateActivity::class.java))
        }
        binding.receive.setOnClickListener {
            startActivity(Intent(applicationContext,ReceiveActivity::class.java))
        }
        binding.chat.setOnClickListener {
            startActivity(Intent(applicationContext,ChatActivity::class.java))
        }
        binding.logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(applicationContext,LoginActivity::class.java))
            finish()
        }
    }
}