package com.balinasoft.themoviedb.common.extension

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.fragment.app.Fragment


fun Fragment.camera(uri: Uri, requestCode: Int) {
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

    this.startActivityForResult(takePictureIntent, requestCode)
}

fun Fragment.gallery(requestCode: Int) {
    val intent = Intent(Intent.ACTION_GET_CONTENT)

    intent.type = "image/*"

    this.startActivityForResult(intent, requestCode)
}

/*
fun Fragment.showDialog(title: String, message: String, onPositiveButtonClick: () -> Unit, onNegativeButtonClick: () -> Unit) {
    val builder = AlertDialog.Builder(context!!)
    builder.setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setNegativeButton(getString(R.string.no)) {
                dialog, _ ->
            onNegativeButtonClick.invoke()
            dialog.cancel()
        }
        .setPositiveButton(R.string.yes) {
                dialog, _ ->
            onPositiveButtonClick.invoke()
            dialog.cancel()
        }
    val alert = builder.create()
    alert.show()
}*/
