package com.example.digital_store.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digital_store.Fragments.ViewPager.Jewelery
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.R

class JeweleryAdapter :RecyclerView.Adapter<JeweleryAdapter.JeweleryViewHolder>(){

    val jewelery:ArrayList<ProductsItem> = ArrayList()
    var onClick:((Int)->Unit)?=null
    fun submitList(list:ArrayList<ProductsItem>){

        jewelery.clear()
        jewelery.addAll(list)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JeweleryViewHolder {
        return JeweleryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_jewelery,parent
        ,false))
    }

    override fun getItemCount(): Int {
        return jewelery.size
    }

    override fun onBindViewHolder(holder: JeweleryViewHolder, position: Int) {

        val list=jewelery[position]

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

    class JeweleryViewHolder(view: View):RecyclerView.ViewHolder(view){

        val llJewRoot: LinearLayout =view.findViewById(R.id.llJeweleryRoot)
        val ivImage:ImageView=view.findViewById(R.id.ivJeweleryImage_jewelery)
        val tvTitle:TextView=view.findViewById(R.id.tvJeweleryTitle_jewelery)
        val tvCategory:TextView=view.findViewById(R.id.tvCategory_jewelery)
        val tvPrice:TextView=view.findViewById(R.id.tvJeweleryPrice_jewelery)
        val tvRating:TextView=view.findViewById(R.id.tvJeweleryRating_jewelery)

    }

}