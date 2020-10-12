package io.hwls.android.feature

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import io.hwls.android.R
import io.hwls.android.Screens
import io.hwls.android.common.MessageBus
import io.hwls.android.common.SystemMessage
import io.hwls.android.common.ui.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

@ExperimentalCoroutinesApi
@FlowPreview
@KoinExperimentalAPI
class MainActivity : AppCompatActivity(), Observer<SystemMessage> {
    private val messageBus: MessageBus by inject()
    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    private val navigator: Navigator =
        object : SupportAppNavigator(this, supportFragmentManager, R.id.container) {
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupKoinFragmentFactory()
        setContentView(R.layout.layout_container)
        observeMessages()
        router.newRootScreen(Screens.Flow.Settings())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        messageBus.messageBus.removeObserver(this)
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    override fun onChanged(t: SystemMessage?) {
        t?.let { Toast.makeText(this, t.message, Toast.LENGTH_LONG).show() }
    }

    private fun observeMessages() {
        messageBus.messageBus.observe(this, this)
    }
}