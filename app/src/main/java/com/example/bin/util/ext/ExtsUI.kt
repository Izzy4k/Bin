package com.example.bin.util.ext

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible

fun View.visible() {
    this.isVisible = true
}



fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}
