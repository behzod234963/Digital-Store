package com.example.digital_store.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.R

class MensAdapter :RecyclerView.Adapter<MensAdapter.JeweleryViewHolder>(){

    val mensClothing:ArrayList<ProductsItem> = ArrayList()
    var onClick:((Int)->Unit)?=null
    fun submitList(list:ArrayList<ProductsItem>){

        mensClothing.clear()
        mensClothing.addAll(list)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JeweleryViewHolder {
        return JeweleryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_mens,parent
            ,false))
    }

    override fun getItemCount(): Int {
        return mensClothing.size
    }

    override fun onBindViewHolder(holder: JeweleryViewHolder, position: Int) {

        val list=mensClothing[position]

        holder.apply {

            Glide.with(ivImage).load(list.image).into(ivImage)
            tvTitle.text=list.title
            tvPrice.text="${list. price } USD"
            tvRating.text=list.rating?.rate.toString()
            tvCategory.text=list.category

            llJewRoot.setOnClickListener {

                onClick?.invoke(position)

            }

        }

    }

    class JeweleryViewHolder(view: View): RecyclerView.ViewHolder(view){

        val llJewRoot: LinearLayout =view.findViewById(R.id.llRoot_mens)
        val ivImage: ImageView =view.findViewById(R.id.ivImage_mens)
        val tvTitle: TextView =view.findViewById(R.id.tvTitle_mens)
        val tvCategory: TextView =view.findViewById(R.id.tvCategory_mens)
        val tvPrice: TextView =view.findViewById(R.id.tvPrice_mens)
        val tvRating: TextView =view.findViewById(R.id.tvRating_mens)

    }

}