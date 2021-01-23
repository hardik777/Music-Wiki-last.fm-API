package com.practical.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.practical.R
import com.practical.controller.LoadImage
import com.practical.controller.NewIntentWithData
import com.practical.model.Movie
import kotlinx.android.synthetic.main.raw_movie.view.*

/**
 * Created by Hardik(22/01/2021)
 */
class MovieListAdapter(
    var activity: Activity,
    private var arrayList: ArrayList<Movie>
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View? = null

        v = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_movie, parent, false)

        return ViewHolder(v!!)
    }

    /**
     * Get Total item count
     */
    override fun getItemCount(): Int {
        return arrayList.size
    }

    /**
     * Set data in views
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = arrayList[position]

        holder.tvMoviesName.text = data.title

        activity.LoadImage(data.poster, holder.imgMovie)

        holder.itemView.setOnClickListener {
            val hashMap = HashMap<String, Any>()
            hashMap["JSON"] = Gson().toJson(data).toString()

//            (activity as Activity).NewIntentWithData(
//                MovieDetailsActivity::class.java,
//                hashMap,
//                false,
//                false
//            )
        }
    }

    /**
     * declare views
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvMoviesName = view.tvMoviesName
        var imgMovie = view.imgMovie
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}