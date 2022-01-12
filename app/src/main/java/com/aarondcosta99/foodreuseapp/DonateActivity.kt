package com.aarondcosta99.foodreuseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.aarondcosta99.foodreuseapp.databinding.ActivityDonateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DonateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDonateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        db.collection("users").document(userID).addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                binding.userName.text = snapshot.getString("userName").toString()
            }
        }
        binding.submitBtn.setOnClickListener {
            val userName = binding.userName.text.toString()
            val userAddress = binding.userAddress.text.toString()
            val userDetails = binding.userDetails.text.toString()
            if(TextUtils.isEmpty(userAddress))
            {
                binding.userAddress.error = "Address is required"
            }
            if(TextUtils.isEmpty(userDetails))
            {
                binding.userDetails.error = "Details is required"
            }
            else{
                val donation = hashMapOf(
                    "userName" to userName,
                    "userAddress" to userAddress,
                    "userDetails" to userDetails,
                    "userID" to userID,
                    "time" to FieldValue.serverTimestamp()
                )
                db.collection("donations").document().set(donation, SetOptions.merge()).addOnSuccessListener {
                    Toast.makeText(this,"Your submission has been successfully saved into the datbase", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}