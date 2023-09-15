package com.example.digital_store.Fragments.Main

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.UsersItem
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentAboutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class About : Fragment() {

    lateinit var navController: NavController
    lateinit var binding: FragmentAboutBinding
    val IMAGE_CHOOSER = 4
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        binding.lavLoadingAbout.visibility = View.GONE
        loadUser(4)
        navController = NavController(requireContext())

        binding.apply {

            ivBackAbout.setOnClickListener {

                navController.popBackStack()

            }

            lavLoadingAbout.visibility = View.VISIBLE

            flProfileAbout.setOnClickListener {

                val chooseFromGallery = Intent(Intent.ACTION_GET_CONTENT)

                chooseFromGallery.type = "image/*"
                startActivityForResult(
                    Intent.createChooser(
                        chooseFromGallery,
                        "Select profile picture"
                    ), IMAGE_CHOOSER
                )

            }

            btnEditAbout.setOnClickListener {

                findNavController().navigate(R.id.action_about_to_editProfile)
                navController.popBackStack()

            }

        }

    }


    //    Loading user details
    private fun loadUser(id: Int) {

        ApiClient.apiServis.getUserById(id).enqueue(object : Callback<UsersItem> {
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {

                if (response.isSuccessful) {

                    loadUserItems(response.body())

                }

            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }


    //    Loading user items
    private fun loadUserItems(body: UsersItem?) {

        binding.apply {

            tvFirstNameAbout.text = body?.name?.firstname
            tvLastNameAbout.text = body?.name?.lastname
            tvEmailAbout.text = body?.email
            tvPhoneAbout.text = body?.phone
            tvAddressAbout.text = body?.address.toString()

        }

    }


    //    Getting Image URI
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_CHOOSER && resultCode == RESULT_OK) {

            val imageURI = data?.data
            binding.ivProfileImageAbout.setImageURI(imageURI)

        }

    }

}