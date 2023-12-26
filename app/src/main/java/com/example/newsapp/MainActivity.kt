package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.mvvm.NewsRepository
import com.example.newsapp.mvvm.NewsViewModel
import com.example.newsapp.mvvm.NewsViewModelFactory
import com.example.newsapp.room.NewsDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel : NewsViewModel by lazy {
        val db = NewsDatabase.getDatabase(this)
        val newsRepo = NewsRepository(db)
        val newsFactory = NewsViewModelFactory(newsRepo)
        ViewModelProvider(this,newsFactory)[NewsViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

    }
}