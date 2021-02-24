package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.firstapp.data.UserDataBaseHelper
import com.example.firstapp.model.Produto
import com.example.firstapp.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMenuUsuario.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        })

        btnMenuCategoria.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, CategoriaActivity::class.java)
            startActivity(intent)
        })

        btnMenuProduto.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ProdutoActivity::class.java)
            startActivity(intent)
        })
    }
}