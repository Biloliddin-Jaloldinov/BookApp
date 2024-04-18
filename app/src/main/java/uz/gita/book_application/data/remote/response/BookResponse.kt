package uz.gita.book_application.data.remote.response

data class BookResponse(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val fav: Boolean
)
