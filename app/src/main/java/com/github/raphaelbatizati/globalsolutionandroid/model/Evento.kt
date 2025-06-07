package com.github.raphaelbatizati.globalsolutionandroid.model

data class Evento(
    val local: String,
    val tipoEvento: String,
    val grauImpacto: String,
    val dataEvento: String,
    val pessoasAfetadas: Int
)
