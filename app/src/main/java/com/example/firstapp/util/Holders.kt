package com.example.firstapp.util

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.categoria_list_item.view.*
import kotlinx.android.synthetic.main.user_list_item.view.*
import org.w3c.dom.Text

class UserListItemHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.name
    val login: TextView = itemView.login
}

class CategoriaListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val codCategoria: TextView = itemView.lblCodCategoria
    val descCategoria: TextView = itemView.lblDescCategoria
}