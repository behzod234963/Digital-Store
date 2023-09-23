package com.example.digital_store.Fragments.Main

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
                    cartAdapter.submitList(carts)

                } catch (_: IndexOutOfBoundsException) {
                }

            }

            tvTotalCartCart.text.apply {

                cartAdapter.submitList(carts)
                tvTotalCartCart.text = "Total ${carts.size} items"

            }

            cartAdapter.deleteItem = {

                try {

                    deleteCartByID(carts[it].id!!)
                    loadCarts()
                    cartAdapter.submitList(carts)

                } catch (e: IndexOutOfBoundsException) {

                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()

                }

            }

            cartAdapter.llClick = {

                cartAmountDialog()
                var currentCount = SharedPreferences(requireContext()).getCount()
                carts[it].count=currentCount
                
                currentPrice(currentCount,carts[it].price)

                btn.setOnClickListener {

                    Toast.makeText(
                        requireContext(),
                        "Operation not implemented",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }

            }

        }

    }

    private fun currentPrice(count: Int, price: Double):Double {
        
        var result = 0.0
        result=count*price

        return result

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


    //    Creating Custom Dialog for cart amount
    private fun cartAmountDialog() {

        var count = 1

        binding.apply {

            val cartDialog = Dialog(requireContext())
            cartDialog.setContentView(R.layout.item_cart_dialog)
            cartDialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            cartDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            cartDialog.setCancelable(false)
            val llMinus: LinearLayout = cartDialog.findViewById(R.id.llMinus_cartdialog)
            val llPlus: LinearLayout = cartDialog.findViewById(R.id.llPlus_cartdialog)
            val tvCount: TextView = cartDialog.findViewById(R.id.tvCount_dialog)
            val btnSave: Button = cartDialog.findViewById(R.id.btnSave_dialog)
            val btnCancel: Button = cartDialog.findViewById(R.id.btnCancel_dialog)

            tvCount.text = count.toString()

            llMinus.setOnClickListener {

                if (count > 1) {

                    count--

                }

            }
            llPlus.setOnClickListener {

                count++

            }
            btnSave.setOnClickListener {

                SharedPreferences(requireContext()).saveCount(count)

            }
            btnCancel.setOnClickListener {

                cartDialog.dismiss()

            }
            Toast.makeText(requireContext(), "vdbvkvd", Toast.LENGTH_SHORT).show()

        }
        
    }

}
