package com.aarondcosta99.foodreuseapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aarondcosta99.foodreuseapp.MessageActivity
import com.aarondcosta99.foodreuseapp.R
import com.aarondcosta99.foodreuseapp.model.Detail
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ReceiveAdapter(options: FirestoreRecyclerOptions<Detail>) : FirestoreRecyclerAdapter<Detail,ReceiveAdapter.ReceiveViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiveViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.receive_item,parent,false)
        return ReceiveViewHolder(itemView)
    }

    fun deleteItem(position: Int) {
        snapshots.getSnapshot(position).reference.delete()
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userName:TextView = itemView.findViewById(R.id.userName)
        val userAddress:TextView = itemView.findViewById(R.id.userAddress)
        val userDetails:TextView = itemView.findViewById(R.id.userDetails)
    }

    override fun onBindViewHolder(holder: ReceiveViewHolder, position: Int, detail: Detail) {
        holder.userName.text = detail.userName
        holder.userAddress.text = detail.userAddress
        holder.userDetails.text = detail.userDetails
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, MessageActivity::class.java)
            intent.putExtra("userName",detail.userID)
            it.context.startActivity(intent)
        }
    }
}