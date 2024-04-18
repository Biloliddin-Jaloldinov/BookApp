package uz.gita.book_application.data.local.room.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val fav: Boolean,
    val isGotFromServer: Int = 0,
    val isSynchronized: Int = 0,
    val isDeleted: Int = 0,
    val isUpdated: Int = 0
)