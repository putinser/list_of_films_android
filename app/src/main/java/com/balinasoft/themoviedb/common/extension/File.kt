package com.balinasoft.themoviedb.common.extension

import android.webkit.MimeTypeMap
import java.io.File

fun File.getMimeType(): String? {
    var type: String? = null
    val extension = MimeTypeMap.getFileExtensionFromUrl(toURI().toURL().toString())
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
    return type
}