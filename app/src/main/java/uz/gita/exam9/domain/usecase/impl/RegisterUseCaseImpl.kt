package uz.gita.exam9.domain.usecase.impl

import uz.gita.exam9.data.remote.request.RegisterRequest
import uz.gita.exam9.domain.repository.RegisterRepository
import uz.gita.exam9.domain.usecase.RegisterUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUseCaseImpl @Inject constructor(private val repository : RegisterRepository) : RegisterUseCase {
    override fun register(registerRequest: RegisterRequest) = repository.register(registerRequest)
}