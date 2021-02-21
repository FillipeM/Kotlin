package com.example.firstapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.data.UserDataBaseHelper
import com.example.firstapp.model.User
import com.example.firstapp.util.UserAdapter
import kotlinx.android.synthetic.main.activity_list_user.*


class ListUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)


        recyclerView.apply {
            layoutManager = LinearLayoutManager( this@ListUserActivity)
            adapter = UserAdapter(getData())
        }
    }

    fun getData() : MutableList<User> {
        val context = this
        val db = UserDataBaseHelper(context)

        return db.readData();
    }
}