package com.adia.dev.playground.ui.explore

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.adia.dev.playground.ui.explore.ui.main.SectionsPagerAdapter
import com.adia.dev.playground.databinding.ActivityExploreBinding
import com.adia.dev.playground.ui.explore.ui.main.TAB_TITLES
import com.google.android.material.tabs.TabLayoutMediator

class ExploreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExploreBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()

    }
}