package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentSignUpStepTwoBinding

class SignUpStepTwo : Fragment() {

    lateinit var binding: FragmentSignUpStepTwoBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpStepTwoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }


    //    Initialize data
    private fun initView() {

        navController = NavController(requireContext())

        binding.apply {

            btnStartSignUptwo.setOnClickListener {

                if (etFirstNameSignUptwo.text?.isNotEmpty()!! &&
                    etLastNameSignUptwo.text?.isNotEmpty()!! &&
                    etPhoneSignUptwo.text?.isNotEmpty()!! &&
                    etAddressSignUptwo.text?.isNotEmpty()!! ){

                    findNavController().navigate(R.id.action_signUpStepTwo_to_store)

                }

            }

        }

    }
}