package com.example.digital_store.DataBase.SQLite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.digital_store.Models.WishListObject


@Database(entities = [WishListObject::class], version = 1)
abstract class WIshListDB : RoomDatabase (){

    abstract fun getDao():WishListInterface

    companion object{

        var DB_INSTANCE:WIshListDB?=null

        fun appDataBase(ctx:Context):WIshListDB{

            if (DB_INSTANCE==null){

                DB_INSTANCE= Room.databaseBuilder(ctx.applicationContext,WIshListDB::class.java,"data").allowMainThreadQueries().build()

            }

            return DB_INSTANCE!!

        }

    }

}