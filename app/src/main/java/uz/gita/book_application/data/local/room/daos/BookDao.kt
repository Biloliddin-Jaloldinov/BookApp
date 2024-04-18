package uz.gita.book_application.data.local.room.daos

import androidx.room.*
import uz.gita.book_application.data.local.room.entites.BookEntity

@Dao
interface BookDao {

    @Insert
    fun insertBook(bookEntity: BookEntity): Long

    @Delete
     fun deleteBook(bookEntity: BookEntity)

    @Update
     fun updateBook(bookEntity: BookEntity)

    @Query(
        "INSERT OR REPLACE INTO bookEntity(id,title,author,description,pageCount,fav,isGotFromServer," +
                "isSynchronized,isDeleted,isUpdated) VALUES(:id,:title,:author,:description,:pageCount,:fav,:isGotFromServer,:isSynchronized,:isDeleted,:isUpdated)"
    )
     fun insertWithId(
        id: Int,
        title: String,
        author: String,
        description: String,
        pageCount: Int,
        fav: Boolean,
        isGotFromServer: Int,
        isSynchronized: Int,
        isDeleted: Int,
        isUpdated: Int
    )


    @Query("SELECT * FROM BookEntity WHERE isDeleted = 0 ORDER BY title")
    fun getALlBooks(): List<BookEntity>

    @Query("UPDATE bookEntity SET isDeleted = 1 WHERE id = :pos")
     fun setDeleted(pos: Int)


    @Query("SELECT * FROM bookEntity WHERE isGotFromServer = 0")
    fun getAddedBooksBooks(): List<BookEntity>


}
