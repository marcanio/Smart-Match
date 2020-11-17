package com.example.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MatchPage extends AppCompatActivity {
    private String url = "http://coms-309-vb-10.cs.iastate.edu:8080/user/match/";

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<MatchUser> userList;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_page);

        mList = findViewById(R.id.main_list);

        userList = new ArrayList<>();
        adapter = new MatchAdapter(getApplicationContext(), userList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(adapter);


        //Add email at the end of the URL
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        url += email;
        getData();


    }
    private void getData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Worked: ", response.toString());
                for(int i =0; i < response.length(); i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        MatchUser user = new MatchUser();
                        user.setFirstName(jsonObject.getString("firstName"));
                        user.setEmailaddress(jsonObject.getString("emailaddress"));
                        user.setGender(jsonObject.getString("gender"));
                        user.setId(jsonObject.getInt("id"));
                        user.setUserscore(jsonObject.getInt("userscore"));
                        userList.add(user);
                    }catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}