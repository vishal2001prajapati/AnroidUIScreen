package com.example.anroiduiscreen.onboardscreen

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.anroiduiscreen.ONE
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.ZERO
import com.example.anroiduiscreen.adapter.ViewPagerAdapter
import com.example.anroiduiscreen.signinscreen.SignInActivity
import kotlinx.android.synthetic.main.activity_on_board_screen.btnNext
import kotlinx.android.synthetic.main.activity_on_board_screen.imgDot
import kotlinx.android.synthetic.main.activity_on_board_screen.imgUnDot
import kotlinx.android.synthetic.main.activity_on_board_screen.viewSwipe

class OnBoardScreenActivity : AppCompatActivity() {

    lateinit var modelList: ArrayList<FoodDeliveryDataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        showViewPager()

        btnNext.setOnClickListener {
            if (viewSwipe.currentItem != modelList.count() - 1) {
                viewSwipe.setCurrentItem(viewSwipe.currentItem + 1, true)
            } else {
                val intent = Intent(this,SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewSwipe.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    ZERO -> {
                        imgDot.setImageResource(R.drawable.ic_dot)
                        imgUnDot.setImageResource(R.drawable.ic_unfilldot)
                    }
                    ONE -> {
                        imgDot.setImageResource(R.drawable.ic_unfilldot)
                        imgUnDot.setImageResource(R.drawable.ic_dot)
                    }
                }
                super.onPageSelected(position)
            }
        })
    }

    private fun showViewPager() {
        modelList = ArrayList()
        modelList.apply {
            add(FoodDeliveryDataClass(R.drawable.startdelivery, getString(R.string.deliveryEveryEveryWhere), getString(R.string.foodSubString)))
            add(FoodDeliveryDataClass(R.drawable.delivrygo, getString(R.string.fastFoodDelivery), getString(R.string.foodSubString)))
        }
        val adapter = ViewPagerAdapter(modelList)
        viewSwipe.apply {
            this.adapter = adapter
        }
    }
}