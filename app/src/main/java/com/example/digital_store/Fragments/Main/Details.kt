package com.example.digital_store.Fragments.Main

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.DataBase.SQLite.DataBaseRepository
import com.example.digital_store.DataBase.SharedPreferences.SharedPreferences
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Models.RoomData
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import kotlin.properties.Delegates

class Details : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var products: ArrayList<ProductsItem>
    lateinit var navController: NavController
    lateinit var wishList: ArrayList<RoomData.WishList>
    lateinit var url: URL
    lateinit var repository: DataBaseRepository
    val args: DetailsArgs by navArgs()
    var detailsID = 1
    var cartID by Delegates.notNull<Int>()
    var title by Delegates.notNull<String>()
    var price =0.0
    var status=1
    var checked=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    //    Initialize data
    private fun initView() {

        repository = DataBaseRepository(requireActivity().application)
        binding.lavLoadingDetails.visibility = View.VISIBLE
        products = ArrayList()
        wishList = ArrayList()
        navController = NavController(requireContext())
        detailsID = args.DetailsId
        loadDetail(detailsID)

        mainController()

    }


//    Business logic for this fragment
    private fun mainController() {

        binding.apply {

            llBackDetails.setOnClickListener {

                findNavController().navigate(R.id.action_details_to_store)
                navController.popBackStack()

            }

            btnAddCartDetails.setOnClickListener {

                cartAmountDialog()

            }

            llCartDetails.setOnClickListener {

                findNavController().navigate(R.id.action_details_to_cart)
                navController.popBackStack()

            }

            llWishlistDetails.setOnClickListener { onBtnWishlistListener()}

        }

    }

    private fun onBtnWishlistListener() {

        status++

        try {

           when(status){

               1->{

                   binding.apply {

                       val wishlistId = tvIdDetails.text.toString()
                       if (checked){

                           ivWishlistDetails.setImageResource(R.drawable.ic_heart_unchecked)
                           repository.deleteById(wishlistId.toInt())


                       }else{

                           ivWishlistDetails.setImageResource(R.drawable.ic_heart_unchecked)

                       }

                   }

               }

               2->{

                   binding.apply {

                       checked=true
                       ivWishlistDetails.setImageResource(R.drawable.ic_heart_checked)
                       val id = tvIdDetails.text
                       val title = tvTitleDetails.text
                       val price = tvPriceDetails.text
                       val rating = tvRatingDetails.text
                       val description = tvDescriptionDetails.text
                       repository.saveProduct(
                           RoomData.WishList(
                               id = id.toString().toInt(),
                               title = title.toString(),
                               price = price.toString(),
                               rating = rating.toString(),
                               description = description.toString(),
                               image = url.toString()
                           )
                       )

                   }
                   status=0

               }

           }

            if (checked){



            }

        } catch (_: NumberFormatException) { }

    }


    //    Loading details
    private fun loadDetail(id: Int) {

        products = ArrayList()

        ApiClient.apiServis.getProductById(id).enqueue(object : Callback<ProductsItem> {
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {

                if (response.isSuccessful) {

                    binding.lavLoadingDetails.visibility = View.GONE
                    loadData(response.body()!!)
                    getURl(response.body()!!)

                }

            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {

                binding.lavLoadingDetails.visibility = View.GONE
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()

            }

        })

    }


    //    Getting image url
    private fun getURl(body: ProductsItem) {

        url = URL(body.image)

    }

    //    Loading items into details
    private fun loadData(body: ProductsItem) {

        cartID=body.id!!
        title=body.title!!
        price= (body.price as Double?)!!

        binding.apply {

            tvIdDetails.text = body.id.toString()
            Glide.with(ivDetailsImageDetails).load(body.image).into(ivDetailsImageDetails).toString()
            tvTitleDetails.text = body.title
            tvPriceDetails.text = "${body.price.toString()} USD"
            tvRatingDetails.text = body.rating.toString()
            tvDescriptionDetails.text = body.description

        }

    }

    //    Creating Custom Dialog for cart amount
    private fun cartAmountDialog() {

        binding.apply {

            var count=1

            val cartDialog = Dialog(requireContext())
            cartDialog.setContentView(R.layout.item_cart_dialog)
            cartDialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            cartDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            cartDialog.setCancelable(false)
            val llMinus: LinearLayout? = cartDialog.findViewById(R.id.llMinus_cartdialog)
            val llPlus: LinearLayout? = cartDialog.findViewById(R.id.llPlus_cartdialog)
            val tvAmount:TextView=cartDialog.findViewById(R.id.tvAmount_cartDialog)
            val btnSave: Button? = cartDialog.findViewById(R.id.btnSave_dialog)
            val btnCancel: Button? = cartDialog.findViewById(R.id.btnCancel_dialog)


            llMinus?.setOnClickListener {

                if (count > 1) {

                    count--
                    tvAmount.text=count.toString()

                }

            }
            llPlus?.setOnClickListener {

                count++
                tvAmount.text=count.toString()

            }

            tvAmount.text=count.toString()

            btnSave?.setOnClickListener {

                if (count>1){

                    try {

                        repository.saveCart(

                            RoomData.Cart(
                                id = cartID,
                                image = url.toString(),
                                title = title,
                                price = price,
                                count = count
                            )
                        )

                    } catch (_: NumberFormatException) {}

                }else{

                    try {

                        repository.saveCart(

                            RoomData.Cart(
                                id = cartID,
                                image = url.toString(),
                                title = title,
                                price = price,
                                count=count
                            )
                        )

                    } catch (_: NumberFormatException) {}

                }
                cartDialog.dismiss()

            }
            btnCancel?.setOnClickListener {

                cartDialog.dismiss()

            }
            cartDialog.show()

        }

    }

    override fun onPause() {
        super.onPause()

        if (checked){

            SharedPreferences(requireContext()).saveInt()

        }

    }

}