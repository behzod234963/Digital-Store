package com.example.digital_store.Fragments.BottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.digital_store.Models.AllProductsModel
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentSearchBinding
import uz.datatalim.digitalstore.adapters.SearchAdapter


class Search : Fragment() {

    lateinit var binding: FragmentSearchBinding
    lateinit var list:ArrayList<AllProductsModel>
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {

        loadList()
        searchAdapter= SearchAdapter(list)
        binding.rvSearchSearch.adapter=searchAdapter


    }

    private fun loadList() {

        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))
        list=ArrayList()
        list.add(AllProductsModel(
            R.drawable.pic_laptop,
            "lenovo thinkpad e15",
            "Cost: 1000 USD",
            5.0,
            500,
            "Laptops"))

    }

}