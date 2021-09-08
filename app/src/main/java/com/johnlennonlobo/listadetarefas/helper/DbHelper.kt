package com.johnlennonlobo.listadetarefas.helper

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class DbHelper(
    context: Context?,
) : SQLiteOpenHelper(context, NAME_DB, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

         val sql = "CREATE TABLE IF NOT EXISTS $TABLE_TAREFAS " +
                 "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 " nome TEXT NOT NULL ); "

        try{
            db?.execSQL(sql)
            Log.i("INFO DB", "Sucesso ao criar tabela")
        }catch (e: Exception){
            Log.i("INFO DB", "Erro ao criar tabela: ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object{
        private var VERSION = 1
         var NAME_DB = "DB_TAREFAS"
         var TABLE_TAREFAS = "tarefas"
    }
}