package com.example.digital_store.Fragments.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.digital_store.Adapter.ElectronicsAdapter
import com.example.digital_store.Adapter.WomensAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentElectronicsBinding
import com.example.digital_store.databinding.FragmentWomensClothingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WomensClothing : Fragment() {

    lateinit var binding: FragmentWomensClothingBinding
    lateinit var electronicsAdapter: WomensAdapter
    lateinit var products:ArrayList<ProductsItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentWomensClothingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {

        products= ArrayList()
        electronicsAdapter= WomensAdapter()
        binding.rvElectronics.adapter=electronicsAdapter
        loadCategory("women's clothing")

        electronicsAdapter.onClick={ position->



        }

    }

    private fun loadCategory(category:String) {

        ApiClient.apiServis.getCategoryByName(category).enqueue(object :
            Callback<ArrayList<ProductsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ProductsItem>>,
                response: Response<ArrayList<ProductsItem>>
            ) {

                if (response.isSuccessful){

                    products.clear()
                    products.addAll(response.body()!!)
                    electronicsAdapter.submitList(products)

                }

            }

            override fun onFailure(call: Call<ArrayList<ProductsItem>>, t: Throwable) {

                Toast.makeText(requireContext(), "${t.localizedMessage}", Toast.LENGTH_LONG).show()

            }
        })

    }
}