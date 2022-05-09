package com.example.anroiduiscreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.homescreen.NearYouLocationDataClass


class NearYouLocationAdapter(private val nearYouLocationList: ArrayList<NearYouLocationDataClass>): RecyclerView.Adapter<NearYouLocationAdapter.NearYouLocationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearYouLocationHolder {
        val viewpagerHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_nearyou, parent, false)
        return NearYouLocationHolder(viewpagerHolder)
    }

    override fun onBindViewHolder(holder: NearYouLocationHolder, position: Int) {
        nearYouLocationList[position].imageBanner.let { holder.bannerImage.setImageResource(it) }
        nearYouLocationList[position].imageStar.let { holder.rateImage.setImageResource(it) }
        nearYouLocationList[position].imageLocation.let { holder.imageLocation.setImageResource(it) }
        holder.apply {
            titleFood.text = nearYouLocationList[position].textTitle
            subTitleFood.text =  nearYouLocationList[position].subTitle
            textRateDigit.text = nearYouLocationList[position].textRating.toString()
            textRateCount.text = nearYouLocationList[position].textRateCount
            textDivision.text = nearYouLocationList[position].divisionSpace
            textKilometer.text = nearYouLocationList[position].textKilometer
        }
    }

    override fun getItemCount(): Int {
        return nearYouLocationList.size
    }

    class NearYouLocationHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bannerImage: ImageView = view.findViewById(R.id.nearYouImageFood)
        val titleFood: TextView = view.findViewById(R.id.foodTitle)
        val subTitleFood: TextView = view.findViewById(R.id.subTitles)
        val rateImage: ImageView = view.findViewById(R.id.rateImage)
        val textRateDigit: TextView = view.findViewById(R.id.textDigitRate)
        val textRateCount: TextView = view.findViewById(R.id.rateCount)
        val textDivision: TextView = view.findViewById(R.id.division)
        val imageLocation: ImageView = view.findViewById(R.id.locationImage)
        val textKilometer: TextView = view.findViewById(R.id.textKilometer)
    }
}