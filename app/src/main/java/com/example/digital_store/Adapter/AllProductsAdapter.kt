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

class AllProductsAdapter :RecyclerView.Adapter<AllProductsAdapter.AllProductsViewHolder>(){

    var onClick:((Int)->Unit)?=null

    val allProductsList:ArrayList<ProductsItem> = ArrayList()

    fun submitList(list:ArrayList<ProductsItem>){

        this.allProductsList.clear()
        this.allProductsList.addAll(list)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsViewHolder {

        return AllProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_all_products,parent,false))

    }

    override fun getItemCount(): Int {

        return allProductsList.size

    }

    override fun onBindViewHolder(holder: AllProductsViewHolder, position: Int) {

        val products=allProductsList[position]

        holder.apply {

            tvProductTitle.text=products.title
            Glide.with(ivProductImage).load(products.image).into(ivProductImage)
            tvProductCost.text=products.price.toString()
            tvProductRating.text=products.rating.toString()
            tvType.text=products.category

        }

    }

    class AllProductsViewHolder(view: View):RecyclerView.ViewHolder(view){

        val llProduct:LinearLayout=view.findViewById(R.id.llProduct)
        val ivProductImage:ImageView=view.findViewById(R.id.ivProductImage_allproducts)
        val tvProductTitle:TextView=view.findViewById(R.id.tvProductTitle_allproduct)
        val tvProductCost:TextView=view.findViewById(R.id.tvProductCost_allproduct)
        val tvProductRating:TextView=view.findViewById(R.id.tvProductRating_allproduct)
        val tvType:TextView=view.findViewById(R.id.tvType_allproducts)

    }

}