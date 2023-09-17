package com.example.digital_store.Fragments.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.digital_store.DataBase.Remote.ApiClient
import com.example.digital_store.DataBase.SQLite.WIshListRepository
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Models.WishListObject
import com.example.digital_store.R
import com.example.digital_store.databinding.FragmentDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Details : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var products: ArrayList<ProductsItem>
    lateinit var navController: NavController
    lateinit var wishList: ArrayList<WishListObject>
    lateinit var wishListRepository: WIshListRepository
    val args: DetailsArgs by navArgs()
    var detailsID = 1


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

        wishListRepository = WIshListRepository(requireActivity().application)
        binding.lavLoadingDetails.visibility = View.VISIBLE
        products = ArrayList()
        wishList = ArrayList()
        navController = NavController(requireContext())
        detailsID = args.DetailsId
        loadDetail(detailsID)

        binding.apply {

            ivBackDetails.setOnClickListener {

                findNavController().navigate(R.id.action_details_to_store)
                navController.popBackStack()

            }

            ivWishlistDetails.setOnClickListener {

                saveItems()

            }

        }

    }


    //    Saving for wishlist
    private fun saveItems() {

        binding.apply {

            ivWishlistDetails.setImageResource(R.drawable.ic_heart_checked)
            val id = tvIdDetails.text
            val title = tvTitleDetails.text
            val price = tvPriceDetails.text
            val rating = tvRatingDetails.text
            val desc = tvDescriptionDetails.text
            when (id) {

                1.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id=id.toString().toInt(),
                            null,
                            title=title.toString(),
                            price = price.toString(),
                            links = R.string.one.toString(),
                            rating = rating.toString(),
                            description = desc.toString()
                        )
                    )

                }

                2.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.two.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                3.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.three.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                4.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.four.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                5.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.five.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                6.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.six.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                7.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.seven.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                8.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.eight.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                9.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.nine.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                10.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.ten.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                11.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.eleven.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                12.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.twelve.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                13.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.thirteen.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                14.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.fourteen.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                15.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.fiveteen.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                16.toString() -> {
                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.sixteen.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )
                }

                17.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.seventeen.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                18.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.eighteen.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                19.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.nineteen.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

                20.toString() -> {

                    wishListRepository.saveProduct(
                        WishListObject(
                            id.toString().toInt(),
                            null,
                            title.toString(),
                            price.toString(),
                            R.string.twenty.toString(),
                            rating.toString(),
                            desc.toString()
                        )
                    )

                }

            }

        }

    }


    //    Loading details
    private fun loadDetail(id: Int) {

        products = ArrayList()

        ApiClient.apiServis.getProductById(id).enqueue(object : Callback<ProductsItem> {
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {

                if (response.isSuccessful) {

                    binding.lavLoadingDetails.visibility = View.GONE
                    loadData(response.body()!!)

                }

            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {

                binding.lavLoadingDetails.visibility = View.GONE
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()

            }

        })

    }

    //    Loading items into details
    private fun loadData(body: ProductsItem) {

        binding.apply {

            tvIdDetails.text = body.id.toString()
            Glide.with(this@Details).load(body.image).into(ivDetailsImageDetails).toString()
            tvTitleDetails.text = body.title
            tvPriceDetails.text = "${body.price.toString()} USD"
            tvRatingDetails.text = body.rating.toString()
            tvDescriptionDetails.text = body.description

        }

    }

}