package com.interactivestandard.test.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import java.io.File


private fun File.createFileAndDirs() = apply {
    parentFile?.mkdirs()
    createNewFile()
}

fun File.write(
    bitmap: Bitmap, format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, quality: Int = 80
) = apply {
    createFileAndDirs()
    outputStream().use {
        bitmap.compress(format, quality, it)
        it.flush()
    }
}