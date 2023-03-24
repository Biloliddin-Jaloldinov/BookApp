package uz.gita.exam9.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.exam9.utils.myApply
import uz.gita.exam9.presentation.viewmodel.impl.VerifyLoginViewModelImpl
import uz.gita.exam9.R
import uz.gita.exam9.databinding.ScreenVerifyLoginBinding
import uz.gita.example.presentation.viewmodel.VerifyLoginViewModel

@AndroidEntryPoint
class VerifyLoginScreen :Fragment(R.layout.screen_verify_login){

    private val viewModel : VerifyLoginViewModel by viewModels<VerifyLoginViewModelImpl>()
    private val binding by viewBinding(ScreenVerifyLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)=binding.myApply {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonConfirm.setOnClickListener { viewModel.verifyLogin(CodeVerifyView.enteredCode) }
        viewModel.openMainScreenLiveData.observe(viewLifecycleOwner,openMainScreenLiveDataObserver)
    }

    private val openMainScreenLiveDataObserver=Observer<Unit>{
        findNavController().navigate(R.id.action_verifyLoginScreen_to_authorBooksListScreen)
    }
}