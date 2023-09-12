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

class DetailsAdapter:RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    var onClick:((Int)->Unit)?=null
    val details:ArrayList<ProductsItem> =ArrayList()

    fun submitList(list:ArrayList<ProductsItem>){

        details.clear()
        details.addAll(list)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {

        return DetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_details,parent,false))

    }

    override fun getItemCount(): Int {

        return details.size

    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {

        val details=details[position]

        holder.apply {

            Glide.with(ivImage).load(details.image).into(ivImage)
            tvPrice.text=details.price.toString()
            tvTitle.text=details.title
            tvRating.text=details.rating.toString()
            tvDescription.text=details.description

            llDetails.setOnClickListener {

                onClick?.invoke(position)

            }

        }

    }


    class DetailsViewHolder(view: View):RecyclerView.ViewHolder(view){

        val llDetails:LinearLayout=view.findViewById(R.id.llDetails_details)
        val ivImage:ImageView=view.findViewById(R.id.ivDetailsImage_details)
        val tvPrice:TextView=view.findViewById(R.id.tvPrice_details)
        val tvTitle:TextView=view.findViewById(R.id.tvTitle_details)
        val tvRating:TextView=view.findViewById(R.id.tvRating_details)
        val tvDescription:TextView=view.findViewById(R.id.tvDescription_details)

    }

}