package com.example.anroiduiscreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.onboardscreen.FoodDeliveryDataClass

class ViewPagerAdapter(val foodDeliveryImageArrayList: ArrayList<FoodDeliveryDataClass>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val viewpagerHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false)
        return ViewPagerHolder(viewpagerHolder)

    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        foodDeliveryImageArrayList[position].imgFoodDelivery.let { holder.image.setImageResource(it) }
        holder.apply {
            titleBold.text = foodDeliveryImageArrayList[position].textBold
            description.text = foodDeliveryImageArrayList[position].textSimple
        }
    }

    override fun getItemCount(): Int {
        return foodDeliveryImageArrayList.size
    }

    class ViewPagerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.deliveryFood)
        val titleBold: TextView = view.findViewById(R.id.textDelivery)
        val description: TextView = view.findViewById(R.id.subTextDelivery)
    }
}