package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.UsersItem
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentSettingsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Settings : Fragment() {

    lateinit var binding: FragmentSettingsBinding
    lateinit var navController: NavController
    lateinit var users: ArrayList<UsersItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        binding.lavUserLoadingSettings.visibility = View.VISIBLE

        binding.llProfileSettings.visibility=View.GONE

        navController = NavController(requireContext())
        users = ArrayList()
        loadUser(4)
        binding.apply {

            llEditProfileSettings.setOnClickListener {

                findNavController().navigate(R.id.action_settings_to_editProfile)
                navController.popBackStack()

            }

            llWishlistSettings.setOnClickListener {

                findNavController().navigate(R.id.action_settings_to_wishlist)
                navController.popBackStack()

            }

            llBackSettings.setOnClickListener {

                findNavController().navigate(R.id.action_settings_to_store)
                navController.popBackStack()

            }

            llProfileSettings.setOnClickListener {

                findNavController().navigate(R.id.action_settings_to_about, bundleOf("editID" to 4))
                navController.popBackStack()

            }

            llLogoutSettings.setOnClickListener {

                findNavController().navigate(R.id.action_settings_to_signIn)
                navController.popBackStack()

            }

        }

    }

    //    Loading user details
    private fun loadUser(id: Int) {

        ApiClient.apiServis.getUserById(id).enqueue(object : Callback<UsersItem> {
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {

                if (response.isSuccessful) {

                    binding.lavUserLoadingSettings.visibility = View.GONE

                    binding.llProfileSettings.visibility=View.VISIBLE
                    laodItems(response.body())

                }

            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {

                binding.lavUserLoadingSettings.visibility=View.VISIBLE

            }
        })

    }

    //    Loading items
    private fun laodItems(body: UsersItem?) {

        binding.apply {

            ivProfileSettings.setImageResource(R.drawable.pic_digital_store)
            tvUsernameSettings.text = body?.username
            tvEmailSettings.text = body?.email

        }

    }

}