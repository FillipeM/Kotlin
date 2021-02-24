package com.example.firstapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.firstapp.model.Categoria

private val DATABASENAME = "CATEGORIADB"
private val DATABASE_VERSION = 1
private val TABLENAME = "Categories"
private val COL_CODIGO = "codCategory"
private val COL_DESCRICAO = "descCategory"

class CategoryDataBaseHelper(var context: Context): SQLiteOpenHelper(context, DATABASENAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val commad = "CREATE TABLE $TABLENAME($COL_CODIGO integer primary key autoincrement, $COL_DESCRICAO varchar(255))"
        db?.execSQL(commad)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(categoria: Categoria)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_DESCRICAO, categoria.descCategoria)
         val resultInsert = db.insert(TABLENAME, null, contentValues)

        if (resultInsert == (0).toLong())
        {
            Toast.makeText(context, "Falha ao inserir categoria", Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(context, "Sucesso", Toast.LENGTH_LONG).show()
        }
    }

    fun readData():MutableList<Categoria>{
        val list: MutableList<Categoria> = ArrayList()
        val db = this.readableDatabase
        val command = "Select * from $TABLENAME"
        val result = db.rawQuery(command, null)
        if (result.moveToFirst())
        {
            do{
                val categoria = Categoria()
                categoria.codCategoria = result.getString(result.getColumnIndex(COL_CODIGO)).toInt()
                categoria.descCategoria = result.getString(result.getColumnIndex(COL_DESCRICAO))

                list.add(categoria)

            }while (result.moveToNext())
        }
        return list
    }
}