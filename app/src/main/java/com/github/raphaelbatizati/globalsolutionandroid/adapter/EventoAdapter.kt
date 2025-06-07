package com.github.raphaelbatizati.globalsolutionandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.raphaelbatizati.globalsolutionandroid.R
import com.github.raphaelbatizati.globalsolutionandroid.model.Evento

class EventoAdapter(
    private val eventos: MutableList<Evento>,
    private val onExcluirClick: (position: Int) -> Unit
) : RecyclerView.Adapter<EventoAdapter.EventoViewHolder>() {

    inner class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvLocal: TextView = itemView.findViewById(R.id.tvLocal)
        val tvTipoEvento: TextView = itemView.findViewById(R.id.tvTipoEvento)
        val tvGrauImpacto: TextView = itemView.findViewById(R.id.tvGrauImpacto)
        val tvData: TextView = itemView.findViewById(R.id.tvData)
        val tvPessoasAfetadas: TextView = itemView.findViewById(R.id.tvPessoasAfetadas)
        val btnExcluir: Button = itemView.findViewById(R.id.btnExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]

        holder.tvLocal.text = "🌍 Local: ${evento.local}"
        holder.tvTipoEvento.text = "🌪️ Tipo: ${evento.tipoEvento}"
        holder.tvGrauImpacto.text = "⚠️ Grau de Impacto: ${evento.grauImpacto}"
        holder.tvData.text = "📅 Data: ${evento.dataEvento}"
        holder.tvPessoasAfetadas.text = "👥 Pessoas Afetadas: ${evento.pessoasAfetadas}"

        holder.btnExcluir.setOnClickListener {
            onExcluirClick(position)
        }
    }

    override fun getItemCount(): Int = eventos.size
}
