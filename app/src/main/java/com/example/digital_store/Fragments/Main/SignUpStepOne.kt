package com.example.digital_store.Fragments.Main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentSignUpStepOneBinding

class SignUpStepOne : Fragment() {

    lateinit var binding: FragmentSignUpStepOneBinding
    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignUpStepOneBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }


//    Initialize data
    @SuppressLint("SuspiciousIndentation")
    private fun initView() {

    navController=NavController(requireContext())
    val pass= binding.etPasswordSignUp.text.toString()
    val confirm = binding.etConfirmSignUp.text.toString()
        binding.apply {

            btnNextSignUp.setOnClickListener {

                if ( etEmailSignup.text.toString().isNotEmpty() && pass == confirm){

                    findNavController().navigate(R.id.action_signUp_to_signUpStepTwo)
                    navController.popBackStack()

                }else{

                    Toast.makeText(requireContext(), "Incorrect Email or password ", Toast.LENGTH_SHORT).show()

                }

            }

            tvSignInSignUp.setOnClickListener {

                findNavController().navigate(R.id.action_signUp_to_signIn)
                navController.popBackStack()

            }

        }

    }

}