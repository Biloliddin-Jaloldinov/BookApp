package uz.gita.exam9.domain.usecase.impl

import uz.gita.exam9.data.remote.request.LoginRequest
import uz.gita.exam9.domain.repository.LoginRepository
import uz.gita.exam9.domain.usecase.LoginUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCaseImpl @Inject constructor(private val repository: LoginRepository): LoginUseCase {
    override fun login(loginRequest: LoginRequest) = repository.login(loginRequest)
}