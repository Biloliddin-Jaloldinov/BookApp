package uz.gita.exam9.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.remote.request.VerifyRequest

interface VerifyRegisterUseCase {
    fun verify(code: VerifyRequest): Flow<Result<Unit>>
}