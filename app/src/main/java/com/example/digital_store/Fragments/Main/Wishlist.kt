package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.digital_store.Adapter.WishlistAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Navigation.Navigator
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentWishlistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Wishlist : Fragment(),Navigator {

    lateinit var wishAdapter:WishlistAdapter
    lateinit var products:ArrayList<ProductsItem>

    lateinit var binding: FragmentWishlistBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentWishlistBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

//    Initialize data
    private fun initView() {

        binding.lavLoadingWishlist.visibility=View.VISIBLE
        wishAdapter= WishlistAdapter()
        products=ArrayList()
        binding.apply {

            rvWishlist.adapter=wishAdapter
            loadWishlist()
            wishAdapter.itemClick={position->

                findNavController().navigate(R.id.action_wishlist_to_details, bundleOf("DetailsId" to products[position].id))

            }

        }

    }

//    Loading items
    private fun loadWishlist() {

        ApiClient.apiServis.getAllProducts().enqueue(object :Callback<ArrayList<ProductsItem>>{
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {

                if (response.isSuccessful){

                    binding.lavLoadingWishlist.visibility=View.GONE
                    products.clear()
                    products.addAll(response.body()!!)
                    wishAdapter.submitList(products)

                }

            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {

                binding.lavLoadingWishlist.visibility=View.GONE

            }


        })

    }

//    Navigator
    override fun saveAction(actionID: Int, bundle: Bundle?) {

        when(actionID){

            R.id.action_wishlist_to_details->{

                findNavController().navigate(R.id.action_wishlist_to_details)

            }

        }

    }

}