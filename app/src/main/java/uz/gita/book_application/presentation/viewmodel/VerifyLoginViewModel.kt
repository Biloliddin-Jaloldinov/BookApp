package uz.gita.example.presentation.viewmodel

import androidx.lifecycle.LiveData

interface VerifyLoginViewModel {
    val openMainScreenLiveData:LiveData<Unit>


    fun verifyLogin(code:String)


}