package isabellaagm.com.github.gsandroid.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import isabellaagm.com.github.gsandroid.model.EventoModel

@Database(entities = [EventoModel::class], version = 1)
abstract class EventoDatabase : RoomDatabase() {
    abstract fun eventoDao(): EventoDao
}

