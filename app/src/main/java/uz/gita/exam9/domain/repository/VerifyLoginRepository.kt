package uz.gita.exam9.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.remote.request.VerifyLoginRequest

interface VerifyLoginRepository {
    fun verifyLogin(code: VerifyLoginRequest): Flow<Result<Unit>>
}