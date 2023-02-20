package com.example.flixster2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
class TvShowsFragment(parentContext: Context) : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tv_shows_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.tShows) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY
        // Using the client, perform the HTTP request
        client[
                "https://api.themoviedb.org/3/tv/top_rated?api_key=34e61e3ddd6944220f1e09bcfb1b726d",
                params,
                object : JsonHttpResponseHandler()
                //Uncomment me once you complete the above sections!
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int, headers: Headers, json: JSON
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        //TODO - Parse JSON into Models

                        // val models : List<BestSellerBook> = null // Fix me!

                        val tvShows = json.jsonObject.get("results");
                        // val moviesRawJSON : String = resultsJSON.get("books").toString()

                        val gson = Gson()
                        val arrayTvshowsType = object : TypeToken<List<TvShows>>() {}.type
                        val models : List<TvShows> = gson.fromJson(tvShows.toString(), arrayTvshowsType)
                        recyclerView.adapter = TvShowsAdapter(models)


                        // Look for this in Logcat:
                        Log.d("TvShowsFragment", "response successful")
                        Log.d("json format", json.toString())
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("TvShowsFragment", errorResponse)
                        }
                    }
                }]


    }
}