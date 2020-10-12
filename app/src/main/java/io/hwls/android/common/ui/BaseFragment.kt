package io.hwls.android.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    private val flowParent
        get() = this as? FlowFragment ?: getParent(this)

    protected val router
        get() = flowParent?.navigation?.router

    abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() {
        router?.exit()
    }

    private fun getParent(fragment: Fragment): FlowFragment? {
        return when {
            fragment is FlowFragment -> fragment
            fragment.parentFragment == null -> null
            else -> getParent(fragment.requireParentFragment())
        }
    }
}