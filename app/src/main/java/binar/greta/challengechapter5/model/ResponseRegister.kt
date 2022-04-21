package binar.greta.challengechapter5.model


import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
)