package com.example.digital_store.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.R

class WishlistAdapter :RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>(){

    val list:ArrayList<ProductsItem> = ArrayList()
    var itemClick:((Int)->Unit)?=null

    fun submitList(list:ArrayList<ProductsItem>){

        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {

        return WishlistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist,parent,false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {

        val list=list[position]
        holder.apply {

            Glide.with(ivImage).load(list.image).into(ivImage)
            tvTitle.text=list.title
            tvCategory.text=list.category
            tvPrice.text=list.price.toString()
            tvRate.text=list.rating?.rate.toString()
            llClick.setOnClickListener {

                itemClick?.invoke(position)

            }

            ivDelete.setOnClickListener {

                itemClick?.invoke(position)

            }

        }

    }

    class WishlistViewHolder(view: View):RecyclerView.ViewHolder(view){

        val llClick:LinearLayout=view.findViewById(R.id.llClick_wishlist)
        val ivImage:ImageView=view.findViewById(R.id.ivImage_wishlist)
        val tvTitle:TextView=view.findViewById(R.id.tvTitle_wishlist)
        val tvCategory:TextView=view.findViewById(R.id.tvCategory_wishlist)
        val tvPrice:TextView=view.findViewById(R.id.tvPrice_wishlist)
        val tvRate:TextView=view.findViewById(R.id.tvRating_wishlist)
        val ivDelete:ImageView=view.findViewById(R.id.ivDelete_wishlist)

    }

}