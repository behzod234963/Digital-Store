package com.example.digital_store.Fragments.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.databinding.FragmentAllProductsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.digital_store.Adapter.AllProductsAdapter
import com.example.digital_store.R


class AllProducts : Fragment() {

    lateinit var binding: FragmentAllProductsBinding
    lateinit var allProductsList: ArrayList<ProductsItem>
    var allProductsAdapter: AllProductsAdapter=AllProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {

        allProductsAdapter=AllProductsAdapter()
        allProductsList= ArrayList()
        loadProducts()
        allProductsAdapter = AllProductsAdapter()
        binding.rvAllProductsItemall.adapter = allProductsAdapter
        allProductsAdapter.submitList(allProductsList)


    }

    private fun loadProducts() {

        ApiClient.api_servis.getAllProducts().enqueue(object :Callback<ArrayList<ProductsItem>>{
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {

                if (response.isSuccessful){

                    allProductsList.clear()
                    allProductsList.addAll(response.body()!!)
                    allProductsAdapter.submitList(allProductsList)

                }

            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })

    }
}