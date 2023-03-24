package uz.gita.exam9.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.exam9.presentation.viewmodel.SplashViewModel
import uz.gita.exam9.presentation.viewmodel.impl.SplashViewModelImpl
import uz.gita.exam9.R

@AndroidEntryPoint
class SplashScreen :Fragment(R.layout.screen_splash){

    private val viewModel : SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.openBooksScreenLiveData.observe(viewLifecycleOwner,openBooksScreenLiveDataObserver)
        viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner,openLoginScreenLiveDataObserver)
    }

    private val openBooksScreenLiveDataObserver=Observer<Unit>{
        findNavController().navigate(R.id.action_splashScreen_to_authorBooksListScreen)
    }

    private val openLoginScreenLiveDataObserver=Observer<Unit>{
        findNavController().navigate(R.id.action_splashScreen_to_loginScreen)
    }
}