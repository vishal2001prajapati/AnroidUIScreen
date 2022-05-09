package com.example.anroiduiscreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.homescreen.MenuDataClass

class MenuRecyclerViewAdapter(var itemList: ArrayList<MenuDataClass>) : RecyclerView.Adapter<MenuRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_recyclerview, parent, false)
        return MyViewHolder(viewHolder)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        itemList[position].images?.let { holder.itemImage.setImageResource(it) }
        holder.itemText.text = itemList[position].strText
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.itemImageMenu)
        val itemText: TextView = itemView.findViewById(R.id.menuText)
    }
}