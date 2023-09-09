package uz.datatalim.digitalstore.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digital_store.Models.AllProductsModel
import com.example.digital_store.R

class AllProductsAdapter :RecyclerView.Adapter<AllProductsAdapter.AllProductsViewHolder>(){

    val allProductsList:ArrayList<AllProductsModel> = ArrayList()

    fun submitList(list:ArrayList<AllProductsModel>){

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

        val l=allProductsList[position]

        holder.apply {

            ivProductImage.setImageResource(l.image)
            tvProductTitle.text=l.title
            tvProductCost.text=l.cost
            tvProductRating.text=l.rating.toString()
            tvProductReviews.text=l.reviews.toString()

        }

    }

    class AllProductsViewHolder(view: View):RecyclerView.ViewHolder(view){

        val llProduct:LinearLayout=view.findViewById(R.id.llProduct)
        val ivProductImage:ImageView=view.findViewById(R.id.ivProductImage_allproducts)
        val tvProductTitle:TextView=view.findViewById(R.id.tvProductTitle_allproduct)
        val tvProductCost:TextView=view.findViewById(R.id.tvProductCost_allproduct)
        val tvProductRating:TextView=view.findViewById(R.id.tvProductRating_allproduct)
        val tvProductReviews:TextView=view.findViewById(R.id.tvProductreviews_allproduct)
        val tvType:TextView=view.findViewById(R.id.tvType_allproducts)

    }

}