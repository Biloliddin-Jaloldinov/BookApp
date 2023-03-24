package uz.gita.exam9.domain.repository.impl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.exam9.data.local.mypref.AppPreferences
import uz.gita.exam9.data.local.room.daos.BookDao
import uz.gita.exam9.data.local.room.entites.BookEntity
import uz.gita.exam9.data.remote.api.BookApi
import uz.gita.exam9.data.remote.request.AddBookRequest
import uz.gita.exam9.data.remote.request.DeleteRequest
import uz.gita.exam9.data.remote.request.EditBookRequest
import uz.gita.exam9.data.remote.response.ErrorResponse
import uz.gita.exam9.domain.repository.AuthorBooksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorBooksRepositoryImpl
@Inject constructor(
    private val api: BookApi,
    private val myPref: AppPreferences,
    private val myDataBase: BookDao,
    private val gson: Gson
) : AuthorBooksRepository {


    override fun insertBook(book: BookEntity) {
        myDataBase.insertBook(book)
    }


    override fun deleteBook(book: BookEntity): Flow<Result<Unit>> = flow<Result<Unit>> {
        val response = api.deleteBook("Bearer ${myPref.token}", DeleteRequest(book.id))
        if (response.isSuccessful) {
            response.body()?.let {

                myDataBase.deleteBook(book)
                emit(Result.success(Unit))
            }
        } else {

            response.errorBody()?.let {
                val errorResponse = gson.fromJson(it.string(), ErrorResponse::class.java)
                emit(Result.failure(Exception(errorResponse.message)))
            }
        }
    }.catch {

        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)

    override fun editBook(book: BookEntity): Flow<Result<Unit>> = flow<Result<Unit>> {
        val response = api.editBook("Bearer ${myPref.token}", EditBookRequest(book.id, book.title, book.author, book.description, book.pageCount))
        if (response.isSuccessful) {
            response.body()?.let {
                myDataBase.updateBook(book)
                emit(Result.success(Unit))
            }
        } else {
            response.errorBody()?.let {
                val errorResponse = gson.fromJson(it.string(), ErrorResponse::class.java)
                emit(Result.failure(Exception(errorResponse.message)))
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)


    override fun syncBook(book: BookEntity): Flow<Result<Unit>> = flow {
        val response = api.addBook("Bearer ${myPref.token}", AddBookRequest(book.title, book.author, book.description, book.pageCount))
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(Unit))
            }
        } else {
            response.errorBody()?.let {
                val errorResponse = gson.fromJson(it.string(), ErrorResponse::class.java)
                emit(Result.failure(Exception(errorResponse.message)))
                return@flow
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)

    override fun getBookFromServer(): Flow<Result<Unit>> = flow {
        val response = api.getAllBooks("Bearer ${myPref.token}")
        if (response.isSuccessful) {
            response.body()?.let { booksList ->
                clearDatabase(myDataBase.getALlBooks())
                for (book in booksList)
                    myDataBase.insertWithId(book.id, book.title, book.author, book.description, book.pageCount, book.fav, 1, 1, 0, 0)
                emit(Result.success(Unit))
            }
        } else {
            response.errorBody()?.let {
                val errorResponse = gson.fromJson(it.string(), ErrorResponse::class.java)
                emit(Result.failure(Exception(errorResponse.message)))
                return@flow
            }
        }
    }.catch {
        emit(Result.failure(Exception(it.message)))
    }.flowOn(Dispatchers.IO)


    override fun getAllBooks(): List<BookEntity> {
        return myDataBase.getALlBooks()
    }

    private fun clearDatabase(books: List<BookEntity>) {
        for (book in books) {
            myDataBase.deleteBook(book)
        }
    }

    override fun getAddBooks() = myDataBase.getAddedBooksBooks()
}

