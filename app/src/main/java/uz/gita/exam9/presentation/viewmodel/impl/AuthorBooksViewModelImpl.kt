package uz.gita.exam9.presentation.viewmodel.impl

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.exam9.aaa.ConnectionReceiver
import uz.gita.exam9.aaa.service.MyService
import uz.gita.exam9.data.local.room.entites.BookEntity
import uz.gita.exam9.domain.usecase.AuthorBookUseCase
import uz.gita.exam9.presentation.viewmodel.AuthorBooksViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorBooksViewModelImpl @Inject constructor(
    private val useCase: AuthorBookUseCase,
) : AuthorBooksViewModel, ViewModel() {
    override val booksListLiveData: MutableLiveData<List<BookEntity>> = MutableLiveData<List<BookEntity>>()
    override val openEditContactDialogLiveData: MutableLiveData<BookEntity> = MutableLiveData<BookEntity>()
    override val openAddContactDialogLiveData: MutableLiveData<Unit> = MutableLiveData<Unit>()
    override val errorLiveData: MutableLiveData<String> = MutableLiveData<String>()

    init {

        getBooks()
    }

    override fun openAddDialog() {
        openAddContactDialogLiveData.value = Unit
    }

    override fun openEditDialog(bookEntity: BookEntity) {
        openEditContactDialogLiveData.value = bookEntity
    }

    override fun addBook(bookEntity: BookEntity) {
        useCase.insertBook(bookEntity)

        getBooks()
    }

    override fun deleteBook(bookEntity: BookEntity) {
        if (bookEntity.isGotFromServer == 0) {
            errorLiveData.value = "Not sync"
            return
        }
        viewModelScope.launch {
            useCase.deleteBook(bookEntity).collect {
                it.onSuccess {
                    getBooks()
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }
        }


    }

    override fun editBook(bookEntity: BookEntity) {
        if (bookEntity.isGotFromServer == 0) {
            errorLiveData.value = "Not sync"
            return
        }

        viewModelScope.launch {
            useCase.editBook(bookEntity).collect() {
                it.onSuccess {
                    getBooks()
                }
                it.onFailure {
                    errorLiveData.value = it.message
                }
            }
        }
    }


    override fun syncDatabase() {
        val booksList = useCase.getAddBooks()
        if (booksList.isEmpty()) {
            downloadBooks()
        } else {
            for (book in booksList)
                useCase.syncBook(book).onEach { it ->
                    it.onSuccess {
                        downloadBooks()
                    }
                    it.onFailure {
                        errorLiveData.value = it.message
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun downloadBooks() {
        useCase.getBookFromServer().onEach {
            it.onSuccess {
                getBooks()
            }
            it.onFailure {
                errorLiveData.value = it.message
            }
        }.launchIn(viewModelScope)
    }

    private fun getBooks() {
        booksListLiveData.value = useCase.getAllBooks()
    }


}

