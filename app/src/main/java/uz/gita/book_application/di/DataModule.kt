package uz.gita.book_application.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.book_application.data.local.room.data.MyDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @[Provides Singleton]
    fun getDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MyDataBase::class.java,
        "Book_Database"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideYourDao(db: MyDataBase) = db.getBookDao()



}