package com.example.anroiduiscreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.homescreen.DiscountDataClass

class DiscountAdapter(var discountItemList: ArrayList<DiscountDataClass>) : RecyclerView.Adapter<DiscountAdapter.MyViewHolderDiscount>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDiscount {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_discount_recyclerview, parent, false)
        return MyViewHolderDiscount(viewHolder)
    }

    override fun onBindViewHolder(holder: MyViewHolderDiscount, position: Int) {
        discountItemList[position].imageDiscount.let { holder.itemImage.setImageResource(it) }
        discountItemList[position].star.let { holder.rateStar.setImageResource(it) }
        discountItemList[position].imagePromo.let { holder.imagePromo.setImageResource(it) }
        holder.apply {
            highlightTitle.text = discountItemList[position].titles
            subSpecification.text = discountItemList[position].subTitle
            rateDigit.text = discountItemList[position].rating.toString()
            countRate.text = discountItemList[position].rateCount
        }
    }

    override fun getItemCount(): Int {
        return discountItemList.size
    }

    class MyViewHolderDiscount(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.coverImage)
        val imagePromo: ImageView = itemView.findViewById(R.id.promoImage)
        val highlightTitle: TextView = itemView.findViewById(R.id.superHot)
        val subSpecification: TextView = itemView.findViewById(R.id.subSpecification)
        val rateStar: ImageView = itemView.findViewById(R.id.rate)
        val rateDigit: TextView = itemView.findViewById(R.id.digitRate)
        val countRate: TextView = itemView.findViewById(R.id.rateCount)
    }
}