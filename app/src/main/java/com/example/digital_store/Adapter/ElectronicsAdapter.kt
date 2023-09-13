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

class ElectronicsAdapter :RecyclerView.Adapter<ElectronicsAdapter.ElectronicsViewHolder>(){

    val electronics:ArrayList<ProductsItem> = ArrayList()
    var onClick:((Int)->Unit)?=null
    fun submitList(list:ArrayList<ProductsItem>){

        electronics.clear()
        electronics.addAll(list)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectronicsViewHolder {
        return ElectronicsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_electronics,parent
            ,false))
    }

    override fun getItemCount(): Int {
        return electronics.size
    }

    override fun onBindViewHolder(holder: ElectronicsViewHolder, position: Int) {

        val list=electronics[position]

        holder.apply {

            Glide.with(ivImage).load(list.image).into(ivImage)
            tvTitle.text=list.title
            tvPrice.text="${list. price } USD"
            tvRating.text=list.rating?.rate.toString()
            tvCategory.text=list.category

            llRoot.setOnClickListener {

                onClick?.invoke(position)

            }

        }

    }

    class ElectronicsViewHolder(view: View):RecyclerView.ViewHolder(view){

        val llRoot: LinearLayout =view.findViewById(R.id.llElectronicsRoot)
        val ivImage: ImageView =view.findViewById(R.id.ivElectronicsImage_electronics)
        val tvTitle: TextView =view.findViewById(R.id.tvElectronicsTitle_electronics)
        val tvCategory: TextView =view.findViewById(R.id.tvCategory_electronics)
        val tvPrice: TextView =view.findViewById(R.id.tvPrice_electronics)
        val tvRating: TextView =view.findViewById(R.id.tvRating_electronics)

    }

}