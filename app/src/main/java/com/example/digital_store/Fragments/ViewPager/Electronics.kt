package com.example.digital_store.Fragments.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.digital_store.Adapter.ElectronicsAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Navigation.Navigator
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentElectronicsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Electronics(val listener: Navigator) : Fragment() {

    lateinit var binding: FragmentElectronicsBinding
    lateinit var electronicsAdapter: ElectronicsAdapter
    lateinit var products: ArrayList<ProductsItem>
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentElectronicsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        binding.lavLoadingElectronics.visibility=View.VISIBLE
        navController = NavController(requireContext())
        products = ArrayList()
        electronicsAdapter = ElectronicsAdapter()
        binding.rvElectronics.adapter = electronicsAdapter
        loadCategory("electronics")

        electronicsAdapter.onClick = { position ->

            listener.saveAction(
                R.id.action_store_to_details,
                bundleOf("DetailsId" to products[position].id)
            )
            navController.popBackStack()

        }

    }

    //    Loading categories
    private fun loadCategory(category: String) {

        ApiClient.apiServis.getCategoryByName(category).enqueue(object :
            Callback<ArrayList<ProductsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {

                if (response.isSuccessful) {

                    binding.lavLoadingElectronics.visibility=View.GONE
                    products.clear()
                    products.addAll(response.body()!!)
                    electronicsAdapter.submitList(products)

                }

            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {

                binding.lavLoadingElectronics.visibility=View.GONE
                Toast.makeText(requireContext(), "${t.localizedMessage}", Toast.LENGTH_LONG).show()

            }
        })

    }

}