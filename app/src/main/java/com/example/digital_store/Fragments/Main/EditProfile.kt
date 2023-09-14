package com.example.digital_store.Fragments.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentEditProfileBinding


class EditProfile : Fragment() {

    lateinit var binding: FragmentEditProfileBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

//  Initialize data
    private fun initView() {

    navController=NavController(requireContext())

        binding.ivBackEdit.setOnClickListener {

            navController.popBackStack()

        }

    }

}