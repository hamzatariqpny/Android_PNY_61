package com.dev.androidpny61;

import android.app.Activity;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkManager {

    private  RequestQueue requestQueue;

    public NetworkManager(Activity activity){
        requestQueue = Volley.newRequestQueue(activity);
    }

    public interface handleCallResult {
        void getSucessResult(ArrayList<Movie> movies);
        void getErrorResult(String error);
    }

    public  void sendStringRequest(String url, final handleCallResult result) {

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        ArrayList<Movie> movies = parseJson(response);
                        result.getSucessResult(movies);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError) {
                    result.getErrorResult("1");
                } else if (error instanceof ServerError) {
                    result.getErrorResult("2");
                } else if (error instanceof NetworkError) {
                    result.getErrorResult("3");
                } else if (error instanceof ParseError) {
                    result.getErrorResult("4");
                } else {
                    result.getErrorResult("5");
                }
            }
        });

        requestQueue.add(request);
    }


    private  ArrayList<Movie> parseJson(String response) {

        ArrayList<Movie> movies = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);

            boolean status = jsonObject.getBoolean("status");

            if (status) {

                JSONArray moviesArray = jsonObject.getJSONArray("movies");

                for (int i = 0; i < moviesArray.length(); i++) {

                    JSONObject movieObj = moviesArray.getJSONObject(i);

                    Movie movie = new Movie();

                    movie.setId(movieObj.optString("id", ""));
                    movie.setName(movieObj.optString("name", ""));
                    movie.setImage(movieObj.optString("image", ""));
                    movie.setRatting(movieObj.getInt("ratting"));

                    movies.add(movie);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }


}
