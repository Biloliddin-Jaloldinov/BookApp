package uz.gita.exam9.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.remote.request.RegisterRequest

interface RegisterUseCase {
    fun register(registerRequest: RegisterRequest): Flow<Result<Unit>>
}