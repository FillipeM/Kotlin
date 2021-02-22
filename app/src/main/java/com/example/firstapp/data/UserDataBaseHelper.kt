package com.example.firstapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.firstapp.model.User

private val DATABASENAME = "FIRSTAPPDATABASE"
private val DATABASE_VERSION = 1
private val TABLENAME = "Users"
private val COL_NAME = "name"
private val COL_LOGIN = "login"
private val COL_PWD = "pwd"
private val COL_CODIGO = "codUsuario"
private val COL_DELETED = "DELETED"

class UserDataBaseHelper (var context : Context) : SQLiteOpenHelper(context, DATABASENAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val command = "CREATE TABLE $TABLENAME ($COL_CODIGO integer primary key autoincrement, $COL_NAME varchar(255), $COL_LOGIN varchar(255), $COL_PWD varchar(255), $COL_DELETED bit)"
        db?.execSQL(command)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user: User){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, user.name)
        contentValues.put(COL_LOGIN, user.login)
        contentValues.put(COL_PWD, user.pwd)
        contentValues.put(COL_DELETED, user.deleted)

        val resultInsert = db.insert(TABLENAME, null, contentValues)

        if (resultInsert == (0).toLong())
        {
            Toast.makeText(context, "Falha ao inserir usuário", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    fun readData():MutableList<User>{
        val list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLENAME WHERE DELETED = 0"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do{
                val user = User()
                user.codUsuario = result.getString(result.getColumnIndex(COL_CODIGO)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.login = result.getString(result.getColumnIndex(COL_LOGIN))
                user.pwd = result.getString(result.getColumnIndex(COL_PWD))
                user.deleted = result.getString(result.getColumnIndex(COL_DELETED)).toBoolean();
                list.add(user)
            }while (result.moveToNext())
        }
        return  list;
    }
}