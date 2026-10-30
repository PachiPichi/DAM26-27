package com.example.mvvmpatrick.domain.useCases

import com.example.mvvmpatrick.data.Politicos

class DamePresidenteUseCase {
    fun damePresidente() = Politicos.damePresidente()
}