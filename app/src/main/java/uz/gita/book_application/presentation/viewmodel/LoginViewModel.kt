package uz.gita.book_application.presentation.viewmodel

import androidx.lifecycle.LiveData

interface LoginViewModel {
    val openRegisterScreenLiveData: LiveData<Unit>
    val openVerifyLoginScreenLiveData: LiveData<Unit>
    val notConnectionLiveData: LiveData<Unit>
    val progressLiveData: LiveData<Boolean>

    fun openSignUpScreenLiveData()
    fun login(phone:String,password:String)
}