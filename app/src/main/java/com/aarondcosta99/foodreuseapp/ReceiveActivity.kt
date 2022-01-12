package com.aarondcosta99.foodreuseapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aarondcosta99.foodreuseapp.adapter.ReceiveAdapter
import com.aarondcosta99.foodreuseapp.databinding.ActivityReceiveBinding
import com.aarondcosta99.foodreuseapp.model.Detail
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

import com.google.android.gms.tasks.OnFailureListener

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth

class ReceiveActivity : AppCompatActivity() {

    private lateinit var receiveAdapter: ReceiveAdapter
    private var db = Firebase.firestore
    private lateinit var binding: ActivityReceiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        eventChangeListener()
    }

    private fun eventChangeListener() {
        val query:Query = db.collection("donations").orderBy("time",Query.Direction.DESCENDING)
        val options:FirestoreRecyclerOptions<Detail> = FirestoreRecyclerOptions.Builder<Detail>().setQuery(query,Detail::class.java).build()

        receiveAdapter = ReceiveAdapter(options)
        receiveAdapter.startListening()
        binding.receiveRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.receiveRecyclerView.adapter = receiveAdapter

        val callback:ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                if(receiveAdapter.snapshots[viewHolder.adapterPosition].userID == FirebaseAuth.getInstance().currentUser?.uid){
                    //do nothing
                } else {
                    return 0
                }
                return super.getMovementFlags(recyclerView, viewHolder)
            }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    receiveAdapter.deleteItem(viewHolder.adapterPosition)
                }
            }
            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(binding.receiveRecyclerView)
        }
    override fun onStart() {
        super.onStart()
        receiveAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        receiveAdapter.stopListening()
    }
}
