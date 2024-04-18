package uz.gita.book_application.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.book_application.domain.repository.*
import uz.gita.book_application.domain.repository.AuthorBooksRepository
import uz.gita.book_application.domain.repository.impl.AuthorBooksRepositoryImpl
import uz.gita.book_application.domain.repository.impl.*

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




