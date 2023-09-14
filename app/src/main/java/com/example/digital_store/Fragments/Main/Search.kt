package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digital_store.Adapter.SearchAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Navigation.Navigator
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Search : Fragment() {

    lateinit var navController: NavController
    lateinit var binding: FragmentSearchBinding
    lateinit var list: ArrayList<ProductsItem>
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        list = ArrayList()
        loadList()
        searchAdapter = SearchAdapter()
        binding.rvSearchSearch.adapter = searchAdapter
        binding.rvSearchSearch.layoutManager = LinearLayoutManager(requireContext())
        searchAdapter.submitList(list)

        binding.etSearchSearch.addTextChangedListener {

            searchAdapter.filter.filter(it)
            searchAdapter.submitList(list)

        }

        searchAdapter.itemClick = { position ->

            findNavController().navigate(
                R.id.action_search_to_details,
                bundleOf("DetailsId" to list[position].id)
            )

        }


    }

    //    Load items
    private fun loadList() {

        ApiClient.apiServis.getAllProducts().enqueue(object : Callback<ArrayList<ProductsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {

                if (response.isSuccessful) {

                    list.clear()
                    list.addAll(response.body()!!)
                    searchAdapter.submitList(list)

                }

            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}