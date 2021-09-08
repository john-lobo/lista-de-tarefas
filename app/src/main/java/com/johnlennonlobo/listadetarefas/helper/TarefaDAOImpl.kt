package com.johnlennonlobo.listadetarefas.helper

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.johnlennonlobo.listadetarefas.model.Tarefa
import java.lang.Exception

class TarefaDAOImpl(private val context: Context) : TarefaDAO{

    val db = DbHelper(context)
    val escreve = db.writableDatabase
    val ler = db.readableDatabase

    override fun insert(tarefa: Tarefa): Boolean {

        var cv = ContentValues()
            cv.put("nome",tarefa.nomeTarefa)
       try {
           escreve.insert(DbHelper.TABLE_TAREFAS,null,cv)
           Log.i("INFO DB", "Sucesso ao inserir tarefa " )
       }catch (e: Exception){
           Log.i("INFO DB", "Erro ao inserir tarefa ${e.message}" )
           return false
       }

        return true
    }

    override fun update(tarefa: Tarefa): Boolean {
        var cv = ContentValues ()
        cv.put("nome",tarefa.nomeTarefa)
        val args: Array<String> = arrayOf(tarefa.id.toString())

        try {
            escreve.update(DbHelper.TABLE_TAREFAS, cv, "id=?",args)
            Log.i("INFO DB", "Sucesso ao inserir tarefa " )

        }catch (e :Exception){
            Log.i("INFO DB", "Erro ao inserir tarefa ${e.message}" )
            return false

        }
       return true
    }

    override fun delete(tarefa: Tarefa): Boolean {

        val args: Array<String> = arrayOf(tarefa.id.toString())
        try {
            escreve.delete(DbHelper.TABLE_TAREFAS,"id=?",args)
            Log.i("INFO DB", "Sucesso ao delatar tarefa " )
        }catch (e:Exception){
            Log.i("INFO DB", "Erro ao deletar tarefa ${e.message}" )
            return false
        }

        return true
    }

    override fun getLisTarefas(): ArrayList<Tarefa> {

        var listaTarefas:ArrayList<Tarefa> = arrayListOf()
        var sql = "SELECT * FROM ${DbHelper.TABLE_TAREFAS} ;"
        var c = ler.rawQuery(sql,null)
        while (c.moveToNext()){

            var id = c.getLong(c.getColumnIndex("id"))
            var nomeTarefa = c.getString(c.getColumnIndex("nome"))

            listaTarefas.add(Tarefa(id = id,nomeTarefa = nomeTarefa))
        }

        return listaTarefas

    }


}