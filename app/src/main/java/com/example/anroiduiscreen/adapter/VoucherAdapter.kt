package com.example.anroiduiscreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.homescreen.VoucherDataClass

class VoucherAdapter(private val voucherItemList: ArrayList<VoucherDataClass>): RecyclerView.Adapter<VoucherAdapter.MyVoucherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVoucherViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_voucher, parent, false)
        return MyVoucherViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: MyVoucherViewHolder, position: Int) {
        holder.voucherImage.setImageResource(voucherItemList[position].imageVoucher)
    }

    override fun getItemCount(): Int {
       return voucherItemList.size
    }

    class MyVoucherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val voucherImage: ImageView = itemView.findViewById(R.id.imageVoucher)

    }


}