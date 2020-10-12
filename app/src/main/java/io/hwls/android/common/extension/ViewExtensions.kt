package io.hwls.android.common.extension

import android.widget.ImageView
import net.cachapa.expandablelayout.util.FastOutSlowInInterpolator

fun ImageView.toggleRotate() {
    animate()
        .setStartDelay(0)
        .rotation(if (rotation == 0f) 90f else 0f)
        .setDuration(200)
        .setInterpolator(FastOutSlowInInterpolator())
        .start()
}