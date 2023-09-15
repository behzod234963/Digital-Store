package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentAboutBinding
import com.example.digital_store.databinding.FragmentSignInBinding

class
SignIn : Fragment() {

    lateinit var navController: NavController
    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
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

            btnSignInSignIn.setOnClickListener {

                if (etUsernameSignIn.text?.isNotEmpty()!! && etPasswordSignIn.text?.isNotEmpty()!!) {

                    findNavController().navigate(R.id.action_signIn_to_store)
                    navController.popBackStack()

                }else{

                    Toast.makeText(requireContext(), " Incorrect \nUsername or Password", Toast.LENGTH_SHORT).show()

                }

            }

            tvSignUpSignIn.setOnClickListener{

                findNavController().navigate(R.id.action_signIn_to_signUp)
                navController.popBackStack()

            }

        }

    }

}