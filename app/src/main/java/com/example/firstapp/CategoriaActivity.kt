package com.example.firstapp

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
        val descCategoria = txtDescCategoria.text.toString()

        val db = CategoryDataBaseHelper(this)
        btnSalvarCategoria.setOnClickListener(View.OnClickListener {
            val categoria = Categoria(codCategoria, descCategoria)
            db.insertData(categoria)
            clearFields()
        })
    }

    private fun clearFields() {
        txtCodCategoria.text = ""
        txtDescCategoria.text = ""
    }
}