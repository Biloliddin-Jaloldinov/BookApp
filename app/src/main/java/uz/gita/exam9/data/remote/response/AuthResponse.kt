package uz.gita.exam9.data.remote.response

sealed class AuthResponse {

    data class RegisterResponse(
        val token:String
    )

    data class VerifyResponse(
        val token:String
    )

    data class VerifyLoginResponse(
        val token:String
    )

    data class LoginResponse(
        val token:String
    )

}