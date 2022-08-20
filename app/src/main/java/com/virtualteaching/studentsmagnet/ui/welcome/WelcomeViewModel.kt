package com.virtualteaching.studentsmagnet.ui.welcome

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.virtualteaching.studentsmagnet.R
import com.virtualteaching.studentsmagnet.data.DataSource
import com.virtualteaching.studentsmagnet.model.IconButton

class WelcomeViewModel(val dataSource: DataSource) : ViewModel() {

    val iconButtonLiveData = dataSource.getIconButtonList()

    fun insertIconButton(
        iconButtonIcon: Int,
        iconButtonTitle: String,
        uri: Uri,
        instagramProfile: String?
    ) {
        val newIconButton = IconButton(
            iconButtonTitle,
            iconButtonIcon,
            uri,
            instagramProfile
        )

        dataSource.addIconButton(newIconButton)
    }
}

class WelcomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WelcomeViewModel(
                dataSource = DataSource.getDataSource(listOf(
                    IconButton(
                        context.getString(R.string.info_and_sign_up),
                        R.drawable.ic_baseline_app_registration,
                        Uri.parse(context.getString(R.string.register_url))
                    ),
                    IconButton(
                        context.getString(R.string.timetable),
                        R.drawable.ic_baseline_calendar,
                        Uri.parse(context.getString(R.string.calendar_url))
                    ),
                    IconButton(
                        context.getString(R.string.contact_us),
                        R.drawable.ic_whatsapp,
                        Uri.parse("https://api.whatsapp.com/send?phone=+34613550464")
                    ),
                    IconButton(
                        context.getString(R.string.follow_us),
                        R.drawable.ic_instagram,
                        Uri.parse("https://instagram.com/${context.getString(R.string.instagram_virtual_teaching)}"),
                        context.getString(R.string.instagram_virtual_teaching)
                    ),
                    IconButton(
                        context.getString(R.string.rate_us),
                        R.drawable.ic_black_star_filled,
                        Uri.parse(context.getString(R.string.review_url))
                    )
                ))
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}