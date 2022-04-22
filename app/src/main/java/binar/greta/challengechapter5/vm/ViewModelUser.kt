package binar.greta.challengechapter5.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.greta.challengechapter5.model.GetAllFilmResponseItem
import binar.greta.challengechapter5.model.Responseuser
import binar.greta.challengechapter5.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<Responseuser>

    init {
        liveDataFilm = MutableLiveData()
    }


    fun getLiveFilmObserver() : MutableLiveData<Responseuser>{
        return liveDataFilm
    }

//    Register
    fun makeRegister(email: String, password: String, username : String){
        ApiClient.instance.registerUser(email, password, username)
                .enqueue(object :Callback<Responseuser>{
                    override fun onResponse(call: Call<Responseuser>, response: Response<Responseuser>) {
                        if (response.isSuccessful){
                            liveDataFilm.postValue(response.body())
                        }else{
                            liveDataFilm.postValue(null)
                        }
                    }
                    override fun onFailure(call: Call<Responseuser>, t: Throwable) {
                        liveDataFilm.postValue(null)
                    }

                })
    }

//  Login
    fun makeApiFilm(email :String, password : String){
        ApiClient.instance.loginUser(email, password)
            .enqueue(object : Callback<Responseuser>{
                override fun onResponse(
                    call: Call<Responseuser>,
                    response: Response<Responseuser>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else{
                        liveDataFilm.postValue(null)
                    }
                }
                override fun onFailure(call: Call<Responseuser>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })

    }

//    UpdateProfile
    fun makeUpdateProfile(id : Int, alamat : String, nama : String,
                          tanggalLahir : String, username : String){
        ApiClient.instance.updateUser(id.toString(), alamat, nama, tanggalLahir, username)
                .enqueue(object : Callback<Responseuser>{
                    override fun onResponse(call: Call<Responseuser>, response: Response<Responseuser>) {
                        if(response.isSuccessful){
                            liveDataFilm.postValue(response.body())
                        }else{
                            liveDataFilm.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<Responseuser>, t: Throwable) {
                        liveDataFilm.postValue(null)
                    }

                })

    }
}