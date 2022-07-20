@file:Suppress("MoveLambdaOutsideParentheses")

package binar.greta.challengechapter5.Activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import binar.greta.challengechapter5.R
import binar.greta.challengechapter5.vm.ViewModelUser
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var prefs : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        prefs = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)

        btn_updateProfile.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        btn_updateProfile.setOnClickListener {
            dataProfile()
        }

        btn_logoutProfile.setOnClickListener {
            val alertLogout = AlertDialog.Builder(this)
                    .setTitle("LOGOUT")
                    .setMessage("Yakin anda ingin keluar?")
                    .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                        prefs.edit().clear().apply()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
            alertLogout.show()


        }
    }

    private fun dataProfile(){
        val id = prefs.getString("id", "")
        val alamat = edt_alamatProfile.text.toString()
        val nama = edt_namaProfile.text.toString()
        val tanggalLahir = edt_tanggalLahirProfile.text.toString()
        val username = edt_usernameProfile.text.toString()


        val viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveFilmObserver().observe(this,{
            if(it != null){
                Toast.makeText(this, "Gagal Update Data", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Berhasil Update Data", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java ))
                finish()
            }
        })
        viewModel.makeUpdateProfile(id!!.toInt(), alamat, nama, tanggalLahir, username)
    }
}