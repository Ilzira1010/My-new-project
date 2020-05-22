package com.example.myapplicationready

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, dbname,factory, version){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table user(id integer primary key autoincrement," + "name varchar(30), email varchar(50), password varchar(20))")
    }

    override fun onUpgrade(DB: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertUserData(name:String, email:String, password:String) {
        val DB: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("name", name)
        values.put("email", email)
        values.put("password", password)
        DB.insert("user",null,values)
        DB.close()
    }
    fun userPresent(email: String, password: String): Boolean {
        val DB = writableDatabase
        val query="select * from user where email = '$email' and password ='$password'"
        val cursor = DB.rawQuery(query, null)
        if (cursor.count<=0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true

    }

    companion object {
        internal val dbname = "userDB"
        internal val factory =null
        internal val version =1
    }
}