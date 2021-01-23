package com.practical.APIclient

import com.androidnetworking.common.Priority
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import org.json.JSONException

/**
 * Created by Hardik(22/01/2021)
 */
class API {

    val mainURL = "http://ws.audioscrobbler.com/2.0/"
    val getGenreCategory = mainURL
    val getAlbumList = mainURL
    val getArtistList = mainURL
    val getTrackList = mainURL
    val getGenreDetails = mainURL
    val getAlbumDetails = mainURL
    val getArtistDetails = mainURL
    val getTrackDetails = mainURL

    var headers: HashMap<String, String> = HashMap()

    constructor()

    /**
     * Set header as Authorization
     */
    fun getMovieList(): Rx2ANRequest {
        try {
            headers["Authorization"] = "Bearer Wookie2019"
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return Rx2AndroidNetworking.get(mainURL)
            .addHeaders(headers)
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun getGenreCategory(): Rx2ANRequest {
        return Rx2AndroidNetworking.get(getGenreCategory)
            .addHeaders(headers)
            .addQueryParameter("method", "chart.gettoptags")
            .addQueryParameter("api_key", "70d3197a875fd15c93bb1862c052da35")
            .addQueryParameter("format", "json")
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun getAlbumDetail(artist: String, album: String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(getAlbumDetails)
            .addHeaders(headers)
            .addQueryParameter("method", "album.getinfo")
            .addQueryParameter("artist", artist)
            .addQueryParameter("album", album)
            .addQueryParameter("api_key", "70d3197a875fd15c93bb1862c052da35")
            .addQueryParameter("format", "json")
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun getTrackDetail(artist: String, track: String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(getTrackDetails)
            .addHeaders(headers)
            .addQueryParameter("method", "track.getInfo")
            .addQueryParameter("artist", artist)
            .addQueryParameter("track", track)
            .addQueryParameter("api_key", "70d3197a875fd15c93bb1862c052da35")
            .addQueryParameter("format", "json")
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun getArtistDetail(artist: String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(getArtistDetails)
            .addHeaders(headers)
            .addQueryParameter("method", "artist.getinfo")
            .addQueryParameter("artist", artist)
            .addQueryParameter("api_key", "70d3197a875fd15c93bb1862c052da35")
            .addQueryParameter("format", "json")
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun getAlbumList(genre: String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(getAlbumList)
            .addHeaders(headers)
            .addQueryParameter("method", "tag.gettopalbums")
            .addQueryParameter("tag", genre)
            .addQueryParameter("api_key", "70d3197a875fd15c93bb1862c052da35")
            .addQueryParameter("format", "json")
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun getGenreDetails(genre: String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(getGenreDetails)
            .addHeaders(headers)
            .addQueryParameter("method", "tag.getinfo")
            .addQueryParameter("tag", genre)
            .addQueryParameter("api_key", "70d3197a875fd15c93bb1862c052da35")
            .addQueryParameter("format", "json")
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun getArtistList(genre: String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(getArtistList)
            .addHeaders(headers)
            .addQueryParameter("method", "tag.gettopartists")
            .addQueryParameter("tag", genre)
            .addQueryParameter("api_key", "70d3197a875fd15c93bb1862c052da35")
            .addQueryParameter("format", "json")
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    fun getTrackList(genre: String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(getTrackList)
            .addHeaders(headers)
            .addQueryParameter("method", "tag.gettoptracks")
            .addQueryParameter("tag", genre)
            .addQueryParameter("api_key", "70d3197a875fd15c93bb1862c052da35")
            .addQueryParameter("format", "json")
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }

    /**
     * Get Search data as per searchview
     * Set header as Authorization
     */
    fun getSearchList(searchName: String): Rx2ANRequest {
        try {
            headers["Authorization"] = "Bearer Wookie2019"
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return Rx2AndroidNetworking.get(mainURL)
            .addHeaders(headers)
            .addQueryParameter("q", searchName)
            .setTag("Request")
            .setPriority(Priority.HIGH)
            .build()
    }
}
