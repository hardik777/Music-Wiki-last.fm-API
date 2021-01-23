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
import kotlinx.android.synthetic.main.raw_search.view.*

/**
 * Created by Hardik(22/01/2021)
 */
class SearchListAdapter(
        var activity: Activity,
        private var arrayList: ArrayList<Movie>
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View? = null

        v = LayoutInflater.from(parent.context)
                .inflate(R.layout.raw_search, parent, false)

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

        holder.tvTitle.text = data.title

        var castName = ""
        for (i in data.cast.indices) {
            if (castName.isEmpty()) {
                castName = data.cast[i]
            } else {
                castName += ", " + data.cast[i]
            }
        }

        holder.tvCast.text = castName
        holder.tvDuration.text = "Duration : " + data.length
        holder.ratingBar.rating = (data.imdb_rating / 2).toFloat()

        activity.LoadImage(data.poster, holder.imgMovie)

        holder.itemView.setOnClickListener {

            val hashMap = HashMap<String, Any>()
            hashMap["JSON"] = Gson().toJson(data).toString()

//            (activity as Activity).NewIntentWithData(
//                    MovieDetailsActivity::class.java,
//                    hashMap,
//                    false,
//                    true
//            )
        }
    }

    /**
     * declare views
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvTitle = view.tvTitle
        var tvCast = view.tvCast
        var tvDuration = view.tvDuration
        var ratingBar = view.ratingBar
        var imgMovie = view.imgMovie
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}