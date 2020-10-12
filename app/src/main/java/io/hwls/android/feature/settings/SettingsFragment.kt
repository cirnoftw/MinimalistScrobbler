package io.hwls.android.feature.settings

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import io.hwls.android.R
import io.hwls.android.common.extension.toggleRotate
import io.hwls.android.common.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.cachapa.expandablelayout.ExpandableLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class SettingsFragment : BaseFragment() {
    private val viewModel: SettingsViewModel by viewModel()

    override val layoutRes: Int = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        authHeaderContainer.setOnClickListener {
            authContainer.toggle()
        }
        authLoginButton.setOnClickListener {
            if (!authUsernameInputView.text.isNullOrBlank() && !authPasswordInputView.text.isNullOrBlank()) {
                authContainer.collapse()
                viewModel.login(
                    authUsernameInputView.text.toString(),
                    authPasswordInputView.text.toString()
                )
            } else {
                if (authUsernameInputView.text.isNullOrBlank())
                    authUsernameInputLayout.error = getString(R.string.validator_error_empty)
                if (authPasswordInputView.text.isNullOrBlank())
                    authPasswordInputLayout.error = getString(R.string.validator_error_empty)
            }
        }
        authLogoutButton.setOnClickListener {
            authContainer.collapse()
            viewModel.logout()
        }
        authContainer.setOnExpansionUpdateListener { _, state ->
            if (state == ExpandableLayout.State.COLLAPSING || state == ExpandableLayout.State.EXPANDING) {
                authHeaderForwardImageView.toggleRotate()
            }
        }
    }

    private fun initObservers() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            it?.run {
                authHeaderLoginStateView.text = getString(R.string.auth_logged_in, username)
                authLoginContainer.visibility = GONE
                authLogoutContainer.visibility = VISIBLE
            } ?: run {
                authHeaderLoginStateView.setText(R.string.auth_not_logged_in)
                authLogoutContainer.visibility = GONE
                authLoginContainer.visibility = VISIBLE
            }
        }
    }
}