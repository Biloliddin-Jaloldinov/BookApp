package uz.gita.exam9.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.exam9.data.remote.request.VerifyLoginRequest
import uz.gita.exam9.domain.usecase.VerifyLoginUseCase
import uz.gita.example.presentation.viewmodel.VerifyLoginViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyLoginViewModelImpl @Inject constructor(private val useCase: VerifyLoginUseCase) : VerifyLoginViewModel, ViewModel() {
    override val openMainScreenLiveData = MutableLiveData<Unit>()

    override fun verifyLogin(code: String) {
        useCase.verifyLogin(VerifyLoginRequest(code)).onEach {
            it.onSuccess {
                openMainScreenLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }
}