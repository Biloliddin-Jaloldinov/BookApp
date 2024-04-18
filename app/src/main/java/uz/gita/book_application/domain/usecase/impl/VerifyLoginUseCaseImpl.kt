package uz.gita.book_application.domain.usecase.impl

import uz.gita.book_application.data.remote.request.VerifyLoginRequest
import uz.gita.book_application.domain.repository.VerifyLoginRepository
import uz.gita.book_application.domain.usecase.VerifyLoginUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VerifyLoginUseCaseImpl @Inject constructor(private val repository: VerifyLoginRepository) : VerifyLoginUseCase {
    override fun verifyLogin(code: VerifyLoginRequest) = repository.verifyLogin(code)

}