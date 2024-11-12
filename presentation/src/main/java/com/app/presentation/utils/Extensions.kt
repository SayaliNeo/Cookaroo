package com.app.presentation.utils

import android.view.View

object Extensions {
    fun View.visibilityToggle(shouldDisplay: Boolean) {
        if (shouldDisplay) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}