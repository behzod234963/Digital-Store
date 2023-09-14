package com.example.digital_store.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.R

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(),Filterable {

    val list:ArrayList<ProductsItem> = ArrayList()
    var oldList:ArrayList<ProductsItem> = ArrayList()
    var itemClick:((Int)->Unit)?=null

    fun submitList(list:ArrayList<ProductsItem>){

        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search,parent,false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val products=list[position]
        if (holder is SearchViewHolder){

            holder.apply {

                Glide.with(ivImage).load(products.image).into(ivImage)
                tvTitle.text=products.title
                tvType.text=products.category
                tvCost.text=products.price.toString()
                tvRating.text=products.rating.toString()

                llSearchClick.setOnClickListener {

                    itemClick?.invoke(position)

                }

            }

        }

    }

    class SearchViewHolder(view:View):RecyclerView.ViewHolder(view){

        val ivImage:ImageView=view.findViewById(R.id.ivImage_search)
        val tvTitle:TextView=view.findViewById(R.id.tvTitle_search)
        val tvType:TextView=view.findViewById(R.id.tvType_search)
        val tvCost:TextView=view.findViewById(R.id.tvCost_search)
        val tvRating:TextView=view.findViewById(R.id.tvRating_search)
        val llSearchClick:LinearLayout=view.findViewById(R.id.llSearchClick_search)

    }

    override fun getFilter(): Filter {
        return customFilter
    }

    var customFilter=object :Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {

            var newList:ArrayList<ProductsItem> =ArrayList()
            if (p0.isNullOrEmpty()){

                newList.addAll(list)

            }else{

                for (i in list){

                    if (i.title?.lowercase()!!.contains(p0.toString().lowercase())){

                        newList.add(i)

                    }

                }

            }

            val result=FilterResults()
            result.values=newList
            return result

        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

            list.clear()
            list.addAll(p1?.values as ArrayList<ProductsItem>)
            notifyDataSetChanged()

        }
    }

}