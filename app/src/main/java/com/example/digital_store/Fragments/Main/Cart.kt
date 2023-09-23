package com.example.digital_store.Fragments.Main

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.Adapter.CartAdapter
import com.example.digital_store.DataBase.SQLite.DataBaseRepository
import com.example.digital_store.DataBase.SharedPreferences.SharedPreferences
import com.example.digital_store.Models.RoomData
import com.example.digital_store.R
import com.example.digital_store.Utils.CartIncDec
import com.example.digital_store.databinding.FragmentCartBinding

class Cart : Fragment(), CartIncDec {

    lateinit var binding: FragmentCartBinding
    lateinit var cartRepository: DataBaseRepository
    lateinit var cartAdapter: CartAdapter
    lateinit var carts: ArrayList<RoomData.Cart>
    lateinit var navController: NavController
    var price = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }


    //    Initialize data
    @SuppressLint("SetTextI18n")
    private fun initView() {

        binding.apply {

            cartRepository = DataBaseRepository(requireActivity().application)
            cartAdapter = CartAdapter(requireContext())
            rvCartCart.adapter = cartAdapter
            carts = ArrayList()
            loadCarts()
            cartAdapter.submitList(carts)
            navController = NavController(requireContext())

            cartAdapter.onClick = {

                findNavController().navigate(
                    R.id.action_cart_to_details,
                    bundleOf("DetailsId" to carts[it].id)
                )
                navController.popBackStack()

            }

            llBackCart.setOnClickListener {

                findNavController().navigate(R.id.action_cart_to_store)
                navController.popBackStack()

            }

            llClearAllCart.setOnClickListener {

                try {

                    clearAllCart()
                    loadCarts()
                    tvTotalCartCart.text = "Total ${carts.size} items"
                    cartAdapter.submitList(carts)

                } catch (_: IndexOutOfBoundsException) {
                }

            }

            tvTotalCartCart.text.apply {

                tvTotalCartCart.text = "Total ${carts.size} items"
                cartAdapter.submitList(carts)

            }

            cartAdapter.deleteItem = {

                try {

                    deleteCartByID(carts[it].id!!)
                    loadCarts()
                    tvTotalCartCart.text = "Total ${carts.size} items"
                    cartAdapter.submitList(carts)

                } catch (e: IndexOutOfBoundsException) {

                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()

                }

            }

        }

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


    //    Delete single cart
    private fun deleteCartByID(id: Int) {

        cartRepository.deleteCartByID(id)
        
    }


}
