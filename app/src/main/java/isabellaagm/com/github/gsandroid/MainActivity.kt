package isabellaagm.com.github.gsandroid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import isabellaagm.com.github.gsandroid.viewmodel.EventosAdapter
import isabellaagm.com.github.gsandroid.viewmodel.EventosViewModel
import isabellaagm.com.github.gsandroid.viewmodel.EventosViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EventosViewModel

    private lateinit var editTextLocal: EditText
    private lateinit var spinnerTipoEvento: Spinner
    private lateinit var spinnerGrauImpacto: Spinner
    private lateinit var editTextDataEvento: EditText
    private lateinit var editTextPessoasAfetadas: EditText
    private lateinit var buttonIncluir: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        supportActionBar?.title = "Registro de Eventos Extremos"

        editTextLocal = findViewById(R.id.editTextLocal)
        spinnerTipoEvento = findViewById(R.id.spinnerTipoEvento)
        spinnerGrauImpacto = findViewById(R.id.spinnerGrauImpacto)
        editTextDataEvento = findViewById(R.id.editTextDataEvento)
        editTextPessoasAfetadas = findViewById(R.id.editTextPessoasAfetadas)
        buttonIncluir = findViewById(R.id.buttonIncluir)
        recyclerView = findViewById(R.id.recyclerView)

        ArrayAdapter.createFromResource(
            this,
            R.array.tipos_evento_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTipoEvento.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.graus_impacto_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGrauImpacto.adapter = adapter
        }

        val eventosAdapter = EventosAdapter { evento ->
            viewModel.removeEvento(evento)
        }
        recyclerView.adapter = eventosAdapter

        val viewModelFactory = EventosViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EventosViewModel::class.java)


        viewModel.eventosLiveData.observe(this) { eventos ->
            eventosAdapter.updateEventos(eventos)
        }

        buttonIncluir.setOnClickListener {
            val local = editTextLocal.text.toString()
            val tipoEvento = spinnerTipoEvento.selectedItem.toString()
            val grauImpacto = spinnerGrauImpacto.selectedItem.toString()
            val dataEvento = editTextDataEvento.text.toString()
            val pessoasAfetadas = editTextPessoasAfetadas.text.toString().toInt()

            viewModel.addEvento(local, tipoEvento, grauImpacto, dataEvento, pessoasAfetadas)

            editTextLocal.text.clear()
            editTextDataEvento.text.clear()
            editTextPessoasAfetadas.text.clear()
            spinnerTipoEvento.setSelection(0)
            spinnerGrauImpacto.setSelection(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_identification -> {
                val intent = Intent(this, IdentificationActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.action_main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}


