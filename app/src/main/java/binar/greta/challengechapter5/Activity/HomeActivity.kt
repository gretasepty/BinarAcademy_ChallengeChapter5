@file:Suppress("RedundantOverride", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate")

package binar.greta.challengechapter5.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import binar.greta.challengechapter5.R
import binar.greta.challengechapter5.adapter.AdapterFilm
import binar.greta.challengechapter5.model.GetAllFilmResponseItem
import binar.greta.challengechapter5.network.ApiClient
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        prefs = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)
        val dataUsername = prefs.getString("username", "")
        val dataid = prefs.getString("id", "")
        txt_usernameHome.append("Welcome $dataUsername!")

        img_profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        getDataFilm()

    }

    fun getDataFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<GetAllFilmResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetAllFilmResponseItem>>,
                    response: Response<List<GetAllFilmResponseItem>>
                ) {
                    if (response.isSuccessful){
                        val dataFilm = response.body()
                        val adapt = AdapterFilm(dataFilm!!){
                            val pindah = Intent(this@HomeActivity, DetailActivity::class.java)
                            pindah.putExtra("detailfilm", it)
                            startActivity(pindah)
                        }

                        val linma = LinearLayoutManager(applicationContext,
                        LinearLayoutManager.VERTICAL, false)

                        rv_film.layoutManager = linma
                        rv_film.adapter = adapt
                    }else{
                        Toast.makeText(this@HomeActivity, "Gagal", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<List<GetAllFilmResponseItem>>, t: Throwable) {
                    Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_LONG).show()
                }

            })

    }

    override fun onResume() {
        super.onResume()
        getDataFilm()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}