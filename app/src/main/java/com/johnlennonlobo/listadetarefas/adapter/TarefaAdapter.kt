package com.johnlennonlobo.listadetarefas.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.johnlennonlobo.listadetarefas.R
import com.johnlennonlobo.listadetarefas.activity.AddTarefaActivity
import com.johnlennonlobo.listadetarefas.helper.TarefaDAOImpl
import com.johnlennonlobo.listadetarefas.model.Tarefa

class TarefaAdapter(private val listTarefas: ArrayList<Tarefa>, private val context:Context) : RecyclerView.Adapter<TarefaAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tarefa,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder){

            bind(listTarefas[position])

            itemView.setOnClickListener {
                //recuperar tarefa para edição
                var tarefa = listTarefas.get(position)

                //Envia tarefa para tela adicionar tarefa
                val intent = Intent(context, AddTarefaActivity::class.java)
                intent.putExtra("tarefaSelecionada",tarefa)
                context.startActivity(intent)
            }

            itemView.setOnLongClickListener {

                var tarefa = listTarefas.get(position)
                AlertDialog.Builder(context)
                    .setTitle("Confirmar exclusão")
                    .setMessage("Deseja excluir a tarefa: ${tarefa.nomeTarefa}")
                    .setPositiveButton("Sim"){_,_ ->

                        var tarefaDAO = TarefaDAOImpl(context)
                       if( tarefaDAO.delete(tarefa)){
                           Toast.makeText(context, "Tarefa excluida com sucesso", Toast.LENGTH_SHORT).show()
                           listTarefas.removeAt(position)
                           notifyDataSetChanged()
                       }else{
                           Toast.makeText(context, "Erro ao excluir tarefa", Toast.LENGTH_SHORT).show()
                       }

                    }
                    .setNegativeButton("Não",null)
                    .create()
                    .show()
                true
            }
        }

    }

    override fun getItemCount(): Int {
        return listTarefas.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(tarefa: Tarefa){
            val textTarefa = itemView.findViewById<TextView>(R.id.textTarefa)
            textTarefa.text = "${tarefa.nomeTarefa}"
        }
    }
}