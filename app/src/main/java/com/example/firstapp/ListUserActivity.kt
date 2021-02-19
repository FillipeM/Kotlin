package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.contentcapture.DataShareWriteAdapter
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.data.UserDataBaseHelper
import com.example.firstapp.util.UserAdapter

class ListUserActivity : AppCompatActivity() {
    var dataAdapter = UserAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        var rv = findViewById<RecyclerView>(R.id.recyclerView);
        rv.adapter = dataAdapter
        getData()

        val btn = findViewById<Button>(R.id.btnVoltar)
        btn.setOnClickListener(View.OnClickListener {

        })



    }

    fun getData()
    {
        val context = this
        val db = UserDataBaseHelper(context)

        dataAdapter.list = db.readData();
    }
}