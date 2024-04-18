package uz.gita.book_application.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.book_application.data.local.mypref.AppPreferences
import uz.gita.book_application.presentation.viewmodel.SplashViewModel
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(private val pref: AppPreferences) : SplashViewModel, ViewModel() {

    private val executors = Executors.newSingleThreadExecutor()
    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    override val openBooksScreenLiveData = MutableLiveData<Unit>()


    init {
        executors.execute {
            Thread.sleep(1000)
            if (pref.startScreen == "LOGIN") openLoginScreenLiveData.postValue(Unit)
            else openBooksScreenLiveData.postValue(Unit)
        }
    }
}