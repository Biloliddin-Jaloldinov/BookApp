package uz.gita.exam9.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.remote.request.RegisterRequest

interface RegisterRepository {
    fun register(authRequest: RegisterRequest): Flow<Result<Unit>>
}