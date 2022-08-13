package com.virtualteaching.studentsmagnet.model

import android.net.Uri
import androidx.annotation.DrawableRes

data class IconButton(
    val title: String,
    @DrawableRes val image: Int,
    val intentUri: Uri
)
