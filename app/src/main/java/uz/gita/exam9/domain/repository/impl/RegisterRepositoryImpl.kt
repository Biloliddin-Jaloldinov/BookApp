package uz.gita.exam9.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.exam9.data.local.mypref.AppPreferences
import uz.gita.exam9.data.remote.api.BookApi
import uz.gita.exam9.data.remote.request.RegisterRequest
import uz.gita.exam9.domain.repository.RegisterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepositoryImpl @Inject constructor(private val pref: AppPreferences, private val authApi: BookApi) : RegisterRepository {

    override fun register(authRequest: RegisterRequest): Flow<Result<Unit>> = flow<Result<Unit>> {
        val response = authApi.register(authRequest)
        if (response.isSuccessful) {
            response.body()?.let {
                pref.token = it.token
                emit(Result.success(Unit))
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)
}