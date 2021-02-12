package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.firstapp.model.Usuario
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View){
        val usuario: Usuario;

        val nome = findViewById<EditText>(R.id.txtNome);
        val login = findViewById<EditText>(R.id.txtLogin);
        val pwd = findViewById<EditText>(R.id.txtPassword);

        usuario = Usuario(codUsuario = 0, nome = nome.text.toString(), login = login.text.toString(), pwd = pwd.text.toString(), deleted = false);

    }
}