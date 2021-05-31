package com.example.pagmovies.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pagmovies.R
import com.example.pagmovies.data.Result
import com.example.pagmovies.ui.main.MainViewModel
import com.squareup.picasso.Picasso

class DetailsFragment(val result: Result)  : Fragment() {

    val detailsImgMovie by lazy { view?.findViewById<ImageView>(R.id.details_image_movie) }
    val detailsImgBackMovie by lazy { view?.findViewById<ImageView>(R.id.details_imageback_movie) }
    val detailsTitleMovie by lazy { view?.findViewById<TextView>(R.id.details_title_movie) }
    val detailsDescriptionMovie by lazy { view?.findViewById<TextView>(R.id.details_description) }
    val detailsValueRating by lazy { view?.findViewById<TextView>(R.id.details_value_rating) }
    val detailsRatingBar by lazy { view?.findViewById<RatingBar>(R.id.details_ratingbar) }
    val detailsDateMovie by lazy { view?.findViewById<TextView>(R.id.details_date) }
    val detailsFav by lazy { view?.findViewById<ImageView>(R.id.details_favorite) }
    val detailsFavAdd by lazy { view?.findViewById<ImageView>(R.id.details_favorite_add) }

    companion object {
        fun newInstance(it: Result) = DetailsFragment(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val baseUrlImage = "https://image.tmdb.org/t/p/"
        val tamanhoImage = "w500/"

        addFav()

        detailsTitleMovie?.text = result.title
        detailsDescriptionMovie?.text = result.overview
        detailsValueRating?.text = (result.vote_average?.div(2)).toString()
        detailsDateMovie?.text = result.release_date
        Picasso.get().load(baseUrlImage + tamanhoImage + result.poster_path).into(detailsImgMovie)
        Picasso.get().load(baseUrlImage + tamanhoImage + result.backdrop_path).into(detailsImgBackMovie)

        var valueRating = result.vote_average?.div(2)
        if (valueRating != null) {
            detailsRatingBar!!.rating = valueRating.toFloat()
        }
    }

    fun addFav(){
        detailsFav?.setOnClickListener {
            detailsFavAdd?.visibility = View.VISIBLE
        }

        detailsFavAdd?.setOnClickListener {
            detailsFavAdd?.visibility = View.GONE
        }
    }
}