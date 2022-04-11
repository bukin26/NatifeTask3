package com.gmail.notifytask3.util

import android.widget.ImageView
import com.bumptech.glide.Glide

object Extensions {

    fun ImageView.loadImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl)
            .into(this)
    }
}