package com.example.firstapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.example.firstapp.model.User

class UserAdapter: RecyclerView.Adapter<UserListItemHolder> (){
    var list = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserListItemHolder, position: Int) {
        val item = list[position]

        holder.lblName.text = item.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemHolder {
        val li = LayoutInflater.from(parent.context)
        val view = li.inflate(R.layout.user_list_item, parent, false) as TextView
        return  UserListItemHolder(view)
    }
}