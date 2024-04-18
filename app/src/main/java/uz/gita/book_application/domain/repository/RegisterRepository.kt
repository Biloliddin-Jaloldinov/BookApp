package uz.gita.book_application.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.book_application.data.remote.request.RegisterRequest

interface RegisterRepository {
    fun register(authRequest: RegisterRequest): Flow<Result<Unit>>
}