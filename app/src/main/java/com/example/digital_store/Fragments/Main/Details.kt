package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digital_store.Adapter.DetailsAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentDetailsBinding
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class Details : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var products:ArrayList<ProductsItem>
    lateinit var detailsAdapter:DetailsAdapter
    lateinit var navController:NavController
    val args:DetailsArgs by navArgs()
    var detailId=1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {

        products= ArrayList()
        detailsAdapter= DetailsAdapter()
        detailId=args.id
        loadDetail(id)
        binding.rvDetailsDetails.adapter=DetailsAdapter()
        detailsAdapter.submitList(products)
        navController= NavController(requireContext())

        binding.ivBackDetails.setOnClickListener {

            findNavController().navigate(R.id.action_details_to_store)
            navController.popBackStack()

        }

    }

    private fun loadDetail(id:Int) {

        products=ArrayList()

        ApiClient.api_servis.getProductById(id).enqueue(object :Callback<ProductsItem>{
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {


                if (response.isSuccessful){

                    products.clear()
                    products.addAll(products)
                    detailsAdapter.submitList(products)

                }

            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {

                products.clear()
                products.addAll(products)
                detailsAdapter.submitList(products)
                Log.d("11111","${t.localizedMessage}")

            }

        })

    }

}