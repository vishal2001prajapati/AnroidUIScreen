package com.example.anroiduiscreen.homescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.anroiduiscreen.R

class HomeScreenAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val slideList: List<Int>) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return slideList.size
    }

    override fun createFragment(position: Int): Fragment {
        return HomeScreenFragment(slideList[position])
    }
}
