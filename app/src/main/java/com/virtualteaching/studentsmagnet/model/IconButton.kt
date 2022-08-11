package com.virtualteaching.studentsmagnet.model

import android.net.Uri
import androidx.annotation.DrawableRes

data class IconButton(
    val title: String,
    @DrawableRes val imageId: Int,
    val intentUri: Uri
)
