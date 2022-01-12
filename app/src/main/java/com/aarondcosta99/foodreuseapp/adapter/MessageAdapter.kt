package com.aarondcosta99.foodreuseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aarondcosta99.foodreuseapp.R
import com.aarondcosta99.foodreuseapp.model.Message
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(private val MessageList:ArrayList<Message>):RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private val left = 0
    private val right = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType==right){
            val view1 = LayoutInflater.from(parent.context).inflate(R.layout.chat_sender_item,parent,false)
            MessageViewHolder(view1)
        }else{
            val view2 = LayoutInflater.from(parent.context).inflate(R.layout.chat_receiver_item,parent,false)
            MessageViewHolder(view2)
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message:Message = MessageList[position]
        holder.showMessage.text = message.message
        if(position==MessageList.size-1){
            if(message.isSeen)
            {
                holder.textSeen.text = "Seen"
            }else{
                holder.textSeen.text = "Delivered"
            }
        }else{
            holder.textSeen.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return MessageList.size
    }

    class MessageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val showMessage: TextView = itemView.findViewById(R.id.showMessage)
        val textSeen: TextView = itemView.findViewById(R.id.textSeen)
    }

    override fun getItemViewType(position: Int): Int {
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        return if(MessageList[position].userID==userID)
        {
            right
        }else
        {
            left
        }
    }
}