package com.virtualteaching.studentsmagnet.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.virtualteaching.studentsmagnet.R
import com.virtualteaching.studentsmagnet.model.IconButton


fun launchIconButton(iconButton: IconButton, context: Context) {
    when {
        //Any intent with uri
        iconButton.uri != Uri.EMPTY -> {
            val intent = Intent(Intent.ACTION_VIEW, iconButton.uri)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                try {
                    context.startActivity(intent)
                } catch (exception: ActivityNotFoundException) {
                    Toast.makeText(context, "Oops! An error has occurred", Toast.LENGTH_LONG).show()
                }
            }
        }
        //Instagram targeted intents
        iconButton.instagramProfile != null -> {
            launchInstagramProfile(iconButton.instagramProfile, context)
        }
    }
}

private fun launchInstagramProfile(instagramProfile: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/$instagramProfile"))
    intent.`package` = context.getString(R.string.instagram_package_id)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://instagram.com/$instagramProfile")
            )
        )
    }
}