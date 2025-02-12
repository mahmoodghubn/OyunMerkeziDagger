package com.example.oyunmerkezi.ui.main


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.oyunmerkezi.util.FirebaseGame

//import com.bumptech.glide.Glide
//import com.example.oyunmerkezi3.database.Game


@BindingAdapter("sellingPrice")
fun TextView.setGamePrice(item: FirebaseGame?) {
    item?.let {
        text = item.sellingPrice.toString()
    }
}

@BindingAdapter("buyingPrice")
fun TextView.setBuyingPrice(item: FirebaseGame?) {
    item?.let {
        text = item.buyingPrice.toString()
    }
}

@BindingAdapter("gameName")
fun TextView.setGameName(item: FirebaseGame?) {
    item?.let {
        text = item.gameName
    }
}

@BindingAdapter("videoThumbnailImageView")
fun ImageView.setThumbnail(item: FirebaseGame?) {

    val url = "https://i4.ytimg.com/vi/${item!!.URL[0]}/mqdefault.jpg"
    item.let {
        Glide.with(this.context).load(url).into(this)

    }
}

