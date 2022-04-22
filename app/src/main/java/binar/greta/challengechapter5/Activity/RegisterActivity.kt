package binar.greta.challengechapter5.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import binar.greta.challengechapter5.R
import binar.greta.challengechapter5.model.Responseuser
import binar.greta.challengechapter5.vm.ViewModelUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var datafilm : Responseuser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener {
            if (edt_emailRegis.text.isNotEmpty() &&
                    edt_passwordRegis.text.isNotEmpty() &&
                    edt_usernameRegis.text.isNotEmpty() &&
                    edt_konfirmasiPassRegis.text.isNotEmpty()){
                        if (edt_passwordRegis.text.toString() != edt_konfirmasiPassRegis.text.toString()){
                            Toast.makeText(this, "Konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show()
                        }else{
                            dataRegister()
                        }
            }else{
                Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun dataRegister(){
        val emailRegis = edt_emailRegis.text.toString()
        val passRegis = edt_passwordRegis.text.toString()
        val usernameRegis = edt_usernameRegis.text.toString()

        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveFilmObserver().observe(this, {

            if(it != null){
                Toast.makeText(this, "Register berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeRegister(emailRegis,passRegis, usernameRegis)
    }
}