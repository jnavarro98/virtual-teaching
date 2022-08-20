package com.virtualteaching.studentsmagnet.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.graphics.drawable.toBitmap
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
            launchCustomTab(context, this)
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
        launchCustomTab(context, iconButton)
    }
}

private fun launchCustomTab(context: Context, iconButton: IconButton) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    context.getDrawable(R.drawable.ic_baseline_arrow_back)
        ?.let { builder.setCloseButtonIcon(it.toBitmap()) }
    customTabsIntent.launchUrl(context, iconButton.uri)
}