package com.example.digital_store.Fragments.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.databinding.FragmentAllProductsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.digital_store.Adapter.AllProductsAdapter
import com.example.digital_store.Fragments.Main.Details
import com.example.digital_store.R
import com.example.digital_store.SharedPreferences


class AllProducts : Fragment() {

    lateinit var binding: FragmentAllProductsBinding
    lateinit var allProductsList: ArrayList<ProductsItem>
    lateinit var productsAdapter: AllProductsAdapter

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

        productsAdapter=AllProductsAdapter()
        allProductsList= ArrayList()
        binding.rvAllProductsItemall.adapter = productsAdapter
        loadProducts()
        productsAdapter.submitList(allProductsList)

        productsAdapter.onClick={position->

            val id=allProductsList[position].id
            val putId=SharedPreferences(requireContext()).saveId(id!!)
            findNavController().navigate(R.id.action_global_allProducts)

        }

    }

    private fun loadProducts() {

        ApiClient.apiServis.getAllProducts().enqueue(object :Callback<ArrayList<ProductsItem>>{
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {

                if (response.isSuccessful){

                    allProductsList.clear()
                    allProductsList.addAll(response.body()!!)
                    productsAdapter.submitList(allProductsList)

                }

            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {

                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()

            }


        })

    }

}