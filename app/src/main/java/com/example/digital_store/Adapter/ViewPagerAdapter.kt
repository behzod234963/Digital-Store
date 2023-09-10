package com.example.digital_store.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter (
    val vpFragment:ArrayList<Fragment>,
    val vpTitle:ArrayList<String>,
    val fragment:FragmentManager):FragmentPagerAdapter(fragment){
    override fun getCount(): Int {

        return vpFragment.size

    }

    override fun getItem(position: Int): Fragment {

        return vpFragment[position]

    }

    override fun getPageTitle(position: Int): CharSequence? {

        return vpTitle[position]

    }

}