package com.example.mvvmpatrick.data

import com.example.mvvmpatrick.domain.model.Politico

object Politicos {
    private val politicos = mutableListOf(
        Politico("Fernando", "Basurillas", 0),
        Politico("Maricarmen", "Panteras Grises", 0),
    )

    fun damePresidente() = politicos[1]
}