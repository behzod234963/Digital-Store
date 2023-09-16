package com.example.digital_store.DataBase.SQLite

import android.app.Application
import com.example.digital_store.Models.ProductsItem
import com.example.digital_store.Models.WishListObject

class WIshListRepository(val app:Application) {

    private val wishListDao=WIshListDB.appDataBase(app).getDao()

    fun saveProduct(product:WishListObject){

        wishListDao.saveProduct(product)

    }

    fun getAll():List<WishListObject>{

        return wishListDao.getAll()

    }

    fun getById(id:Int):WishListObject{

        return wishListDao.getById(id)

    }

    fun clearAll(product: ArrayList<WishListObject>){

        wishListDao.clearAll(product)

    }

    fun deleteById(id:Int){

        wishListDao.deleteById(id)

    }

}