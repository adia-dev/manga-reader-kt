package com.adia.dev.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adia.dev.playground.databinding.ActivityMainBinding
import com.adia.dev.playground.ui.home.MangaListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MangaListFragment())
                .commitNow()
        }
    }
}