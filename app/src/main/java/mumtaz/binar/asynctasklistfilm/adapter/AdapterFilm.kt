package mumtaz.binar.asynctasklistfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*
import mumtaz.binar.asynctasklistfilm.R
import mumtaz.binar.asynctasklistfilm.model.GetAllFilmResponseItem

class AdapterFilm  (): RecyclerView.Adapter<AdapterFilm.ViewHolder>() {

    private var datafilm : List<GetAllFilmResponseItem>? = null
    fun setDataFilm(film : List<GetAllFilmResponseItem>){
        this.datafilm = film
    }

    class ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return  ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(datafilm!![position].image).into(holder.itemView.img_filmm)

        holder.itemView.tv_judulfilm.text = datafilm!![position].title
        holder.itemView.tv_tglfilm.text = datafilm!![position].createdAt
        holder.itemView.tv_sutradarafilm.text = datafilm!![position].director


    }

    override fun getItemCount(): Int {
        if (datafilm == null) {
            return 0
        } else {
            return datafilm!!.size
        }
    }
}