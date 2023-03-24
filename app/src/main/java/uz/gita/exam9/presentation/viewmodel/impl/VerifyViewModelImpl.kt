package uz.gita.exam9.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.exam9.data.remote.request.VerifyRequest
import uz.gita.exam9.domain.usecase.VerifyRegisterUseCase
import uz.gita.exam9.presentation.viewmodel.VerifyViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyViewModelImpl @Inject constructor(private val useCase: VerifyRegisterUseCase): VerifyViewModel, ViewModel() {
    override val openMainScreenLiveData = MutableLiveData<Unit>()

    override fun verify(code: String) {
        useCase.verify(VerifyRequest(code)).onEach {
            it.onSuccess {
                openMainScreenLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }
}