package com.example.digital_store.Fragments.Main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.UsersItem
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentEditProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
    loadUser(4)

        binding.llBackEdit.setOnClickListener {

            findNavController().navigate(R.id.action_editProfile_to_settings)
            navController.popBackStack()

        }

    }

    private fun loadUser(id: Int) {

        ApiClient.apiServis.getUserById(id).enqueue(object :Callback<UsersItem>{
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {

                if (response.isSuccessful){

                    loadUserData(response.body())

                }

            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }


//    Loading user data
    private fun loadUserData(body: UsersItem?) {

        binding.apply {

            var fistName=etFirstNameEdit.text.toString()
            var lastName=etLastNameEdit.text.toString()
            var address=etAddressEdit.text.toString()
            var email=etEmailEdit.text.toString()
            var phone=etPhoneEdit.text.toString()

            fistName=body?.name?.firstname!!
            lastName=body.name.lastname!!
            address=body.address?.city!!
            email=body.email!!
            phone=body.phone!!

        }

    }

}