package com.example.kotlinchannels

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlinchannels.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btnShowSnackbar.setOnClickListener {
            viewModel.triggerEvent()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect { event ->

                when(event) {
                    is MainViewModel.MyEvent.ErrorEvent -> {
                        Snackbar.make(
                            binding.root,
                            event.message,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }

            }


        }
    }
}