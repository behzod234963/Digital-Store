package com.example.digital_store.DataBase.SQLite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.digital_store.Models.RoomData


@Database(entities = [RoomData.WishList::class, RoomData.Cart::class], version = 1)
abstract class DataBaseCore : RoomDatabase (){

    abstract fun getDao():DataBaseDaO

    companion object{

        var DB_INSTANCE:DataBaseCore?=null

        fun appDataBase(ctx:Context):DataBaseCore{

            if (DB_INSTANCE==null){

                DB_INSTANCE= Room.databaseBuilder(ctx.applicationContext,DataBaseCore::class.java,"data").allowMainThreadQueries().build()

            }

            return DB_INSTANCE!!

        }

    }

}