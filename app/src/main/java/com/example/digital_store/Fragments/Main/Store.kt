package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.digital_store.Adapter.AllProductsAdapter
import com.example.digital_store.Fragments.ViewPager.AllProducts
import com.example.digital_store.Fragments.ViewPager.WomensClothing
import com.example.digital_store.Fragments.ViewPager.Electronics
import com.example.digital_store.Fragments.ViewPager.Jewelery
import com.example.digital_store.Fragments.ViewPager.MensClothing
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentStoreBinding
import com.example.digital_store.Adapter.ViewPagerAdapter
import com.example.digital_store.Models.ProductsItem

class Store : Fragment(), com.example.digital_store.Navigation.Navigator {

    lateinit var binding: FragmentStoreBinding
    lateinit var vpTitle: ArrayList<String>
    lateinit var vpFragment: ArrayList<Fragment>
    lateinit var vpAdapter: ViewPagerAdapter
    lateinit var productsAdapter: AllProductsAdapter
    lateinit var products: ArrayList<ProductsItem>
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

    }

    //    Initialize data
    private fun initView(view: View) {

        productsAdapter = AllProductsAdapter()
        vpTitle = ArrayList()
        vpFragment = ArrayList()
        products = ArrayList()
        loadItems()
        vpAdapter = ViewPagerAdapter(vpFragment, vpTitle, childFragmentManager)
        val vpViewPager: ViewPager = view.findViewById(R.id.vpViewPager_store)
        binding.tlTabLayoutStore.setupWithViewPager(vpViewPager)
        vpViewPager.adapter = vpAdapter

        binding.ivSearchStore.setOnClickListener {

            findNavController().navigate(R.id.action_store_to_search)

        }

        binding.ivProfileStore.setOnClickListener {

            findNavController().navigate(R.id.action_store_to_settings)

        }

        binding.llCartStore.setOnClickListener {

            findNavController().navigate(R.id.action_store_to_cart)

        }


    }

    //    Loading Items
    private fun loadItems() {

        vpFragment = ArrayList()
        vpTitle = ArrayList()
        vpTitle.add("All")
        vpFragment.add(AllProducts(this))
        vpTitle.add("Jewelery")
        vpFragment.add(Jewelery(this))
        vpTitle.add("Electronics")
        vpFragment.add(Electronics(this))
        vpTitle.add("MensClothing")
        vpFragment.add(MensClothing(this))
        vpTitle.add("WomensClothing")
        vpFragment.add(WomensClothing(this))

    }

    //    Navigator
    override fun saveAction(actionID: Int, bundle: Bundle?) {

        when (actionID) {

            R.id.action_store_to_details -> {

                findNavController().navigate(R.id.action_store_to_details, bundle)

            }

        }

    }


}