package uz.gita.exam9.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.exam9.data.local.room.entites.BookEntity

interface AuthorBookUseCase {
    fun insertBook(book: BookEntity)

    fun deleteBook(book: BookEntity): Flow<Result<Unit>>

    fun editBook(book: BookEntity): Flow<Result<Unit>>

    fun syncBook(book: BookEntity): Flow<Result<Unit>>

    fun getBookFromServer(): Flow<Result<Unit>>

    fun getAllBooks(): List<BookEntity>

    fun getAddBooks(): List<BookEntity>
}