package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentSplashBinding

class Splash : Fragment() {

    lateinit var navController: NavController
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({

            initView()

        },2000)

    }

    private fun initView() {

        findNavController().navigate(R.id.action_splash_to_mainActivity)
        navController.popBackStack()

    }
}