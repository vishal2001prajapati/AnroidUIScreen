package com.example.anroiduiscreen.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.ZERO
import kotlinx.android.synthetic.main.activity_home_screen.imageSwipeViewPager

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var myModelList: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        itemShow()
    }

    private fun itemShow() {
        myModelList = ArrayList()
        myModelList.apply {
            add(R.drawable.banner_image)
            add(R.drawable.banner_image_two)
        }
        imageSwipeViewPager.adapter = HomeScreenAdapter(supportFragmentManager, lifecycle, myModelList)
    }
}