package com.solvabit.mate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IntrosliderAdaptor(private val introSlides: List<IntroSlide>):
    RecyclerView.Adapter<IntrosliderAdaptor.IntroSlideViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon =view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introslide: IntroSlide) {
            textTitle.text = introslide.title
            textDescription.text = introslide.description
            imageIcon.setImageResource(introslide.icon)
        }
    }
}