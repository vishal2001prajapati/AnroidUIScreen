package com.example.anroiduiscreen.homescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.anroiduiscreen.R
import kotlinx.android.synthetic.main.fragment_home_screen.view.itemImageSwipe


class HomeScreenFragment(private val imageId: Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        view.itemImageSwipe.setImageResource(imageId)
        return view
    }
}