package uz.gita.book_application.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.book_application.data.remote.request.VerifyRequest

interface VerifyRegisterUseCase {
    fun verify(code: VerifyRequest): Flow<Result<Unit>>
}