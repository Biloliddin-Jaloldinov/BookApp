package uz.gita.book_application.presentation.viewmodel

import androidx.lifecycle.LiveData

interface VerifyViewModel {
    val openMainScreenLiveData:LiveData<Unit>


    fun verify(code:String)
}