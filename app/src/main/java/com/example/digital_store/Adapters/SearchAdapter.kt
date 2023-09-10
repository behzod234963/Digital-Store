package uz.datatalim.digitalstore.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digital_store.Models.AllProductsModel
import com.example.digital_store.R

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(),Filterable {

    val list:ArrayList<AllProductsModel> = ArrayList()
    var oldList:ArrayList<AllProductsModel> = ArrayList()

    fun submitList(list:ArrayList<AllProductsModel>){

        this.list.clear()
        this.list.addAll(list)
        this.oldList=list
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search,parent,false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val l=list[position]
        if (holder is SearchViewHolder){

            holder.apply {

                ivImage.setImageResource(l.image)
                tvTitle.text=l.title
                tvType.text=l.type
                tvCost.text=l.cost
                tvReviews.text=l.reviews.toString()
                tvRating.text=l.rating.toString()

            }

        }

    }

    class SearchViewHolder(view:View):RecyclerView.ViewHolder(view){

        val ivImage:ImageView=view.findViewById(R.id.ivImage_search)
        val tvTitle:TextView=view.findViewById(R.id.tvTitle_search)
        val tvType:TextView=view.findViewById(R.id.tvType_search)
        val tvCost:TextView=view.findViewById(R.id.tvCost_search)
        val tvRating:TextView=view.findViewById(R.id.tvRating_search)
        val tvReviews:TextView=view.findViewById(R.id.tvReviews_search)

    }

    override fun getFilter(): Filter {
        return customFilter
    }

    var customFilter=object :Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {

            var newList:ArrayList<AllProductsModel> =ArrayList()
            if (p0.isNullOrEmpty()){

                newList.addAll(list)

            }else{

                for (i in list){

                    if (i.title.lowercase().contains(p0.toString().lowercase())){

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
            list.addAll(p1?.values as ArrayList<AllProductsModel>)
            notifyDataSetChanged()

        }
    }

}