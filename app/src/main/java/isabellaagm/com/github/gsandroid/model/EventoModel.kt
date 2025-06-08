package isabellaagm.com.github.gsandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class EventoModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val local: String,
    val tipoEvento: String,
    val grauImpacto: String,
    val dataEvento: String,
    val pessoasAfetadas: Int
)
