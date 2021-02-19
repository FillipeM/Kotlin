package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.firstapp.data.UserDataBaseHelper
import com.example.firstapp.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        val db = UserDataBaseHelper(context)
        val btn = findViewById<Button>(R.id.btnSalvar)
        val txtName = findViewById<EditText>(R.id.txtNome)
        val txtLogin = findViewById<EditText>(R.id.txtLogin)
        val txtPwd = findViewById<EditText>(R.id.txtPassword)
        btn.setOnClickListener(View.OnClickListener {
            if (!txtName.text.isEmpty() && !txtLogin.text.isEmpty() && !txtPwd.text.isEmpty()) {
                val user = User(codUsuario = 0, name = txtName.text.toString(), login = txtLogin.text.toString(), pwd = txtPwd.text.toString(), deleted = false )
                db.insertData(user);
                clearFields()
            }
        })

        val btnList = findViewById<Button>(R.id.btnListaUsuarios)
        btnList.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListUserActivity::class.java)
            startActivity(intent)
        })
    }

    private fun clearFields()
    {
        val txtName = findViewById<EditText>(R.id.txtNome)
        val txtLogin = findViewById<EditText>(R.id.txtLogin)
        val txtPwd = findViewById<EditText>(R.id.txtPassword)

        txtLogin.text.clear()
        txtName.text.clear()
        txtPwd.text.clear()
    }


}