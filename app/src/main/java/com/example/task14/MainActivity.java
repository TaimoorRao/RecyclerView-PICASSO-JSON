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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity /*implements CardAdapter.OnItemClickListener*/ {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";
    private static final String URL = "https://api.github.com/users";

    private RecyclerView mRecyclerView;
    //    private CardAdapter mCardAdapter;
//    private TestJSON[] mCardList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mCardList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {

//        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
//        JsonObjectRequest json_request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("hits");
//
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject_hit = jsonArray.getJSONObject(i);
//
//                                String creatorName = jsonObject_hit.getString("user");
//                                String imageURL = jsonObject_hit.getString("webformatURL");
//                                int likes = jsonObject_hit.getInt("likes");
//
//                                mCardList.add(new CardItem(imageURL, creatorName, likes));
//                            }
//                            mCardAdapter = new CardAdapter(MainActivity.this, mCardList);
//                            mRecyclerView.setAdapter(mCardAdapter);
//                            mCardAdapter.setOnItemClickListener(MainActivity.this);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//        mRequestQueue.add(json_request);
//    }
        /**
         * Task : Serialization
         */
        StringRequest json_request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                TestJSON testJSON = new TestJSON("mojombo", "https://avatars.githubusercontent.com/u/1?v=4", "User");
                String test = gson.toJson(testJSON);
                TestJSON testJSON1 = new TestJSON("defunkt", "https://avatars.githubusercontent.com/u/2?v=4", "User");
                String test1 = gson.toJson(testJSON1);
                TestJSON testJSON2 = new TestJSON("pjhyett", "https://avatars.githubusercontent.com/u/3?v=4", "User");
                String test2 = gson.toJson(testJSON2);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        mRequestQueue.add(json_request);
        /**
         * Task : Deserialization
         */
//        StringRequest json_request = new StringRequest(URL, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        GsonBuilder gsonBuilder = new GsonBuilder();
//                        Gson gson = gsonBuilder.create();
//                        TestJSON[] test = gson.fromJson(response, TestJSON[].class);
//                        mRecyclerView.setAdapter(new CardAdapter(MainActivity.this, test));
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//        mRequestQueue.add(json_request);
    }

//    @Override
//    public void onItemClick(int position) {
//        DetailItemDialog detailItemDialog = new DetailItemDialog();
//        TestJSON clickItem = mCardList[position];
//        Bundle args = new Bundle();
//        args.putString(EXTRA_URL, clickItem.getAvatarUrl());
//        args.putString(EXTRA_CREATOR, clickItem.getLogin());
//        args.putString(EXTRA_LIKES, clickItem.getType());
//        detailItemDialog.setArguments(args);
//        detailItemDialog.show(getSupportFragmentManager(),"Detail Item Dialog");
//    }
}