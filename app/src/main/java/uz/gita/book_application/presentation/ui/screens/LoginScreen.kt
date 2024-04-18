package uz.gita.book_application.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.book_application.R
import uz.gita.book_application.databinding.ScreenLoginBinding
import uz.gita.book_application.presentation.viewmodel.LoginViewModel
import uz.gita.book_application.presentation.viewmodel.impl.LoginViewModelImpl
import uz.gita.book_application.utils.myApply

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()
    private val binding by viewBinding(ScreenLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        super.onViewCreated(view, savedInstanceState)

        buttonRegister.setOnClickListener { viewModel.openSignUpScreenLiveData() }
        viewModel.openRegisterScreenLiveData.observe(viewLifecycleOwner, openRegisterScreenLiveDataObserver)
        buttonLogin.setOnClickListener {
            viewModel.login(inputPhoneNumber.text.toString(), inputPassword.text.toString())
        }
        viewModel.openVerifyLoginScreenLiveData.observe(viewLifecycleOwner, openVerifyLoginScreenLiveDataObserver)
    }

    private val openVerifyLoginScreenLiveDataObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_verifyLoginScreen)
    }

    private val openRegisterScreenLiveDataObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_loginScreen_to_registerScreen)
    }

}