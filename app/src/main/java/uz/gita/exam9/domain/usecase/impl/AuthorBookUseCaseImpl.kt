package uz.gita.exam9.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.exam9.data.local.room.entites.BookEntity
import uz.gita.exam9.domain.repository.AuthorBooksRepository
import uz.gita.exam9.domain.usecase.AuthorBookUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorBookUseCaseImpl @Inject constructor(private val repository: AuthorBooksRepository) : AuthorBookUseCase {

    override fun insertBook(book: BookEntity) {
        repository.insertBook(book)
    }

    override fun deleteBook(book: BookEntity): Flow<Result<Unit>> = flow {
        repository.deleteBook(book).collect { result ->
            result.onSuccess {
                emit(Result.success(Unit))
            }
            result.onFailure {
                emit(Result.failure(Exception(it.message)))
            }
        }
    }

    override fun editBook(book: BookEntity): Flow<Result<Unit>> = flow {
        repository.editBook(book).collect { it ->
            it.onSuccess {
                emit(Result.success(Unit))
            }
            it.onFailure {
                emit(Result.failure(Exception(it.message)))
            }
        }
    }

    override fun syncBook(book: BookEntity): Flow<Result<Unit>> = flow {

        repository.syncBook(book).collect { it ->
            it.onSuccess {
                emit(Result.success(Unit))
            }
            it.onFailure {
                emit(Result.failure(Exception(it.message)))
            }
        }
    }

    override fun getBookFromServer(): Flow<Result<Unit>> = flow {
        repository.getBookFromServer().collect { it ->
            it.onSuccess {
                emit(Result.success(Unit))
            }
            it.onFailure {
                emit(Result.failure(Exception(it.message)))
            }
        }
    }

    override fun getAllBooks(): List<BookEntity> {
        return repository.getAllBooks()
    }


    override fun getAddBooks() = repository.getAddBooks()
}


