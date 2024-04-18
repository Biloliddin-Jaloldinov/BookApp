package uz.gita.book_application.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.book_application.data.local.mypref.AppPreferences
import uz.gita.book_application.data.remote.api.BookApi
import uz.gita.book_application.data.remote.request.VerifyLoginRequest
import uz.gita.book_application.domain.repository.VerifyLoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VerifyLoginRepositoryImpl @Inject constructor(private val pref: AppPreferences, private val authApi: BookApi) : VerifyLoginRepository {

    override fun verifyLogin(code: VerifyLoginRequest): Flow<Result<Unit>> = flow<Result<Unit>> {
        val response = authApi.verifyLogin("Bearer ${pref.token}", code)
        if (response.isSuccessful) {
            response.body()?.let {
                pref.startScreen = "BookScreen"
                pref.accessToken1 = it.token
                emit(Result.success(Unit))
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)
}
