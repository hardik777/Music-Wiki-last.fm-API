package com.practical.controller

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.practical.R
import java.net.URI
import java.net.URL
import java.util.HashMap


val INTENT_DATA = "INTENT_DATA"
val PERMISSION_REQUEST_CODE = 200

fun Context.ToastMessage(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
}

var dialog: Dialog? = null

/**
 * Show loader
 */
fun Context.OpenLoader() {

    dialog = Dialog(this)
    dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    dialog!!.setContentView(R.layout.progress)
    dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog!!.setCancelable(true)
    dialog!!.setCanceledOnTouchOutside(true)

    val lp = WindowManager.LayoutParams()
    lp.copyFrom(dialog!!.window!!.attributes)
    lp.width = WindowManager.LayoutParams.WRAP_CONTENT
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT
    dialog!!.window!!.attributes = lp
    dialog!!.show()

}

/**
 * Hide loader
 */
fun Context.CloseLoader() {
    try {
        if (dialog != null) {
            if (dialog!!.isShowing) {
                dialog!!.dismiss()
                dialog = null
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Load image from glide
 */
fun Context.LoadImage(imgUrl: String, img: ImageView) {
    Glide.with(this)
            .load(imgUrl)
            .placeholder(R.drawable.ic_loading_image)
            .error(R.drawable.ic_no_image)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .centerCrop()
            .into(img)
}

/**
 * To start another activity.
 */
fun Activity.NewIntentWithData(
        ourClass: Class<*>,
        data: Boolean,
        isAnimation: Boolean
) {
    val intent = Intent(this, ourClass)
    intent.putExtra(INTENT_DATA, data)
    startActivity(intent)
    if (isAnimation) {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    } else {
        overridePendingTransition(R.anim.animation_one, R.anim.animation_two)
    }
}


fun Activity.NewIntentWithData(
        ourClass: Class<*>,
        hashMap: HashMap<String, Any>,
        isAnimation: Boolean,
        isFinish: Boolean
) {
    val intent = Intent(this, ourClass)
    intent.putExtra(INTENT_DATA, hashMap)
    startActivity(intent)
    if (isAnimation) {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    } else {
        overridePendingTransition(R.anim.animation_one, R.anim.animation_two)
    }
    if (isFinish) {
        finish()
    }
}
