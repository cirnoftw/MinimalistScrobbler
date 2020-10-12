package io.hwls.android.feature.settings

import android.os.Bundle
import android.view.View
import io.hwls.android.R
import io.hwls.android.common.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class SettingsFragment : BaseFragment() {
    private val viewModel: SettingsViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginState.observe(viewLifecycleOwner) {
            tokenView.text = it?.username
                ?.let { username -> getString(R.string.auth_logged_in, username) }
                ?: getString(R.string.auth_not_logged_in)
        }
        loginButton.setOnClickListener {
            viewModel.login(usernameInputView.text.toString(), passwordInputView.text.toString())
        }
    }
}