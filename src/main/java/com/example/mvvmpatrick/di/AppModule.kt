package com.example.mvvmpatrick.di

import com.example.mvvmpatrick.domain.useCases.DamePresidenteUseCase
import com.example.mvvmpatrick.ui.main.MainViewModel

object AppModule {
    fun presidenteMainViewModel(): MainViewModel = MainViewModel(damePresidenteUseCase)

    val damePresidenteUseCase : DamePresidenteUseCase = DamePresidenteUseCase()
}