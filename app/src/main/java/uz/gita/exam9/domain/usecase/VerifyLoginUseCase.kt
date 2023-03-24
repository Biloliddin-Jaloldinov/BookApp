package uz.gita.exam9.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.remote.request.VerifyLoginRequest

interface VerifyLoginUseCase {
    fun verifyLogin(code: VerifyLoginRequest): Flow<Result<Unit>>
}