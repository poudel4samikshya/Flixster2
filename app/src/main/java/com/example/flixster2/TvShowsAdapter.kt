package com.example.flixster2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

const val TVSHOWS_EXTRA = "TVSHOWS_EXTRA"
private const val TAG = "TvShowsAdapterprivate"
class TvShowsAdapter (private val tvshows: List<TvShows>) :
    RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tv_shows_fragment, parent, false)
        return TvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvshow = tvshows[position]
        ViewCompat.setTransitionName(holder.itemView.findViewById(R.id.tvName), tvshow.tvName)
        holder.bind(tvshow)
    }

    override fun getItemCount() = tvshows.size

    inner class TvShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val name = itemView.findViewById(R.id.tvName) as TextView
        private val tposter = itemView.findViewById(R.id.tvPoster) as ImageView

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(tvshow: TvShows) {
            name.text = tvshow.tvName

            val radius = 30
            Glide.with(itemView.context)
                .load(("https://image.tmdb.org/t/p/w500/"+tvshow.tvPoster_path))
                .centerInside()
                .transform(RoundedCorners(radius))
                .into(tposter)
        }

        override fun toString(): String {
            return name.toString()
        }

        override fun onClick(v: View?) {
            val tvshow = tvshows[absoluteAdapterPosition]


            val intent = Intent(itemView.context, TvShowsActivity::class.java)
            intent.putExtra(TVSHOWS_EXTRA, tvshow)

            itemView.context.startActivity(intent)
        }
    }
}


