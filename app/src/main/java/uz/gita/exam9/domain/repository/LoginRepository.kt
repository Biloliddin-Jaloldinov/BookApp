package uz.gita.exam9.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.remote.request.LoginRequest

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Flow<Result<Unit>>
}