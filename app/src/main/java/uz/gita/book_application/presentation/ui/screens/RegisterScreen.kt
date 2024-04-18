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
import uz.gita.book_application.presentation.viewmodel.impl.RegisterViewModelImpl
import uz.gita.book_application.R
import uz.gita.book_application.databinding.ScreenRegisterBinding
import uz.gita.book_application.presentation.viewmodel.RegisterViewModel

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.screen_register) {

    private val viewModel : RegisterViewModel by viewModels<RegisterViewModelImpl>()
    private val binding by viewBinding(ScreenRegisterBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        super.onViewCreated(view, savedInstanceState)
        buttonRegister.setOnClickListener {
            viewModel.register(
                inputPhoneNumber.text.toString(),
                inputPassword.text.toString(),
                inputLastName.text.toString(),
                inputFirstName.text.toString()
            )
        }
        viewModel.openVerifyScreenLiveData.observe(viewLifecycleOwner, openVerifyScreenLiveData)

    }

    private val openVerifyScreenLiveData = Observer<Unit> {
        findNavController().navigate(R.id.action_registerScreen_to_verifyRegisterScreen)
    }



}