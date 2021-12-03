package com.aarondcosta99.foodreuseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.aarondcosta99.foodreuseapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.createAcc.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
        binding.loginBtn.setOnClickListener {
            val userPassword = binding.userPassword.text.toString().trim()
            val userEmail = binding.userEmail.text.toString().trim()
            if(TextUtils.isEmpty(userEmail))
            {
                binding.userEmail.error = "Email is required"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(userPassword))
            {
                binding.userPassword.error = "Password is required"
                return@setOnClickListener
            }
            binding.progressBar.visibility = View.VISIBLE
            FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    Toast.makeText(this,"User Logged In. Redirecting you to Home Page", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                    startActivity(Intent(applicationContext,MainActivity::class.java))
                } else {
                    Toast.makeText(this,"Error!!! "+ it.exception!!.message,Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

}