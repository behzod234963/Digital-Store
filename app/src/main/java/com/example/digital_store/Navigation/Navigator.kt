package com.example.digital_store.Navigation

import android.os.Bundle

interface Navigator {

    fun saveAction(actionID:Int,bundle: Bundle?=null)

}