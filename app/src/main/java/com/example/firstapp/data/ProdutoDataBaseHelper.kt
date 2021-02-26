package com.example.firstapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.database.getBlobOrNull
import com.example.firstapp.model.Produto

private const val DATABASENAME = "PRODUTODB"
private const val DATABASE_VERSION = 1
private const val TABLENAME = "Products"
private const val COL_CODIGO = "codProduto"
private const val COL_DESCRICAO = "descProduto"
private const val COL_CATEGORIA = "codCategoria"
private const val COL_IMG = "img"

class ProdutoDataBaseHelper(var context: Context) :
    SQLiteOpenHelper(context, DATABASENAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        var command =
            "CREATE TABLE $TABLENAME($COL_CODIGO integer primary key autoincrement, $COL_DESCRICAO varchar(255), $COL_CATEGORIA int, $COL_IMG blob)"
        db?.execSQL(command)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var command = "DROP TABLE IF EXISTS $TABLENAME"
        db?.execSQL(command)

        onCreate(db)
    }

    fun insertData(descProduto: String, codCategoria: Int, img: ByteArray) {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_DESCRICAO, descProduto)
        cv.put(COL_CATEGORIA, codCategoria)
        cv.put(COL_IMG, img)

        val resultInsert = db.insert(TABLENAME, null, cv)

        if (resultInsert == 0.toLong()) {
            Toast.makeText(context, "Registo inserido com sucesso!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Erro ao inserir!", Toast.LENGTH_LONG).show()
        }
    }

    fun readData(): MutableList<Produto>{
        var db = this.readableDatabase
        val dbCategoria = CategoryDataBaseHelper(context)
        var list: MutableList<Produto> = ArrayList()
        val command = "SELECT * FROM $TABLENAME"
        var result = db.rawQuery(command, null)
        if (result.moveToFirst()){
            do{
                val codProduto = result.getString(result.getColumnIndex(COL_CODIGO)).toInt()
                val descProduto = result.getString(result.getColumnIndex(COL_DESCRICAO))
                val codCategoria = result.getString(result.getColumnIndex(COL_CATEGORIA)).toInt()
                val categoria = dbCategoria.getById(codCategoria)
                val img = result.getBlobOrNull(result.getColumnIndex(COL_IMG))
                var produto = Produto(codProduto, descProduto, categoria, img)

                list.add(produto)

            }while (result.moveToNext())
        }
        return  list;
    }
}