package io.hwls.android.feature.settings

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.material.slider.Slider
import io.hwls.android.R
import io.hwls.android.common.LoadingState
import io.hwls.android.common.extension.expandArrow
import io.hwls.android.common.extension.protectedClickListener
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
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        authHeaderContainer.setOnClickListener {
            authContainer.toggle()
        }
        authContainer.setOnExpansionUpdateListener { fraction, _ ->
            authHeaderForwardImageView.expandArrow(fraction)
        }
        authLoginButton.setOnClickListener {
            if (!authUsernameInputView.text.isNullOrBlank() && !authPasswordInputView.text.isNullOrBlank()) {
                // authContainer.collapse()
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
            // authContainer.collapse()
            viewModel.logout()
        }

        scrobblingHeaderContainer.setOnClickListener {
            scrobblingContainer.toggle()
        }
        scrobblingContainer.setOnExpansionUpdateListener { fraction, _ ->
            scrobblingHeaderForwardImageView.expandArrow(fraction)
        }
        scrobbleEnabledCheckView.protectedClickListener {
            viewModel.enableScrobbling(!scrobbleEnabledCheckView.isChecked)
        }
        scrobbleEnabledCheckView.setOnCheckedChangeListener { _, isChecked ->
            scrobbleNowPlayingEnabledCheckView.isEnabled = isChecked
        }
        scrobbleNowPlayingEnabledCheckView.protectedClickListener {
            viewModel.enabledNowPlaying(!scrobbleNowPlayingEnabledCheckView.isChecked)
        }
        scrobblePointSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
            }

            override fun onStopTrackingTouch(slider: Slider) {
                viewModel.setScrobblePoint(slider.value.toInt())
            }
        })
        scrobblePointSlider.addOnChangeListener { _, value, _ ->
            scrobblePointValueView.text = getString(R.string.scrobble_point_percent, value.toInt())
        }
    }

    private fun initObservers() {
        with(viewModel) {
            loginState.observe(viewLifecycleOwner) {
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
            authLoadingState.observe(viewLifecycleOwner) {
                when (it) {
                    is LoadingState.Loading -> authLoader.visibility = VISIBLE
                    is LoadingState.Loaded -> {
                        authLoader.visibility = GONE
                        authContainer.collapse(false)
                    }
                    is LoadingState.Error -> {
                        authLoader.visibility = GONE
                    }
                }
            }
            scrobblingEnabled.observe(viewLifecycleOwner) {
                scrobbleEnabledCheckView.isChecked = it
            }
            nowPlayingEnabled.observe(viewLifecycleOwner) {
                scrobbleNowPlayingEnabledCheckView.isChecked = it
            }
            scrobblingPoint.observe(viewLifecycleOwner) {
                val pointNormalized = it.coerceIn(50, 100)
                scrobblePointSlider.value = pointNormalized.toFloat()
                scrobblePointValueView.text =
                    getString(R.string.scrobble_point_percent, pointNormalized)
            }
            // scrobbleShort.observe(viewLifecycleOwner) {
            // }
        }
    }
}