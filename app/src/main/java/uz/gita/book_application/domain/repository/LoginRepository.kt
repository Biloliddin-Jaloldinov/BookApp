package uz.gita.book_application.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.book_application.data.remote.request.LoginRequest

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Flow<Result<Unit>>
}