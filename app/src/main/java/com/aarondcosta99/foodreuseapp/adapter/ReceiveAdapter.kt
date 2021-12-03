package com.aarondcosta99.foodreuseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aarondcosta99.foodreuseapp.R
import com.aarondcosta99.foodreuseapp.model.Detail

class ReceiveAdapter(private val ReceiveList:ArrayList<Detail>) : RecyclerView.Adapter<ReceiveAdapter.ReceiveViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiveViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.receive_item,parent,false)
        return ReceiveViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReceiveViewHolder, position: Int) {
        val detail: Detail = ReceiveList[position]
        holder.userName.text = detail.userName
        holder.userAddress.text = detail.userAddress
        holder.userDetails.text = detail.userDetails
    }

    override fun getItemCount(): Int {
       return ReceiveList.size
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userName:TextView = itemView.findViewById(R.id.userName)
        val userAddress:TextView = itemView.findViewById(R.id.userAddress)
        val userDetails:TextView = itemView.findViewById(R.id.userDetails)
    }
}