package uz.gita.book_application.data.remote.request

data class EditBookRequest(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
)
