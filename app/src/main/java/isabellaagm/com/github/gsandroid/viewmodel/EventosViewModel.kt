package isabellaagm.com.github.gsandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import isabellaagm.com.github.gsandroid.data.EventoDao
import isabellaagm.com.github.gsandroid.model.EventoModel
import isabellaagm.com.github.gsandroid.data.EventoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


class EventosViewModel(application: Application) : AndroidViewModel(application) {

    private val eventoDao: EventoDao

    val eventosLiveData: LiveData<List<EventoModel>>

    init {

        val database = Room.databaseBuilder(
            getApplication(),
            EventoDatabase::class.java,
            "eventos_database"
        ).build()

        eventoDao = database.eventoDao()
        eventosLiveData = eventoDao.getAll()
    }

    fun addEvento(local: String, tipo: String, impacto: String, data: String, pessoasAfetadas: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newEvento = EventoModel(local = local, tipoEvento = tipo, grauImpacto = impacto, dataEvento = data, pessoasAfetadas = pessoasAfetadas)
            eventoDao.insert(newEvento)
        }
    }

    fun removeEvento(evento: EventoModel) {

        viewModelScope.launch(Dispatchers.IO) {
            eventoDao.delete(evento)
        }
    }
}