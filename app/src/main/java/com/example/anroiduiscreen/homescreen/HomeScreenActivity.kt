package com.example.anroiduiscreen.homescreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.adapter.DiscountAdapter
import com.example.anroiduiscreen.adapter.MenuRecyclerViewAdapter
import com.example.anroiduiscreen.adapter.NearYouLocationAdapter
import com.example.anroiduiscreen.adapter.VoucherAdapter
import kotlinx.android.synthetic.main.activity_home_screen.timerCountHours
import kotlinx.android.synthetic.main.activity_home_screen.timerCountMinutes
import kotlinx.android.synthetic.main.activity_home_screen.timerCountSecond
import java.text.DecimalFormat
import java.text.NumberFormat

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var myModelList: ArrayList<Int>
    var voucherList: ArrayList<VoucherDataClass> = ArrayList()
    lateinit var nearYouRecyclerView: RecyclerView
    lateinit var voucherRecyclerView: RecyclerView
    lateinit var discountRecyclerView: RecyclerView
    lateinit var menuRecyclerView: RecyclerView
    lateinit var imageSwipeViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        nearYouRecyclerView = findViewById(R.id.nearYouRecyclerView)
        voucherRecyclerView = findViewById(R.id.voucherRecyclerView)
        menuRecyclerView = findViewById(R.id.menuRecyclerView)
        imageSwipeViewPager = findViewById(R.id.imageSwipeViewPager)
        discountRecyclerView = findViewById(R.id.discountRecyclerView)
        itemShow()
        menuItemRecyclerView()
        discountItem()
        timeCount()
        voucherDiscount()
        nearYouDataShow()




    }

    private fun nearYouDataShow() {
        val nearYouLocationList = ArrayList<NearYouLocationDataClass>()
        val adapter: NearYouLocationAdapter?
        nearYouLocationList.apply {
            add(NearYouLocationDataClass(R.drawable.burger,getString(R.string.combination_fried_rice),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.5,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._1_2km)))
            add(NearYouLocationDataClass(R.drawable.snacks,getString(R.string.wonton_egg_noodle_soup),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.3,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._5_00m)))
            add(NearYouLocationDataClass(R.drawable.burger,getString(R.string.chicken_black_bean),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.5,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._1_2km)))
            add(NearYouLocationDataClass(R.drawable.snacks,getString(R.string.combination_fried_rice),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.3,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._5_00m)))
            add(NearYouLocationDataClass(R.drawable.burger,getString(R.string.wonton_egg_noodle_soup),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.5,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._5_00m)))
            add(NearYouLocationDataClass(R.drawable.snacks,getString(R.string.chicken_black_bean),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.3,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._5_00m)))
            add(NearYouLocationDataClass(R.drawable.burger,getString(R.string.combination_fried_rice),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.5,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._1_2km)))
            add(NearYouLocationDataClass(R.drawable.snacks,getString(R.string.wonton_egg_noodle_soup),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.3,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._5_00m)))
            add(NearYouLocationDataClass(R.drawable.burger,getString(R.string.chicken_black_bean),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.5,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._1_2km)))
            add(NearYouLocationDataClass(R.drawable.snacks,getString(R.string.combination_fried_rice),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.3,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._5_00m)))
            add(NearYouLocationDataClass(R.drawable.burger,getString(R.string.wonton_egg_noodle_soup),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.5,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._5_00m)))
            add(NearYouLocationDataClass(R.drawable.snacks,getString(R.string.chicken_black_bean),getString(R.string.shrimp_ham_mix_vegetable),R.drawable.ic_star,4.3,getString(R.string._100),getString(R.string.space_division),R.drawable.ic_delivery_location,getString(R.string._5_00m)))
        }
        adapter = NearYouLocationAdapter(nearYouLocationList)
        nearYouRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        nearYouRecyclerView.adapter = adapter
    }

    private fun voucherDiscount() {
        voucherList.apply {
            add(VoucherDataClass(R.drawable.ic_voucher))
            add(VoucherDataClass(R.drawable.ic_voucher_fifteen))
            add(VoucherDataClass(R.drawable.ic_voucher_ten))
            add(VoucherDataClass(R.drawable.ic_vouchergray))
        }
        val adapter = VoucherAdapter(voucherList)
        voucherRecyclerView.adapter = adapter

    }

    private fun timeCount() {
        object : CountDownTimer(7200000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val f: NumberFormat = DecimalFormat(getString(R.string.zero))
                val hour = millisUntilFinished / 3600000 % 24
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                timerCountHours.text = f.format(hour)
                timerCountMinutes.text = f.format(min)
                timerCountSecond.text = f.format(sec)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timerCountHours.text = getString(R.string.zero)
                timerCountMinutes.text = getString(R.string.zero)
                timerCountSecond.text = getString(R.string.zero)
                finish()
            }
        }.start()
    }

    private fun discountItem() {
        val discountList = ArrayList<DiscountDataClass>()
        val discountAdapter: DiscountAdapter?
        discountList.apply {
            add(DiscountDataClass(R.drawable.burger, R.drawable.ic_promo, getString(R.string.super_hot), getString(R.string.meat_with_sauce), R.drawable.ic_star, 4.5, getString(R.string._100)))
            add(DiscountDataClass(R.drawable.snacks, R.drawable.ic_promo, getString(R.string.combo_hot), getString(R.string._2_pieces_with_sauce), R.drawable.ic_star, 4.5, getString(R.string._100)))
            add(DiscountDataClass(R.drawable.burger, R.drawable.ic_promo, getString(R.string.steak_beef), getString(R.string._2_pieces_with_sauce), R.drawable.ic_star, 4.5, getString(R.string._100)))
            add(DiscountDataClass(R.drawable.snacks, R.drawable.ic_promo, getString(R.string.super_hot), getString(R.string.meat_with_sauce), R.drawable.ic_star, 4.5, getString(R.string._100)))
            add(DiscountDataClass(R.drawable.burger, R.drawable.ic_promo, getString(R.string.super_hot), getString(R.string.meat_with_sauce), R.drawable.ic_star, 4.5, getString(R.string._100)))
            add(DiscountDataClass(R.drawable.snacks, R.drawable.ic_promo, getString(R.string.combo_hot), getString(R.string._2_pieces_with_sauce), R.drawable.ic_star, 4.5, getString(R.string._100)))
        }

        discountAdapter = DiscountAdapter(discountList)
        discountRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        discountRecyclerView.adapter = discountAdapter
    }

    private fun menuItemRecyclerView() {
        val list = ArrayList<MenuDataClass>()
        var adapter: MenuRecyclerViewAdapter? = null
        list.apply {
            add(MenuDataClass(R.drawable.ic_icon_voucher, getString(R.string.voucher)))
            add(MenuDataClass(R.drawable.ic_rice, getString(R.string.rice)))
            add(MenuDataClass(R.drawable.ic_drink, getString(R.string.drink)))
            add(MenuDataClass(R.drawable.ic_fastfood, getString(R.string.fast_food)))
            add(MenuDataClass(R.drawable.ic_bread, getString(R.string.bread)))
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