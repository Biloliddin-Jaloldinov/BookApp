package uz.gita.book_application.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.book_application.data.remote.request.VerifyLoginRequest

interface VerifyLoginRepository {
    fun verifyLogin(code: VerifyLoginRequest): Flow<Result<Unit>>
}