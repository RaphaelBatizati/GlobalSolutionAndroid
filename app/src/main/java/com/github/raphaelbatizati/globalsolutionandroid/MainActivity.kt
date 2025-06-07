package com.github.raphaelbatizati.globalsolutionandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.raphaelbatizati.globalsolutionandroid.adapter.EventoAdapter
import com.github.raphaelbatizati.globalsolutionandroid.databinding.ActivityMainBinding
import com.github.raphaelbatizati.globalsolutionandroid.model.Evento

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: EventoAdapter
    private val listaEventos = mutableListOf<Evento>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inicializa o RecyclerView
        adapter = EventoAdapter(listaEventos) { pos ->
            listaEventos.removeAt(pos)
            adapter.notifyItemRemoved(pos)
            Toast.makeText(this, "Evento removido.", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // 2. Botão para adicionar evento
        binding.btnAddEvent.setOnClickListener {
            val location = binding.etLocation.text.toString().trim()
            val eventType = binding.etEventType.text.toString().trim()
            val impactLevel = binding.etImpactLevel.text.toString().trim()
            val eventDate = binding.etEventDate.text.toString().trim()
            val affectedPeopleStr = binding.etAffectedPeople.text.toString().trim()

            // Validação de campos obrigatórios
            if (location.isEmpty() || eventType.isEmpty() || impactLevel.isEmpty() ||
                eventDate.isEmpty() || affectedPeopleStr.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val affectedPeople = affectedPeopleStr.toIntOrNull()
            if (affectedPeople == null || affectedPeople <= 0) {
                Toast.makeText(this, "Número estimado de pessoas deve ser maior que zero.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Criação do novo Evento e adição à lista
            val novoEvento = Evento(
                local = location,
                tipoEvento = eventType,
                grauImpacto = impactLevel,
                dataEvento = eventDate,
                pessoasAfetadas = affectedPeople
            )

            listaEventos.add(novoEvento)
            adapter.notifyItemInserted(listaEventos.size - 1)

            Toast.makeText(this, "Evento incluído com sucesso!", Toast.LENGTH_SHORT).show()

            // Limpa os campos
            binding.etLocation.text.clear()
            binding.etEventType.text.clear()
            binding.etImpactLevel.text.clear()
            binding.etEventDate.text.clear()
            binding.etAffectedPeople.text.clear()
        }
    }
}
