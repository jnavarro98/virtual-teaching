package com.virtualteaching.studentsmagnet.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.virtualteaching.studentsmagnet.R
import com.virtualteaching.studentsmagnet.model.IconButton


fun IconButton.launch(context: Context) {
    when {
        //Instagram targeted intents
        this.instagramProfile != null -> {
            launchInstagramProfile(this, context)
        }
        //Any intent with uri
        this.uri != Uri.EMPTY -> {
            launchCustomTab(context, this.uri)
        }
    }
}

private fun launchInstagramProfile(iconButton: IconButton, context: Context) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("http://instagram.com/_u/${iconButton.instagramProfile}")
    )
    intent.`package` = context.getString(R.string.instagram_package_id)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        launchCustomTab(context, iconButton.uri)
    }
}

private fun launchCustomTab(context: Context, uri: Uri) {
    val builder = CustomTabsIntent.Builder()
    builder.setStartAnimations(context, R.anim.slide_up, R.anim.stay);
    builder.setExitAnimations(context, R.anim.slide_down, R.anim.stay);
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, uri)
}