package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.UsersItem
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentAboutBinding
import com.example.digital_store.databinding.FragmentSignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class
SignIn : Fragment() {

    lateinit var navController: NavController
    lateinit var binding: FragmentSignInBinding
    lateinit var users:ArrayList<UsersItem>

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
        loadUser(4)
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

    private fun loadUser(id: Int) {

        ApiClient.apiServis.getUserById(id).enqueue(object :Callback<UsersItem>{
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {

                if (response.isSuccessful){

                    loadUserItems(response.body())

                }

            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {

                Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_SHORT).show()

            }
        })

    }

    private fun loadUserItems(body: UsersItem?) {

        var username=binding.etUsernameSignIn.text.toString()
        var password=binding.etPasswordSignIn.text.toString()

        binding.apply {

            username=body?.username.toString()
            password=body?.password.toString()

        }

    }

}