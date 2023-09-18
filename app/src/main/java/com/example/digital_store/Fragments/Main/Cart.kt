package com.example.digital_store.Fragments.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.Adapter.CartAdapter
import com.example.digital_store.DataBase.SQLite.DataBaseRepository
import com.example.digital_store.Models.RoomData
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentCartBinding

class Cart : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var cartRepository: DataBaseRepository
    lateinit var cartAdapter: CartAdapter
    private lateinit var carts: ArrayList<RoomData.Cart>
    lateinit var navController: NavController
    var totalCost=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }


    //    Initialize data
    private fun initView() {

        navController = NavController(requireContext())
        cartRepository = DataBaseRepository(requireActivity().application)
        cartAdapter = CartAdapter()
        binding.rvCartCart.adapter = cartAdapter
        carts = ArrayList()
        loadCarts()
        cartAdapter.submitList(carts)

        var price=""

        binding.apply {

            cartAdapter.onClick = {

                findNavController().navigate(R.id.action_cart_to_details)
                navController.popBackStack()

            }

            ivBackCart.setOnClickListener {

                navController.popBackStack()

            }

            ivClearAllCart.setOnClickListener {

                clearAllCart()
                cartAdapter.submitList(carts)
                loadCarts()

            }

            tvTotalCartCart.text="Total ${carts.size} items"

            for (i in carts.indices){

                price+=carts[i].price.toDouble()

            }

            totalCost=price
            tvTotalCostCart.text="${totalCost} USD"

            cartAdapter.deleteItem={

                deleteCartByID(id)
                loadCarts()
                cartAdapter.submitList(carts)

            }

        }

    }


//    Delete single cart
    private fun deleteCartByID(id: Int) {

        cartRepository.deleteCartByID(id)

    }


    //    Clear all carts
    private fun clearAllCart() {

        cartRepository.clearAllCart(carts)

    }


    //    Loading carts
    private fun loadCarts() {

        carts = ArrayList()
        carts = cartRepository.getAllCart() as ArrayList<RoomData.Cart>

    }

}