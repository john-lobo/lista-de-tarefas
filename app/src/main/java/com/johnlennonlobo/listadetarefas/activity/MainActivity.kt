package com.johnlennonlobo.listadetarefas.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnlennonlobo.listadetarefas.R
import com.johnlennonlobo.listadetarefas.adapter.TarefaAdapter
import com.johnlennonlobo.listadetarefas.databinding.ActivityMainBinding
import com.johnlennonlobo.listadetarefas.helper.TarefaDAOImpl
import com.johnlennonlobo.listadetarefas.model.Tarefa

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tarefas: ArrayList<Tarefa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
          var intent = Intent(this, AddTarefaActivity::class.java)
            startActivity(intent)
        }
    }


    private fun configRecycler() {

        var tarefaDAOImpl =TarefaDAOImpl(this)
        tarefas = tarefaDAOImpl.getLisTarefas()

        with(binding.included.recycleView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TarefaAdapter(tarefas,this@MainActivity)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        configRecycler()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}