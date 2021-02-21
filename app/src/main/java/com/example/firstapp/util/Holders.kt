package com.example.firstapp.util

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserListItemHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.name
    val login: TextView = itemView.login
}