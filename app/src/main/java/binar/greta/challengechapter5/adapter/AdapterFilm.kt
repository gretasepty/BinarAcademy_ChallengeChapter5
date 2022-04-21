package binar.greta.challengechapter5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.greta.challengechapter5.R
import binar.greta.challengechapter5.model.GetAllFilmResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*

class AdapterFilm(private var datafilm : List<GetAllFilmResponseItem>,
                    private var onclick : (GetAllFilmResponseItem)-> Unit)
    : RecyclerView.Adapter<AdapterFilm.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film,parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.txt_sutradara.text = datafilm[position].director
        holder.itemView.txt_tanggal.text = datafilm[position].releaseDate
        holder.itemView.txt_judul.text = datafilm[position].title

        Glide.with(holder.itemView.context).load(datafilm[position].image)
            .into(holder.itemView.img_film)

        holder.itemView.cardFilm.setOnClickListener{
            onclick(datafilm[position])
        }

    }

    override fun getItemCount(): Int {
        return datafilm.size
    }
}