package uz.gita.exam9.data.remote.api

import retrofit2.Response
import retrofit2.http.*
import uz.gita.exam9.data.remote.request.*
import uz.gita.exam9.data.remote.response.AuthResponse
import uz.gita.exam9.data.remote.response.BookResponse
import uz.gita.exam9.data.remote.response.ErrorResponse

interface BookApi {

    //auth
    @POST("auth/sign-up")
    suspend fun register(@Body data: RegisterRequest): Response<AuthResponse.RegisterResponse>

    @POST("auth/sign-up/verify")
    suspend fun verify(@Header("authorization") token: String, @Body() data: VerifyRequest): Response<AuthResponse.VerifyResponse>

    @POST("auth/sign-in")
    suspend fun login(@Header("authorization") token: String, @Body data: LoginRequest): Response<AuthResponse.LoginResponse>

    @POST("auth/sign-in/verify")
    suspend fun verifyLogin(@Header("authorization") token: String, @Body data: VerifyLoginRequest): Response<AuthResponse.VerifyLoginResponse>


    //book
    @GET("books")
    suspend fun getAllBooks(@Header("authorization") token: String): Response<List<BookResponse>>

    @POST("book")
    suspend fun addBook(@Header("authorization") token: String, @Body data: AddBookRequest): Response<BookResponse>

    @PUT("book")
    suspend fun editBook(@Header("authorization") token: String, @Body data: EditBookRequest): Response<BookResponse>

    @HTTP(method = "DELETE", path = "book", hasBody = true)
    suspend fun deleteBook(@Header("authorization") Authorization: String, @Body bookId: DeleteRequest): Response<ErrorResponse>
}