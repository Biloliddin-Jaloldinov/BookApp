package uz.gita.book_application.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.book_application.data.local.mypref.AppPreferences
import uz.gita.book_application.data.remote.api.BookApi
import uz.gita.book_application.data.remote.request.VerifyRequest
import uz.gita.book_application.domain.repository.VerifyRegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VerifyRegisterRepositoryImpl @Inject constructor(private val pref: AppPreferences, private val authApi: BookApi) : VerifyRegisterRepository {

    override fun verify(code: VerifyRequest): Flow<Result<Unit>> = flow<Result<Unit>> {
        val response = authApi.verify("Bearer ${pref.token}", code)
        if (response.isSuccessful) {
            response.body()?.let {
                pref.accessToken = it.token
                emit(Result.success(Unit))
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)
}