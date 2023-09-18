package com.example.digital_store.Fragments.Main

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.DataBase.SQLite.WIshListRepository
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Models.WishListObject
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.net.URL

class Details : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var products: ArrayList<ProductsItem>
    lateinit var navController: NavController
    lateinit var wishList: ArrayList<WishListObject>
    lateinit var url:URL
    lateinit var wishListRepository: WIshListRepository
    val args: DetailsArgs by navArgs()
    var detailsID = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        wishListRepository = WIshListRepository(requireActivity().application)
        binding.lavLoadingDetails.visibility = View.VISIBLE
        products = ArrayList()
        wishList = ArrayList()
        navController = NavController(requireContext())
        detailsID = args.DetailsId
        loadDetail(detailsID)

        binding.apply {

            ivBackDetails.setOnClickListener {

                findNavController().navigate(R.id.action_details_to_store)
                navController.popBackStack()

            }

            ivWishlistDetails.setOnClickListener {

                ivWishlistDetails.setImageResource(R.drawable.ic_heart_checked)
                val id = tvIdDetails.text
                val title=tvTitleDetails.text
                val price=tvPriceDetails.text
                val rating=tvRatingDetails.text
                val description=tvDescriptionDetails.text
                wishListRepository.saveProduct(
                    WishListObject(
                        id = id.toString().toInt(),
                        title=title.toString(),
                        price = price.toString(),
                        rating =rating.toString(),
                        description = description.toString(),
                        image = url.toString()
                    )
                )

            }

        }

    }


    //    Loading details
    private fun loadDetail(id: Int) {

        products = ArrayList()

        ApiClient.apiServis.getProductById(id).enqueue(object : Callback<ProductsItem> {
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {

                if (response.isSuccessful) {

                    binding.lavLoadingDetails.visibility = View.GONE
                    loadData(response.body()!!)
                    getURl(response.body()!!)

                }

            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {

                binding.lavLoadingDetails.visibility = View.GONE
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()

            }

        })

    }

    private fun getURl(body: ProductsItem) {

        url=URL(body.image)

    }

    //    Loading items into details
    private fun loadData(body: ProductsItem) {

        binding.apply {

            tvIdDetails.text = body.id.toString()
            Glide.with(this@Details).load(body.image).into(ivDetailsImageDetails).toString()
            tvTitleDetails.text = body.title
            tvPriceDetails.text = "${body.price.toString()} USD"
            tvRatingDetails.text = body.rating.toString()
            tvDescriptionDetails.text = body.description

        }

    }

}