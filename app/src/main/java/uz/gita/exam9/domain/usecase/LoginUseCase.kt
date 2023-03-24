package uz.gita.exam9.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.remote.request.LoginRequest

interface LoginUseCase {
    fun login(loginRequest: LoginRequest): Flow<Result<Unit>>
}