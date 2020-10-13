package io.hwls.android.common.extension

import android.widget.CheckBox
import android.widget.ImageView

fun ImageView.expandArrow(fraction: Float) {
    rotation = 90f * fraction
}

inline fun CheckBox.protectedClickListener(crossinline listener: (CheckBox) -> Unit) =
    setOnClickListener {
        it as CheckBox
        it.isChecked = !it.isChecked
        listener(it)
    }