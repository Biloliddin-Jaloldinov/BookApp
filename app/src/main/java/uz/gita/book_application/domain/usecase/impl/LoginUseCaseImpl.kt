package uz.gita.book_application.domain.usecase.impl

import uz.gita.book_application.data.remote.request.LoginRequest
import uz.gita.book_application.domain.repository.LoginRepository
import uz.gita.book_application.domain.usecase.LoginUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCaseImpl @Inject constructor(private val repository: LoginRepository): LoginUseCase {
    override fun login(loginRequest: LoginRequest) = repository.login(loginRequest)
}