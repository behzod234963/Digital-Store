package com.example.digital_store.Utils

import androidx.recyclerview.widget.DiffUtil
import com.example.digital_store.Models.RoomData

class CartDiffUtils(val oldList:ArrayList<RoomData.Cart>,
                    val newList:ArrayList<RoomData.Cart>):DiffUtil.Callback() {


    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int =newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList[oldItemPosition].count == newList[newItemPosition].count

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList==newList

    }
}