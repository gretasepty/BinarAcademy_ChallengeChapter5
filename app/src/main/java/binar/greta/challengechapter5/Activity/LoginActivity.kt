@file:Suppress("unused", "MoveLambdaOutsideParentheses")

package binar.greta.challengechapter5.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import binar.greta.challengechapter5.R
import binar.greta.challengechapter5.model.Responseuser
import binar.greta.challengechapter5.vm.ViewModelUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var datafilm : Responseuser
    private lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefs = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)



        btn_login.setOnClickListener {
            if(edt_emailLogin.text.isNotEmpty() &&
                    edt_passLogin.text.isNotEmpty()){
                dataLogin()
            }else{
                Toast.makeText(this, "Username dan password kosong", Toast.LENGTH_SHORT).show()
            }
        }

        txt_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun dataLogin(){
        val textEmail = edt_emailLogin.text.toString()
        val textPassword = edt_passLogin.text.toString()
        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveFilmObserver().observe(this,{

            if (it != null){
                val sf = prefs.edit()
                sf.putString("email", textEmail).apply()
                sf.putString("id", it.id).apply()
                sf.putString("username", it.username).apply()

                Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()

            }else{
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiFilm(textEmail, textPassword)

    }



//    fun dataLogin(email : String, password : String){
//        ApiClient.instance.loginUser(email, password)
//                .enqueue(object : Callback<Responseuser>{
//                    override fun onResponse(call: Call<Responseuser>, response: Response<Responseuser>) {
//                        if (response.isSuccessful){
//                            Toast.makeText(this@LoginActivity, "Berhasil", Toast.LENGTH_LONG).show()
////                            startActivity(Intent(this, HomeActivity::class.java))
//                        }else{
//                            Toast.makeText(this@LoginActivity, "Salah", Toast.LENGTH_LONG).show()
//                        }
//                    }
//                    override fun onFailure(call: Call<Responseuser>, t: Throwable) {
//                        Toast.makeText(this@LoginActivity, "Salah", Toast.LENGTH_LONG).show()
//                    }
//                })
//    }
}