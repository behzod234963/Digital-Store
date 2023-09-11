package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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

class Store : Fragment() {

    lateinit var binding: FragmentStoreBinding
    lateinit var vpTitle:ArrayList<String>
    lateinit var vpFragment:ArrayList<Fragment>
    lateinit var vpAdapter: ViewPagerAdapter
    lateinit var navController: NavController
    lateinit var productsAdapter:AllProductsAdapter
    lateinit var products:ArrayList<ProductsItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentStoreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

    }

    private fun initView(view: View) {

        vpTitle= ArrayList()
        vpFragment=ArrayList()
        products=ArrayList()
        loadItems()
        navController=NavController(requireContext())
        vpAdapter= ViewPagerAdapter(vpFragment, vpTitle, childFragmentManager)
        val vpViewPager: ViewPager =view.findViewById(R.id.vpViewPager_store)
        binding.tlTabLayoutStore.setupWithViewPager(vpViewPager)
        vpViewPager.adapter=vpAdapter
        productsAdapter= AllProductsAdapter()

        var allProducts=AllProducts()


        allProducts.allProductsAdapter.onClick={position->

            findNavController().navigate(R.id.action_store_to_details, bundleOf("id" to products[position].id))

        }

        binding.ivSearchStore.setOnClickListener {

            findNavController().navigate(R.id.action_store_to_search)
            navController.popBackStack()

        }

        binding.ivProfileStore.setOnClickListener {

            findNavController().navigate(R.id.action_store_to_settings)
            navController.popBackStack()

        }

        binding.ivCartStore.setOnClickListener {

            findNavController().navigate(R.id.action_store_to_cart)
            navController.popBackStack()

        }

    }

    private fun loadItems() {

        vpFragment= ArrayList()
        vpTitle=ArrayList()
        vpTitle.add("All")
        vpFragment.add(AllProducts())
        vpTitle.add("Jewelery")
        vpFragment.add(Jewelery())
        vpTitle.add("Electronics")
        vpFragment.add(Electronics())
        vpTitle.add("MensClothing")
        vpFragment.add(MensClothing())
        vpTitle.add("WomensClothing")
        vpFragment.add(WomensClothing())

    }
}