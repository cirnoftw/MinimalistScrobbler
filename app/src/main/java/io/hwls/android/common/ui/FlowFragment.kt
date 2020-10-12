package io.hwls.android.common.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import io.hwls.android.R
import io.hwls.android.common.extension.setLaunchScreen
import io.hwls.android.common.navigation.CommonNavigationViewModel
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command

abstract class FlowFragment : BaseFragment() {
    private val viewModelOwner
        get() = ViewModelOwner.from(this)

    val navigation: CommonNavigationViewModel by sharedViewModel(owner = { viewModelOwner })

    override val layoutRes: Int = R.layout.layout_container

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    open val launchScreen: SupportAppScreen? = null

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.container) {
            override fun activityBack() {
                router?.finishFlow()
            }

            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                // Fix incorrect order lifecycle callback of MainFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            launchScreen?.let(navigator::setLaunchScreen)
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: router?.exit()
    }

    override fun onResume() {
        super.onResume()
        navigation.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigation.navigatorHolder.removeNavigator()
        super.onPause()
    }
}