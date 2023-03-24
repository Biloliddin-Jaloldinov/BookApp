package uz.gita.exam9.data.remote.request

data class EditBookRequest(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
)
