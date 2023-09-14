package com.example.digital_store.Utils

import android.view.View

object Extensions {

    fun View.hide() {

        this.visibility = View.GONE

    }

    fun View.show() {

        this.visibility = View.VISIBLE

    }

}