package uz.gita.exam9.data.local.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.exam9.data.local.room.daos.BookDao
import uz.gita.exam9.data.local.room.entites.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class MyDataBase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

}