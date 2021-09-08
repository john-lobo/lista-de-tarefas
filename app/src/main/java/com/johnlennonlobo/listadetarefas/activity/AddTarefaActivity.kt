package com.johnlennonlobo.listadetarefas.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.johnlennonlobo.listadetarefas.R
import com.johnlennonlobo.listadetarefas.databinding.ActivityAddTarefaBinding
import com.johnlennonlobo.listadetarefas.helper.TarefaDAOImpl
import com.johnlennonlobo.listadetarefas.model.Tarefa

class AddTarefaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTarefaBinding
    private var tarefaAtual :Tarefa? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddTarefaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recuperar tarefa caso seja edição
        tarefaAtual = intent.extras?.getSerializable("tarefaSelecionada") as? Tarefa
        if(tarefaAtual != null){
            binding.inputText.setText(tarefaAtual?.nomeTarefa)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_add_tarefa,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.item_salvar ->{

                val inputText = binding.inputText.text.toString()

                if(inputText.isNotEmpty()){

                    if(tarefaAtual != null){

                        updateTarefa()

                    }else{

                        insertTarefa()

                    }
                }else{
                    Snackbar.make(binding.root,"Prencha o campo",Snackbar.LENGTH_SHORT).show()
                }

            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertTarefa() {
        var tarefaDAO=TarefaDAOImpl(this)
        val inputText=binding.inputText.text.toString()

        var tarefa = Tarefa()
        tarefa.nomeTarefa = inputText

        if (tarefaDAO.insert(tarefa)) {
            Toast.makeText(this, "Sucesso ao inserir a tarefa", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Erro ao inserir a tarefa", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateTarefa() {

        var tarefaDAO = TarefaDAOImpl(this)
        val inputText = binding.inputText.text.toString()

        var tarefa = Tarefa()
        tarefa.nomeTarefa=inputText
        tarefa.id = tarefaAtual?.id

        if (tarefaDAO.update(tarefa)) {
           Toast.makeText(this, "Sucesso ao alterar a tarefa", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Erro ao alterar a tarefa", Toast.LENGTH_LONG).show()
        }
    }
}