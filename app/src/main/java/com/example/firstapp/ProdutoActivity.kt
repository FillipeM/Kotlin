package com.example.firstapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.data.CategoryDataBaseHelper
import com.example.firstapp.data.ProdutoDataBaseHelper
import com.example.firstapp.util.CustomSpinAdapter
import kotlinx.android.synthetic.main.activity_produto.*
import java.io.ByteArrayOutputStream

const val REQUEST_IMAGE_CAPTURE = 1

class ProdutoActivity : AppCompatActivity() {
    var codCategoria: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        loadSpinnerData()
        btnProdutoFoto.setOnClickListener(View.OnClickListener {
            dispatchTakePictureIntent()
        })

        btnSalvarProduto.setOnClickListener(View.OnClickListener {
            saveProduto()
        })

        btnListarProdutos.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListProdutoActivity::class.java)
            startActivity(intent)
        })
    }

    private fun saveProduto() {
        val db = ProdutoDataBaseHelper(this)
        val img = (imageView.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        img.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val ba = stream.toByteArray()
        db.insertData(
            descProduto = txtDescricaoProduto.text.toString(),
            codCategoria = codCategoria,
            img = ba
        )
    }

    private  fun clearFields()
    {
        txtDescricaoProduto.text.clear()
        loadSpinnerData()
        imageView.setImageDrawable(null)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun loadSpinnerData() {
        val db = CategoryDataBaseHelper(this)

        val list = db.readData()

        val adapter = CustomSpinAdapter(
            this,
            android.R.layout.simple_spinner_item, list
        )

        ddlCategoria.adapter = adapter

        ddlCategoria.onItemSelectedListener = ddlCategoria_onItemSelected(adapter)

    }

    private fun ddlCategoria_onItemSelected(adapter: CustomSpinAdapter): OnItemSelectedListener {
        return object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val categoria = adapter.getItem(position)

                codCategoria = categoria.codCategoria
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
}