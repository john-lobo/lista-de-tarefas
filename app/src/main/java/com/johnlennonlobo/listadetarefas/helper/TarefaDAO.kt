package com.johnlennonlobo.listadetarefas.helper

import com.johnlennonlobo.listadetarefas.model.Tarefa

interface TarefaDAO {

    fun insert (tarefa:Tarefa) : Boolean
    fun update(tarefa:Tarefa) : Boolean
    fun delete (tarefa:Tarefa) : Boolean
    fun getLisTarefas(): List<Tarefa>
}