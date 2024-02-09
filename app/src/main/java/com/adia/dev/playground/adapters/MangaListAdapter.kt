package com.adia.dev.playground.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adia.dev.playground.R
import com.adia.dev.playground.models.Manga
import com.bumptech.glide.Glide

class MangaListAdapter(private var mangas: List<Manga>) :
    RecyclerView.Adapter<MangaListAdapter.MangaViewHolder>() {

    lateinit var context: Context

    class MangaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(manga: Manga, context: Context) {
            itemView.findViewById<TextView>(R.id.mangaTitle).apply {
                this.text = manga.attributes.title["en"] ?: "No title"
            }


            val coverArtFilename = Manga.getCoverArtFilename(manga)
            val coverArtUrl =
                coverArtFilename?.let { context.getString(R.string.base_cover_url, manga.id, it) }
                    ?: "https://via.placeholder.com/150"


            Glide.with(itemView)
                .load(coverArtUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.findViewById(R.id.mangaImage))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangaViewHolder {
        context = parent.context

        val view =
            LayoutInflater.from(context).inflate(R.layout.manga_list_item, parent, false)
        return MangaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.bind(mangas[position], context)
    }

    override fun getItemCount() = mangas.size

    fun updateMangas(newMangas: List<Manga>) {
        mangas = newMangas
        notifyDataSetChanged()
    }
}
