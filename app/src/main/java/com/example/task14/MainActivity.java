package com.example.task14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CardAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";
    public static final String SHARED_PREFS = "sharedPrefs";

    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;
    private ArrayList<CardItem> mCardList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCardList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest json_request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject_hit = jsonArray.getJSONObject(i);

                                String creatorName = jsonObject_hit.getString("user");
                                String imageURL = jsonObject_hit.getString("webformatURL");
                                int likes = jsonObject_hit.getInt("likes");

                                mCardList.add(new CardItem(imageURL, creatorName, likes));
                            }
                            mCardAdapter = new CardAdapter(MainActivity.this, mCardList);
                            mRecyclerView.setAdapter(mCardAdapter);
                            mCardAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        mRequestQueue.add(json_request);
    }

    @Override
    public void onItemClick(int position) {
        DetailItemDialog detailItemDialog = new DetailItemDialog();
        CardItem clickItem = mCardList.get(position);
        Bundle args = new Bundle();
        args.putString(EXTRA_URL, clickItem.getImageURL());
        args.putString(EXTRA_CREATOR, clickItem.getCreator());
        args.putInt(EXTRA_LIKES, clickItem.getLikes());
        detailItemDialog.setArguments(args);
        detailItemDialog.show(getSupportFragmentManager(),"Detail Item Dialog");
    }
}