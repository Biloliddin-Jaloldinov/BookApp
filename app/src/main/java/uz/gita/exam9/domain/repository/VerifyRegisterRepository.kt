package uz.gita.exam9.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.remote.request.VerifyRequest

interface VerifyRegisterRepository {
    fun verify(code: VerifyRequest): Flow<Result<Unit>>
}