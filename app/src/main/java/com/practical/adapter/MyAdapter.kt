package com.practical.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.practical.fragment.AlbumFragment
import com.practical.fragment.ArtistFragment
import com.practical.fragment.TracksFragment

class MyAdapter(
    private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int,
    internal var tag: String
) :
    FragmentPagerAdapter(fm) {

    // this is for fragment tabs  
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return AlbumFragment(tag)
            }
            1 -> {
                return ArtistFragment(tag)
            }
            2 -> {
                return TracksFragment(tag)
            }
        }
        return AlbumFragment(tag)
    }

    // this counts total number of tabs  
    override fun getCount(): Int {
        return totalTabs
    }
}  