package uz.gita.book_application.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.book_application.data.remote.request.LoginRequest

interface LoginUseCase {
    fun login(loginRequest: LoginRequest): Flow<Result<Unit>>
}