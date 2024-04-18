package uz.gita.book_application.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.book_application.data.remote.request.VerifyRequest

interface VerifyRegisterRepository {
    fun verify(code: VerifyRequest): Flow<Result<Unit>>
}