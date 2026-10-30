package com.example.mvvmpatrick.domain.model

data class Politico(
    val nombre: String,
    val partido: String,
    val vecesDeseoMuerto: Int = 0
)