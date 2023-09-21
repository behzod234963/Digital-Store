package com.example.digital_store.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digital_store.Models.RoomData
import com.example.digital_store.R
import com.example.digital_store.Utils.CartDiffUtils

class CartAdapter :RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    var deleteItem:((Int)->Unit)?=null
    var onClick:((Int)->Unit)?=null
    var plus:((Int)->Unit)?=null
    var minus:((Int)->Unit)?=null
    var cartslist:ArrayList<RoomData.Cart> =ArrayList()

    fun submitList(newList:ArrayList<RoomData.Cart>){

        val diffUtil=CartDiffUtils(cartslist,newList)
        val result=DiffUtil.calculateDiff(diffUtil)
        cartslist=newList
        result.dispatchUpdatesTo(this)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CartViewHolder{

        return CartViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart,parent,false))

    }

    override fun getItemCount()=cartslist.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val cart=cartslist[position]

        holder.apply {

            id.text=cart.id.toString()

            llCartClick.setOnClickListener {

                onClick?.invoke(position)

            }

            Glide.with(ivImage).load(cart.image).into(ivImage)
            tvTitle.text=cart.title

            if (cart.id!! in 1..4){

                tvCategory.text="Men's clothing"

            }else if (cart.id in (5..8)){

                tvCategory.text="Jewelery"

            }else if (cart.id in (9..14)){

                tvCategory.text="Electronics"

            }else{

                tvCategory.text="Women's clothing"

            }

            ivMinus.setOnClickListener {

                if (cart.count>1){

                    cart.count--
                    val countList=ArrayList<RoomData.Cart>()
                    submitList(countList)
                    minus?.invoke(position)

                }

            }

            ivPlus.setOnClickListener {

                cart.count++

                val countList=ArrayList<RoomData.Cart>()
                submitList(countList)
                plus?.invoke(position)

            }

            ivDelete.setOnClickListener {

                deleteItem?.invoke(position)

            }

            tvPrice.text= cart.price
            tvCountCart.text=cart.count.toString()

        }

    }

    class CartViewHolder(view: View):RecyclerView.ViewHolder(view){

        val id:TextView=view.findViewById(R.id.tvID_cart)
        val llCartClick:LinearLayout=view.findViewById(R.id.llCartClick_cart)
        val ivImage:ImageView=view.findViewById(R.id.ivImage_cart)
        val tvTitle:TextView=view.findViewById(R.id.tvTitle_cart)
        val tvCategory:TextView=view.findViewById(R.id.tvCategory_cart)
        val tvPrice:TextView=view.findViewById(R.id.tvPrice_cart)
        val ivMinus:ImageView=view.findViewById(R.id.ivMinus_cart)
        val tvCountCart:TextView=view.findViewById(R.id.tvCartCount_cart)
        val ivPlus:ImageView=view.findViewById(R.id.ivPlus_cart)
        val ivDelete:ImageView=view.findViewById(R.id.ivDelete_cart)

    }

}