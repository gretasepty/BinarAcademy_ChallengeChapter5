package binar.greta.challengechapter5.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binar.greta.challengechapter5.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btn_updateProfile.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        btn_logoutProfile.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}