package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.digital_store.Fragments.ViewPager.AllProducts
import com.example.digital_store.Fragments.ViewPager.WomensClothing
import com.example.digital_store.Fragments.ViewPager.Electronics
import com.example.digital_store.Fragments.ViewPager.Jewelery
import com.example.digital_store.Fragments.ViewPager.MensClothing
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentStoreBinding
import uz.datatalim.digitalstore.Adapters.ViewPagerAdapter

class Store : Fragment() {

    lateinit var binding: FragmentStoreBinding
    lateinit var vpTitle:ArrayList<String>
    lateinit var vpFragment:ArrayList<Fragment>
    lateinit var vpAdapter: ViewPagerAdapter

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
        loadItems()
        vpAdapter= ViewPagerAdapter(vpFragment, vpTitle, childFragmentManager)
        val vpViewPager: ViewPager =view.findViewById(R.id.vpViewPager_store)
        binding.tlTabLayoutStore.setupWithViewPager(vpViewPager)
        vpViewPager.adapter=vpAdapter

        binding.ivSearchStore.setOnClickListener {

            findNavController().navigate(R.id.action_store_to_search)

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