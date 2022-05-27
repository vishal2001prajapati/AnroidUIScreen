package com.example.anroiduiscreen.homescreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.adapter.DiscountAdapter
import com.example.anroiduiscreen.adapter.MenuRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_home_screen.discountRecyclerView
import kotlinx.android.synthetic.main.activity_home_screen.imageSwipeViewPager
import kotlinx.android.synthetic.main.activity_home_screen.menuRecyclerView
import kotlinx.android.synthetic.main.activity_home_screen.timerCountHours
import kotlinx.android.synthetic.main.activity_home_screen.timerCountMinutes
import kotlinx.android.synthetic.main.activity_home_screen.timerCountSecond
import java.text.DecimalFormat
import java.text.NumberFormat

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var myModelList: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        itemShow()
        menuItemRecyclerView()
        discountItem()
        timeCount()



    }

    private fun timeCount() {
        object : CountDownTimer(7200000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val f: NumberFormat = DecimalFormat("00")
                val hour = millisUntilFinished / 3600000 % 24
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                timerCountHours.text = f.format(hour)
                timerCountMinutes.text = f.format(min)
                timerCountSecond.text = f.format(sec)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timerCountHours.text = "00"
                timerCountMinutes.text = "00"
                timerCountSecond.text ="00"
                finish()
            }
        }.start()
    }


    private fun discountItem() {
        val discountList = ArrayList<DiscountDataClass>()
        var discountAdapter: DiscountAdapter?
                discountList.apply {
                    add(DiscountDataClass(R.drawable.burger,R.drawable.ic_promo,"Super hot","Meat - with sauce",R.drawable.ic_star,4.5,"(100+)"))
                    add(DiscountDataClass(R.drawable.snacks,R.drawable.ic_promo,"Combo hot","2 pieces - with sauce",R.drawable.ic_star,4.5,"(100+)"))
                    add(DiscountDataClass(R.drawable.burger,R.drawable.ic_promo,"Steak Beef","2 pieces - with sauce",R.drawable.ic_star,4.5,"(100+)"))
                    add(DiscountDataClass(R.drawable.snacks,R.drawable.ic_promo,"Super hot","Meat - with sauce",R.drawable.ic_star,4.5,"(100+)"))
                    add(DiscountDataClass(R.drawable.burger,R.drawable.ic_promo,"Super hot","Meat - with sauce",R.drawable.ic_star,4.5,"(100+)"))
                    add(DiscountDataClass(R.drawable.snacks,R.drawable.ic_promo,"Combo hot","2 pieces - with sauce",R.drawable.ic_star,4.5,"(100+)"))
                }

        discountAdapter = DiscountAdapter(discountList)
        discountRecyclerView.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        discountRecyclerView.adapter = discountAdapter
    }

    private fun menuItemRecyclerView() {
        val list = ArrayList<MenuDataClass>()
        var adapter: MenuRecyclerViewAdapter? = null
        list.apply {
            add(MenuDataClass(R.drawable.ic_icon_voucher, "Voucher"))
            add(MenuDataClass(R.drawable.ic_rice, "Rice"))
            add(MenuDataClass(R.drawable.ic_drink, "Drink"))
            add(MenuDataClass(R.drawable.ic_fastfood, "Fast food"))
            add(MenuDataClass(R.drawable.ic_bread, "Bread"))
        }

        adapter = MenuRecyclerViewAdapter(list)
        menuRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        menuRecyclerView.adapter = adapter
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