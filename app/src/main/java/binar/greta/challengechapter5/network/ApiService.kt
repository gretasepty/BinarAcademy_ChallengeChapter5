package binar.greta.challengechapter5.network

import binar.greta.challengechapter5.model.GetAllFilmResponseItem
import binar.greta.challengechapter5.model.ResponseRegister
import binar.greta.challengechapter5.model.Responseuser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login.php")
    @FormUrlEncoded
    fun loginUser(
            @Field("email") email : String,
            @Field("password")password : String
    ) : Call<Responseuser>

    @GET("apifilm.php")
    fun getAllFilm() : Call<List<GetAllFilmResponseItem>>

    @POST("register.php")
    @FormUrlEncoded
    fun registerUser(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("username") username : String
    ) : Call<Responseuser>

    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
            @Field("id")id : String,
            @Field("address")address : String,
            @Field("complete_name")complete_name : String,
            @Field("dateofbirth")dateofbirth : String,
            @Field("username")username : String,
    ) : Call<Responseuser>
}