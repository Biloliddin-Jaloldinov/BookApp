package uz.gita.exam9.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.local.room.entites.BookEntity

interface AuthorBooksRepository {
    fun insertBook(book: BookEntity)

    fun deleteBook(book: BookEntity): Flow<Result<Unit>>

    fun editBook(book: BookEntity): Flow<Result<Unit>>

    fun syncBook(book: BookEntity): Flow<Result<Unit>>

    fun getBookFromServer(): Flow<Result<Unit>>

    fun getAllBooks(): List<BookEntity>

    fun getAddBooks(): List<BookEntity>
}