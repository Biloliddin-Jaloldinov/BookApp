package uz.gita.exam9.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import uz.gita.exam9.data.remote.request.RegisterRequest
import uz.gita.exam9.domain.usecase.RegisterUseCase
import uz.gita.example.presentation.viewmodel.RegisterViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(private val useCase: RegisterUseCase): RegisterViewModel, ViewModel() {
    override val openVerifyScreenLiveData = MutableLiveData<Unit>()
    override val progressLiveData = MutableLiveData<Boolean>()

    override fun register(phone: String, password: String, lastName: String, firstName: String) {
        useCase.register(RegisterRequest(phone, password, lastName, firstName)).onEach {
            it.onSuccess {
                openVerifyScreenLiveData.value = Unit
            }
        }
    }
}
