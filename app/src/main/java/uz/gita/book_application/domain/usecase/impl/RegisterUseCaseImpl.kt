package uz.gita.book_application.domain.usecase.impl

import uz.gita.book_application.data.remote.request.RegisterRequest
import uz.gita.book_application.domain.repository.RegisterRepository
import uz.gita.book_application.domain.usecase.RegisterUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUseCaseImpl @Inject constructor(private val repository : RegisterRepository) : RegisterUseCase {
    override fun register(registerRequest: RegisterRequest) = repository.register(registerRequest)
}