package com.example.digital_store.Fragments.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.digital_store.Models.AllProductsModel
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentAllProductsBinding
import uz.datatalim.digitalstore.Adapters.AllProductsAdapter


class AllProducts : Fragment() {

    lateinit var binding: FragmentAllProductsBinding
    lateinit var allProductsList: ArrayList<AllProductsModel>
    lateinit var allProductsAdapter: AllProductsAdapter

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

        loadProducts()
        allProductsAdapter = AllProductsAdapter()
        binding.rvAllProductsItemall.adapter = allProductsAdapter
        allProductsAdapter.submitList(allProductsList)


    }

    private fun loadProducts() {

        allProductsList = ArrayList()
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))
        allProductsList.add(
            AllProductsModel(
                R.drawable.pic_laptop,
                "lenovo thinkpad e15 g2 black",
                "1000",
                5.0,
                500, "Electronics"
            ))

    }
}