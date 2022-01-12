package com.aarondcosta99.foodreuseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.aarondcosta99.foodreuseapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(FirebaseAuth.getInstance().currentUser != null)
        {
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }
        binding.registerBtn.setOnClickListener {
            val userPassword = binding.userPassword.text.toString().trim()
            val userEmail = binding.userEmail.text.toString().trim()
            val userName = binding.userName.text.toString()
            if(TextUtils.isEmpty(userName))
            {
                binding.userName.error = "Full name is required"
                return@setOnClickListener
            }
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
            if(userPassword.length<6)
            {
                binding.userPassword.error = "Password is greater than 6 characters"
                return@setOnClickListener
            }
            binding.progressBar.visibility = View.VISIBLE
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener { itt ->
                if(itt.isSuccessful)
                {
                    val db = Firebase.firestore
                    val userID = FirebaseAuth.getInstance().currentUser!!.uid
                    val user = hashMapOf(
                        "userName" to userName,
                        "userEmail" to userEmail,
                        "userID" to userID
                    )
                    db.collection("users").document(userID).set(user).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            Toast.makeText(this,"User Created. Redirecting you to Home Page",Toast.LENGTH_SHORT).show()
                            binding.progressBar.visibility = View.GONE
                            startActivity(Intent(applicationContext,MainActivity::class.java))
                        } else {
                            Toast.makeText(this,"Error!!! "+ it.exception!!.message,Toast.LENGTH_SHORT).show()
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                } else {
                    Toast.makeText(this,"Error!!! "+ itt.exception!!.message,Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
        binding.loginAcc.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }
    }
}