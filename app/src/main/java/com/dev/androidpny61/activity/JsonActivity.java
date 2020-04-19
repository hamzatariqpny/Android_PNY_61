package com.dev.androidpny61.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.androidpny61.Movie;
import com.dev.androidpny61.MoviesAdapter;
import com.dev.androidpny61.NetworkManager;
import com.dev.androidpny61.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class JsonActivity extends AppCompatActivity {

    String url = "https://tutorialscache.com/movies.json";
    String sendUrl = "https://dummy.restapiexample.com/api/v1/create";

    RecyclerView moviesRV;
    ProgressBar loader;
    LinearLayout errorView;

    NetworkManager networkManager;
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        moviesRV = findViewById(R.id.moviesRV);
        loader = findViewById(R.id.progressBar_cyclic);
        errorView = findViewById(R.id.errorView);

        moviesRV.setLayoutManager(new LinearLayoutManager(this));

        networkManager = new NetworkManager(this);

        networkManager.sendStringRequest(url, new NetworkManager.handleCallResult() {
            @Override
            public void getSucessResult(ArrayList<Movie> movies) {
                loader.setVisibility(View.INVISIBLE);
                moviesAdapter = new MoviesAdapter(JsonActivity.this,movies);
                moviesRV.setAdapter(moviesAdapter);
            }

            @Override
            public void getErrorResult(String error) {

                loader.setVisibility(View.INVISIBLE);
                if(error.equals("3")){
                    errorView.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(JsonActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
