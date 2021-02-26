package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.data.CategoryDataBaseHelper
import com.example.firstapp.data.ProdutoDataBaseHelper
import com.example.firstapp.model.Categoria
import com.example.firstapp.model.Produto
import com.example.firstapp.util.CategoriaAdapter
import com.example.firstapp.util.ProdutoAdapter
import kotlinx.android.synthetic.main.activity_list_categoria.*
import kotlinx.android.synthetic.main.activity_list_produto.*

class ListProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_produto)

        rvProduto.apply {
            layoutManager = LinearLayoutManager(this@ListProdutoActivity)
            adapter = ProdutoAdapter(getData())
        }
    }

    private  fun getData():MutableList<Produto>{
        val context = this
        val db = ProdutoDataBaseHelper(context)
        return db.readData()
    }
}