package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.data.CategoryDataBaseHelper
import com.example.firstapp.model.Categoria
import com.example.firstapp.util.CategoriaAdapter
import kotlinx.android.synthetic.main.activity_list_categoria.*

class ListCategoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_categoria)

        rvCategoria.apply {
            layoutManager = LinearLayoutManager(this@ListCategoriaActivity)
            adapter = CategoriaAdapter(getData())
        }
    }

    private  fun getData():MutableList<Categoria>{
        val context = this
        val db = CategoryDataBaseHelper(context)
        return db.readData()
    }
}