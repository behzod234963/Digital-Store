package com.example.digital_store.Fragments.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.digital_store.Adapter.AllProductsAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentJeweleryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Jewelery : Fragment() {

    lateinit var binding: FragmentJeweleryBinding
    lateinit var jeweleryAdapter:AllProductsAdapter
    lateinit var products:ArrayList<ProductsItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentJeweleryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {

        products= ArrayList()
        loadProducts()
        jeweleryAdapter= AllProductsAdapter()
        binding.rvJewelery.adapter=AllProductsAdapter()

        jeweleryAdapter.onClick={position->



        }

    }

    private fun loadProducts() {

        ApiClient.api_servis.getCategoryByName("jewelery").enqueue(object :Callback<ArrayList<ProductsItem>>{
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {

                if (response.isSuccessful){

                    products.clear()
                    products.addAll(response.body()!!)
                    jeweleryAdapter.submitList(products)

                }

            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })

    }

}