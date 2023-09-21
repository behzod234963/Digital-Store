package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.digital_store.Adapter.WishlistAdapter
import com.example.digital_store.DataBase.SQLite.DataBaseRepository
import com.example.digital_store.Models.RoomData
import com.example.digital_store.Navigation.Navigator
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentWishlistBinding

class Wishlist : Fragment(), Navigator {

    lateinit var wishAdapter: WishlistAdapter
    lateinit var products: ArrayList<RoomData.WishList>
    lateinit var navController: NavController
    lateinit var wIshListRepository: DataBaseRepository
    var wishID = 0
    lateinit var wishArgs: WishlistArgs
    lateinit var binding: FragmentWishlistBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWishlistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        wIshListRepository = DataBaseRepository(requireActivity().application)
        wishAdapter = WishlistAdapter()
        products = ArrayList()
        binding.rvWishlist.adapter = wishAdapter
        loadWishlist()
        wishAdapter.submitList(products)
        navController = NavController(requireContext())
        binding.apply {

            wishAdapter.itemClick = { position ->

                findNavController().navigate(
                    R.id.action_wishlist_to_details,
                    bundleOf("DetailsId" to products[position].id)
                )

            }

            llClearWishlist.setOnClickListener {

                clearAll(products)
                loadWishlist()
                wishAdapter.submitList(products)

            }
            wishAdapter.deleteItem = {

                try {

                    deleteProduct(products[it].id!!)
                    loadWishlist()
                    wishAdapter.submitList(products)

                } catch (_: IndexOutOfBoundsException) {
                }

            }

            llBackWishlist.setOnClickListener {

                findNavController().navigate(R.id.action_wishlist_to_settings)
                navController.popBackStack()

            }

        }

    }


    //    Clear all products in the wishlist fragment
    private fun clearAll(list: ArrayList<RoomData.WishList>) {

        wIshListRepository.clearAll(list)

    }


    //        Loading items
    private fun loadWishlist() {

        products = ArrayList()
        products = wIshListRepository.getAll() as ArrayList<RoomData.WishList>

    }


    //    Delete object
    fun deleteProduct(id: Int) {

        wIshListRepository.deleteById(id)

    }

    //    Navigator
    override fun saveAction(actionID: Int, bundle: Bundle?) {

        when (actionID) {

            R.id.action_wishlist_to_details -> {

                findNavController().navigate(R.id.action_wishlist_to_details)

            }

        }

    }

}