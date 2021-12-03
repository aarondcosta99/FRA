package com.aarondcosta99.foodreuseapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.aarondcosta99.foodreuseapp.adapter.ReceiveAdapter
import com.aarondcosta99.foodreuseapp.databinding.ActivityReceiveBinding
import com.aarondcosta99.foodreuseapp.model.Detail
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class ReceiveActivity : AppCompatActivity() {
    private lateinit var detailArrayList: ArrayList<Detail>
    private lateinit var receiveAdapter: ReceiveAdapter
    private var db = Firebase.firestore
    private lateinit var binding: ActivityReceiveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.receiveRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.receiveRecyclerView.setHasFixedSize(true)
        detailArrayList = arrayListOf()
        receiveAdapter = ReceiveAdapter(detailArrayList)
        binding.receiveRecyclerView.adapter = receiveAdapter
        eventChangeListener()
    }

    private fun eventChangeListener() {
        db.collection("donations").orderBy("time",Query.Direction.DESCENDING).addSnapshotListener{snapshot,e ->
            if (e != null)
            {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            for(dc: DocumentChange in snapshot?.documentChanges!!)
            {
                if(dc.type == DocumentChange.Type.ADDED)
                {
                    detailArrayList.add(dc.document.toObject(Detail::class.java))
                }
            }
            receiveAdapter.notifyDataSetChanged()
        }
    }
}