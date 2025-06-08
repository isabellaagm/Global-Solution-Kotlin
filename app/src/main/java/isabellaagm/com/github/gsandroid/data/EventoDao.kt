package isabellaagm.com.github.gsandroid.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import isabellaagm.com.github.gsandroid.model.EventoModel

@Dao
interface EventoDao {

    @Query("SELECT * FROM EventoModel")
    fun getAll(): LiveData<List<EventoModel>>

    @Insert
    fun insert(evento: EventoModel)

    @Delete
    fun delete(evento: EventoModel)
}

