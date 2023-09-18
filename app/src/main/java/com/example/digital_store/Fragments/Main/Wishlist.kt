package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.digital_store.Adapter.WishlistAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.DataBase.SQLite.WIshListRepository
import com.example.digital_store.Models.Products
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Models.WishListObject
import com.example.digital_store.Navigation.Navigator
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentWishlistBinding
import retrofit2.Callback
import retrofit2.Response

class Wishlist : Fragment(), Navigator {

    lateinit var wishAdapter: WishlistAdapter
    lateinit var products: ArrayList<WishListObject>
    lateinit var wIshListRepository: WIshListRepository
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

        wIshListRepository = WIshListRepository(requireActivity().application)
        wishAdapter = WishlistAdapter()
        products = ArrayList()
        binding.rvWishlist.adapter = wishAdapter
        loadWishlist()
        wishAdapter.submitList(products)
        binding.apply {

            wishAdapter.itemClick = { position ->

                findNavController().navigate(
                    R.id.action_wishlist_to_details,
                    bundleOf("DetailsId" to products[position].id)
                )

            }

            ivClearWishlist.setOnClickListener {

                clearAll(products)
                loadWishlist()
                wishAdapter.submitList(products)

            }
            wishAdapter.deleteItem = {

                try {

                    deleteProduct(products[it].id!!)
                    loadWishlist()
                    wishAdapter.submitList(products)

                } catch (e: IndexOutOfBoundsException) {

                    Toast.makeText(
                        requireContext(),
                        "Wishlist is already empty",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

        }

    }


    private fun clearAll(list: ArrayList<WishListObject>) {

        wIshListRepository.clearAll(list)

    }


    //        Loading items
    private fun loadWishlist() {

        products = ArrayList()
        products = wIshListRepository.getAll() as ArrayList<WishListObject>

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