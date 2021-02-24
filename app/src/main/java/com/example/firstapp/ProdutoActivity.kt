package com.example.firstapp

import android.content.Intent
import android.graphics.Bitmap
import android.hardware.camera2.CameraDevice
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.data.CategoryDataBaseHelper
import com.example.firstapp.util.CustomSpinAdapter
import kotlinx.android.synthetic.main.activity_produto.*

const val REQUEST_IMAGE_CAPTURE = 1
class ProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        loadSpinnerData()
        btnProdutoFoto.setOnClickListener(View.OnClickListener {
            dispatchTakePictureIntent()
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun dispatchTakePictureIntent(){
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

                Toast.makeText(
                    this@ProdutoActivity,
                    categoria.toString(), Toast.LENGTH_LONG
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
}