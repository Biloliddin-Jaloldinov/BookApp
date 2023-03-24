package uz.gita.exam9.domain.usecase.impl

import uz.gita.exam9.data.remote.request.VerifyLoginRequest
import uz.gita.exam9.domain.repository.VerifyLoginRepository
import uz.gita.exam9.domain.usecase.VerifyLoginUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VerifyLoginUseCaseImpl @Inject constructor(private val repository: VerifyLoginRepository) : VerifyLoginUseCase {
    override fun verifyLogin(code: VerifyLoginRequest) = repository.verifyLogin(code)

}