package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firstapp.data.CategoryDataBaseHelper
import com.example.firstapp.model.Categoria
import kotlinx.android.synthetic.main.activity_categoria.*

class CategoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)

        val codCategoria = 0

        val db = CategoryDataBaseHelper(this)
        btnSalvarCategoria.setOnClickListener(View.OnClickListener {
            val categoria = Categoria(codCategoria, txtDescCategoria.text.toString())
            db.insertData(categoria)
            clearFields()
        })

        btnConsultaCategorias.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListCategoriaActivity::class.java)
            startActivity(intent)
        })
    }

    private fun clearFields() {
        txtCodCategoria.text.clear()
        txtDescCategoria.text.clear()
    }
}