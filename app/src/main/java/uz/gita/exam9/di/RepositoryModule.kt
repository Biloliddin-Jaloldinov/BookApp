package uz.gita.exam9.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.exam9.domain.repository.*
import uz.gita.exam9.domain.repository.AuthorBooksRepository
import uz.gita.exam9.domain.repository.impl.AuthorBooksRepositoryImpl
import uz.gita.exam9.domain.repository.impl.*

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getBooksRepository(impl: AuthorBooksRepositoryImpl): AuthorBooksRepository

    @Binds
    fun getLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    fun getRegisterRepository(impl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    fun getVerifyLoginRepository(impl: VerifyLoginRepositoryImpl): VerifyLoginRepository

    @Binds
    fun getVerifyRegisterRepository(impl: VerifyRegisterRepositoryImpl): VerifyRegisterRepository



}




