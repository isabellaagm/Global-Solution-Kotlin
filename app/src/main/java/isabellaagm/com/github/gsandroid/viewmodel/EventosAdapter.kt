package isabellaagm.com.github.gsandroid.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import isabellaagm.com.github.gsandroid.R
import isabellaagm.com.github.gsandroid.model.EventoModel


class EventosAdapter(
    private val onItemRemoved: (EventoModel) -> Unit
) : RecyclerView.Adapter<EventosAdapter.ItemViewHolder>() {

    private var eventos = listOf<EventoModel>()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewLocal: TextView = view.findViewById(R.id.textViewLocal)
        private val textViewTipoEvento: TextView = view.findViewById(R.id.textViewTipoEvento)
        private val textViewGrauImpacto: TextView = view.findViewById(R.id.textViewGrauImpacto)
        private val textViewDataEvento: TextView = view.findViewById(R.id.textViewDataEvento)
        private val textViewPessoasAfetadas: TextView = view.findViewById(R.id.textViewPessoasAfetadas)
        private val buttonDelete: ImageButton = view.findViewById(R.id.imageButtonDelete)

        fun bind(evento: EventoModel) {
            textViewLocal.text = "Local: ${evento.local}"
            textViewTipoEvento.text = "Tipo: ${evento.tipoEvento}"
            textViewGrauImpacto.text = "Impacto: ${evento.grauImpacto}"
            textViewDataEvento.text = "Data: ${evento.dataEvento}"
            textViewPessoasAfetadas.text = "Pessoas Afetadas: ${evento.pessoasAfetadas}"

            buttonDelete.setOnClickListener {
                onItemRemoved(evento)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.evento_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(eventos[position])
    }

    override fun getItemCount(): Int = eventos.size

    fun updateEventos(newEventos: List<EventoModel>) {
        eventos = newEventos
        notifyDataSetChanged()
    }
}