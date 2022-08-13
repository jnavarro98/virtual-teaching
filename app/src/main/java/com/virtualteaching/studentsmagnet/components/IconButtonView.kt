package com.virtualteaching.studentsmagnet.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.virtualteaching.studentsmagnet.R
import com.virtualteaching.studentsmagnet.databinding.ViewIconButtonBinding

class IconButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ViewIconButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        attrs?.let { setAttributes(context, it) }
    }

    private fun setAttributes(context: Context, attrs: AttributeSet) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.IconButtonView, 0, 0)

        try {
            attributes.getString(R.styleable.IconButtonView_title)?.let {
                title = it
            }
            attributes.getResourceId(R.styleable.IconButtonView_icon, 0)?.let {
                icon = it
            }
        } finally {
            attributes.recycle()
        }
    }

    var icon : Int = 0
        set(imageId : Int) {
            field = imageId
            binding.ivIcon.setImageResource(icon)
        }

    var title : String? = ""
        set(newTitle : String?) {
            field = newTitle
            binding.tvIconTitle.text = title
        }

}