package com.example.digital_store.Fragments.BottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.digital_store.Fragments.ViewPager.AllProducts
import com.example.digital_store.Fragments.ViewPager.Gaming
import com.example.digital_store.Fragments.ViewPager.Laptops
import com.example.digital_store.Fragments.ViewPager.PC
import com.example.digital_store.Fragments.ViewPager.Phones
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
        binding= FragmentStoreBinding.inflate(layoutInflater,container,false)
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
        vpAdapter=ViewPagerAdapter(vpFragment,vpTitle,childFragmentManager)
        val vpViewPager: ViewPager =view.findViewById(R.id.vpViewPager_store)
        binding.tlTabLayoutStore.setupWithViewPager(vpViewPager)
        vpViewPager.adapter=vpAdapter

    }

    private fun loadItems() {

        vpFragment= ArrayList()
        vpTitle=ArrayList()
        vpTitle.add("AllProducts")
        vpFragment.add(AllProducts())
        vpTitle.add("PC")
        vpFragment.add(PC())
        vpTitle.add("Laptops")
        vpFragment.add(Laptops())
        vpTitle.add("Phones")
        vpFragment.add(Phones())
        vpTitle.add("Gaming")
        vpFragment.add(Gaming())

    }
}