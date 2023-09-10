package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentWishlistBinding

class Wishlist : Fragment() {

    lateinit var binding: FragmentWishlistBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentWishlistBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}