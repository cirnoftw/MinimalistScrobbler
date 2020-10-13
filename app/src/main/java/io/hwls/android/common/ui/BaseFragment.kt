package io.hwls.android.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.hwls.android.common.navigation.FlowNavigationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment : Fragment() {
    private val flowParent
        get() = this as? FlowFragment ?: getParent(this)

    val navigation: FlowNavigationViewModel by sharedViewModel(owner = { flowParent.viewModelOwner })

    protected val router by lazy { navigation.router }

    abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() {
        router.exit()
    }

    private fun getParent(fragment: Fragment): FlowFragment {
        return when {
            fragment is FlowFragment -> fragment
            fragment.parentFragment == null ->
                throw IllegalStateException("Fragment must have FlowFragment or Activity parent")
            else -> getParent(fragment.requireParentFragment())
        }
    }
}