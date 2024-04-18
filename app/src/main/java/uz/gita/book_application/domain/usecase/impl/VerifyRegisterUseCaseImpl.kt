package uz.gita.book_application.domain.usecase.impl

import uz.gita.book_application.data.remote.request.VerifyRequest
import uz.gita.book_application.domain.repository.VerifyRegisterRepository
import uz.gita.book_application.domain.usecase.VerifyRegisterUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VerifyRegisterUseCaseImpl @Inject constructor(private val repository: VerifyRegisterRepository) : VerifyRegisterUseCase {
    override fun verify(code: VerifyRequest) = repository.verify(code)
}