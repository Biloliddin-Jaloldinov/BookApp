package uz.gita.book_application.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.book_application.data.remote.request.VerifyLoginRequest

interface VerifyLoginUseCase {
    fun verifyLogin(code: VerifyLoginRequest): Flow<Result<Unit>>
}