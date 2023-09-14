package com.example.digital_store.Fragments.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digital_store.Adapter.DetailsAdapter
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Details : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var products:ArrayList<ProductsItem>
    lateinit var detailsAdapter:DetailsAdapter
    var detailsId=1
    val args:DetailsArgs by navArgs()


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
        detailsId=args.DetailsId
        binding.rvDetailsDetails.adapter=detailsAdapter
        binding.rvDetailsDetails.layoutManager=LinearLayoutManager(requireContext())
        loadDetail(detailsId)


        binding.ivBackDetails.setOnClickListener {

            findNavController().navigate(R.id.action_details_to_store)

        }

    }

    private fun loadDetail(id:Int) {

        products=ArrayList()

        ApiClient.apiServis.getProductById(id).enqueue(object :Callback<ProductsItem>{
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {



            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {

                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()

            }

        })

    }

}