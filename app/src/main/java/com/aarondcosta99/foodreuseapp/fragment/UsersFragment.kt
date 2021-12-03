package com.aarondcosta99.foodreuseapp.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aarondcosta99.foodreuseapp.R
import com.aarondcosta99.foodreuseapp.adapter.UserAdapter
import com.aarondcosta99.foodreuseapp.databinding.FragmentUsersBinding
import com.aarondcosta99.foodreuseapp.model.User
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsersFragment : Fragment(R.layout.fragment_users) {
    private var _binding:FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var userArrayList:ArrayList<User>
    private lateinit var userAdapter:UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        binding.userRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        userAdapter = UserAdapter(userArrayList)
        binding.userRecyclerView.adapter = userAdapter
        eventChangeListener()
        return binding.root
    }

    private fun eventChangeListener() {
        val db = Firebase.firestore
        db.collection("users").orderBy("userName", Query.Direction.ASCENDING).addSnapshotListener{ snapshot, e ->
            if (e != null)
            {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            for(dc: DocumentChange in snapshot?.documentChanges!!)
            {
                if(dc.type == DocumentChange.Type.ADDED)
                {
                    userArrayList.add(dc.document.toObject(User::class.java))
                }
            }
            userAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}