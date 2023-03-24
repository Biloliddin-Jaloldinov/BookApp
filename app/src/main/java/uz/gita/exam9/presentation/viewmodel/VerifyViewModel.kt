package uz.gita.exam9.presentation.viewmodel

import androidx.lifecycle.LiveData

interface VerifyViewModel {
    val openMainScreenLiveData:LiveData<Unit>


    fun verify(code:String)
}