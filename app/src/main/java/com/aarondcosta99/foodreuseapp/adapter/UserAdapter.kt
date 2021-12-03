package com.aarondcosta99.foodreuseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aarondcosta99.foodreuseapp.R
import com.aarondcosta99.foodreuseapp.model.User

class UserAdapter(private val UserList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user:User = UserList[position]
        holder.userName.text = user.userName
    }

    override fun getItemCount(): Int {
        return UserList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userName:TextView = itemView.findViewById(R.id.userName)
    }
}
