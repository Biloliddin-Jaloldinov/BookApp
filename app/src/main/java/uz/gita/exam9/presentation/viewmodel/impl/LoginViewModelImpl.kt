package uz.gita.exam9.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.exam9.data.remote.request.LoginRequest
import uz.gita.exam9.domain.usecase.LoginUseCase
import uz.gita.exam9.presentation.viewmodel.LoginViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(private val useCase: LoginUseCase): LoginViewModel, ViewModel() {
    override val openRegisterScreenLiveData = MutableLiveData<Unit>()
    override val openVerifyLoginScreenLiveData = MutableLiveData<Unit>()
    override val notConnectionLiveData = MutableLiveData<Unit>()
    override val progressLiveData = MutableLiveData<Boolean>()

    override fun openSignUpScreenLiveData() {
        openRegisterScreenLiveData.value = Unit
    }

    override fun login(phone: String, password: String) {
        useCase.login(LoginRequest(phone, password)).onEach {
            it.onSuccess {
                openVerifyLoginScreenLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }
}