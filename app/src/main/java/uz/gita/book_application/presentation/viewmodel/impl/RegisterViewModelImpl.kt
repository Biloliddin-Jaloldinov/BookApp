package uz.gita.book_application.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import uz.gita.book_application.data.remote.request.RegisterRequest
import uz.gita.book_application.domain.usecase.RegisterUseCase
import uz.gita.book_application.presentation.viewmodel.RegisterViewModel
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
