package com.example.digital_store.Fragments.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.digital_store.Adapter.JeweleryAdapter
import com.example.digital_store.Adapter.MensAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Navigation.Navigator
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentJeweleryBinding
import com.example.digital_store.databinding.FragmentMensClothingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MensClothing(val listener: Navigator) : Fragment() {

    lateinit var binding: FragmentMensClothingBinding
    lateinit var mensAdapter: MensAdapter
    lateinit var products: ArrayList<ProductsItem>
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMensClothingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        binding.lavLoadingMens.visibility=View.VISIBLE
        navController = NavController(requireContext())
        products = ArrayList()
        mensAdapter = MensAdapter()
        binding.rvMensClothing.adapter = mensAdapter
        loadCategory("men's clothing")

        mensAdapter.onClick = { position ->

            listener.saveAction(
                R.id.action_store_to_details,
                bundleOf("DetailsId" to products[position].id)
            )
            navController.popBackStack()

        }

    }

    //    Loading category
    private fun loadCategory(category: String) {

        ApiClient.apiServis.getCategoryByName(category).enqueue(object :
            Callback<ArrayList<ProductsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {

                if (response.isSuccessful) {

                    binding.lavLoadingMens.visibility=View.GONE
                    products.clear()
                    products.addAll(response.body()!!)
                    mensAdapter.submitList(products)

                }

            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {

                binding.lavLoadingMens.visibility=View.GONE
                Toast.makeText(requireContext(), "${t.localizedMessage}", Toast.LENGTH_LONG).show()

            }
        })

    }

}