package binar.greta.challengechapter5.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binar.greta.challengechapter5.R
import binar.greta.challengechapter5.model.GetAllFilmResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailfilm = intent.getParcelableExtra<GetAllFilmResponseItem>("detailfilm")

        txt_sutradaraDetail.text = detailfilm?.director
        txt_tanggalDetail.text = detailfilm?.releaseDate
        txt_deskripsiDetail.text = detailfilm?.synopsis
        txt_judulDetail.text = detailfilm?.title

        Glide.with(this).load(detailfilm?.image).into(img_detail)


    }
}