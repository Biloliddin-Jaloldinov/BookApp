package uz.gita.book_application.data.local.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.book_application.data.local.room.daos.BookDao
import uz.gita.book_application.data.local.room.entites.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class MyDataBase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

}