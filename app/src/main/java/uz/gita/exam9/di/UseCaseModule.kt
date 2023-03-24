package uz.gita.exam9.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.exam9.domain.usecase.*
import uz.gita.exam9.domain.usecase.impl.*
import uz.gita.exam9.domain.usecase.AuthorBookUseCase
import uz.gita.exam9.domain.usecase.LoginUseCase
import uz.gita.exam9.domain.usecase.RegisterUseCase

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun getLoginUseCase(impl : LoginUseCaseImpl) : LoginUseCase

    @Binds
    fun getRegisterUseCase(impl : RegisterUseCaseImpl) : RegisterUseCase

    @Binds
    fun getAuthorBookUseCase(impl : AuthorBookUseCaseImpl) : AuthorBookUseCase

    @Binds
    fun getVerifyLoginUseCase(impl : VerifyLoginUseCaseImpl) : VerifyLoginUseCase

    @Binds
    fun getVerifyRegisterUseCase(impl: VerifyRegisterUseCaseImpl): VerifyRegisterUseCase

}

