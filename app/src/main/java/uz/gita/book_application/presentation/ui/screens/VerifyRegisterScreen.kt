package uz.gita.book_application.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.book_application.utils.myApply
import uz.gita.book_application.presentation.viewmodel.VerifyViewModel
import uz.gita.book_application.presentation.viewmodel.impl.VerifyViewModelImpl
import uz.gita.book_application.R
import uz.gita.book_application.databinding.ScreenRegisterVerifyBinding

@AndroidEntryPoint
class VerifyRegisterScreen : Fragment(R.layout.screen_register_verify) {


    private val viewModel : VerifyViewModel by viewModels<VerifyViewModelImpl>()
    private val binding by viewBinding(ScreenRegisterVerifyBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        super.onViewCreated(view, savedInstanceState)
        buttonConfirm.setOnClickListener { viewModel.verify(CodeVerifyView.enteredCode) }
        viewModel.openMainScreenLiveData.observe(viewLifecycleOwner, openMainScreenLiveDataObserver)

    }

    private val openMainScreenLiveDataObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_verifyRegisterScreen_to_authorBooksListScreen)
    }

}