package com.virtualteaching.studentsmagnet.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Intent.launchUri(uri: Uri?, context: Context) {
    uri?.let {
        if(uri != Uri.EMPTY) {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            if(intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }
    }
}