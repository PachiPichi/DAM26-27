package com.example.mvvmpatrick.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmpatrick.R
import com.example.mvvmpatrick.databinding.ActivityMainBinding
import com.example.mvvmpatrick.di.AppModule

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel : MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            AppModule.damePresidenteUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configRecycler()
        observarEstado()

        setupEventos()

    }

    private fun configRecycler(){
        binding.listado.layoutManager = LinearLayoutManager(this)
        binding.listado.adapter = PoliticoAdapter()


    }
    private fun setupEventos() {
        binding.button.setOnClickListener {
            viewModel.handleDamePresidente()

        }
    }

    private fun observarEstado() {
        viewModel.state.observe(this, {state ->
            state?.presidente.let {
                binding.textView.text = state.presidente
            }
            state?.error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

            }
        })
    }
}