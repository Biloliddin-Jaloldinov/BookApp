package uz.gita.exam9.domain.usecase.impl

import uz.gita.exam9.data.remote.request.VerifyRequest
import uz.gita.exam9.domain.repository.VerifyRegisterRepository
import uz.gita.exam9.domain.usecase.VerifyRegisterUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VerifyRegisterUseCaseImpl @Inject constructor(private val repository: VerifyRegisterRepository) : VerifyRegisterUseCase {
    override fun verify(code: VerifyRequest) = repository.verify(code)
}