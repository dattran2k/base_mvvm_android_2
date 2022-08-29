package com.vcc.thth.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.vcc.thth.R

class CustomTab(context: Context, attributeSet: AttributeSet? = null) :
    LinearLayout(context, attributeSet) {

    var icon: ImageView
    var text: TextView
    var notiBadge: TextView

    init {
        inflate(getContext(), R.layout.custom_tab_viewpager, this)
        icon = findViewById(R.id.imgIconTab)
        text = findViewById(R.id.tvIconTab)
        notiBadge = findViewById(R.id.notification_badge)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val color = if(selected){
            R.color.active

        } else {
            R.color.gray_666
        }
        text.setTextColor(ContextCompat.getColor(context,color))
        icon.setColorFilter(ContextCompat.getColor(context,color), android.graphics.PorterDuff.Mode.SRC_IN)
    }
}