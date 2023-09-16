package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.digital_store.Adapter.WishlistAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.DataBase.SQLite.WIshListRepository
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Navigation.Navigator
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentWishlistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Wishlist : Fragment(), Navigator {

    lateinit var wishAdapter: WishlistAdapter
    lateinit var products: ArrayList<ProductsItem>
    lateinit var wIshListRepository: WIshListRepository
     var wishID=0
    lateinit var wishArgs:WishlistArgs
    lateinit var binding: FragmentWishlistBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWishlistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        wishArgs=WishlistArgs(wishID)
        wishID=wishArgs.wishID
        wIshListRepository = WIshListRepository(requireActivity().application)
        wishAdapter = WishlistAdapter()
        products = ArrayList()
        binding.rvWishlist.adapter = wishAdapter
        loadWishlist(wishID)
        wishAdapter.submitList(products)
        binding.apply {

            wishAdapter.itemClick = { position ->

                findNavController().navigate(
                    R.id.action_wishlist_to_details,
                    bundleOf("DetailsId" to products[position].id)
                )

            }

            ivClearWishlist.setOnClickListener {

                products.clear()
                wishAdapter.submitList(products)

            }

        }

    }


    //    Loading items
    private fun loadWishlist(id:Int) {

        ApiClient.apiServis.getProductById(id).enqueue(object :Callback<ProductsItem>{
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {

                if (response.isSuccessful){

                    wishAdapter.submitList(products)

                }

            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }


    //    Delete object
    fun deleteProduct(id: Int) {

        wIshListRepository.deleteById(id)

    }

    //    Navigator
    override fun saveAction(actionID: Int, bundle: Bundle?) {

        when (actionID) {

            R.id.action_wishlist_to_details -> {

                findNavController().navigate(R.id.action_wishlist_to_details)

            }

        }

    }

}