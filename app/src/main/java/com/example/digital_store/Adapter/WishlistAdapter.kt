package com.example.digital_store.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digital_store.Models.RoomData
import com.example.digital_store.R

class WishlistAdapter :RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>(){

    val list:ArrayList<RoomData.WishList> = ArrayList()
    var itemClick:((Int)->Unit)?=null
    var deleteItem:((Int)->Unit) ? =null

    fun submitList(list: ArrayList<RoomData.WishList>){

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
            tvPrice.text= list.price
            tvRate.text= list.rating
            llClick.setOnClickListener {

                itemClick?.invoke(position)

            }
            llDelete.setOnClickListener {

                deleteItem?.invoke(position)

            }

        }

    }

    class WishlistViewHolder(view: View):RecyclerView.ViewHolder(view){

        val llClick:LinearLayout=view.findViewById(R.id.llClick_wishlist)
        var ivImage:ImageView=view.findViewById(R.id.ivImage_wishlist)
        val tvTitle:TextView=view.findViewById(R.id.tvTitle_wishlist)
        val tvPrice:TextView=view.findViewById(R.id.tvPrice_wishlist)
        val tvRate:TextView=view.findViewById(R.id.tvRating_wishlist)
        val llDelete:LinearLayout=view.findViewById(R.id.llDelete_wishlist)

    }

}