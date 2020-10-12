package io.hwls.android.common.ui

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.FragmentParams
import kotlin.reflect.KClass

class KFragmentParams(kFragmentClass: KClass<out Fragment>, arguments: Bundle = bundleOf()) {
    val value: FragmentParams = FragmentParams(kFragmentClass.java, arguments)
}