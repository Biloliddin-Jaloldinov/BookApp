package uz.gita.book_application.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.book_application.data.local.room.entites.BookEntity

interface AuthorBooksViewModel {
    val booksListLiveData :  LiveData<List<BookEntity>>
    val openEditContactDialogLiveData : LiveData<BookEntity>
    val openAddContactDialogLiveData : LiveData<Unit>
    val errorLiveData : LiveData<String>

    fun openAddDialog()

    fun openEditDialog(bookEntity: BookEntity)

    fun addBook(bookEntity: BookEntity)

    fun deleteBook(bookEntity: BookEntity)

    fun editBook(bookEntity: BookEntity)

    fun syncDatabase()

}

