package com.adia.dev.playground.ui.explore.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adia.dev.playground.R
import com.adia.dev.playground.ui.explore.tabs.LibraryFragment
import com.adia.dev.playground.ui.home.MangaListFragment

public val TAB_TITLES = arrayOf(
    "Library",
    "Reading",
    "Later",
    "Completed",
    "Favorites",
    "Recommendations"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MangaListFragment()
            else -> PlaceholderFragment.newInstance(position + 1)
        }
    }
}