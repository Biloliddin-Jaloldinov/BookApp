package uz.gita.book_application.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.book_application.data.remote.request.RegisterRequest

interface RegisterUseCase {
    fun register(registerRequest: RegisterRequest): Flow<Result<Unit>>
}