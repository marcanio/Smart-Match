package com.example.login.Network;

import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.AppController;
import com.example.login.Logic.IVolleyListener;
import com.example.login.Registration;
import com.example.login.SecondActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerRequest implements IServerRequest {
    private String tag_json_obj = "json_obj_req";
    private IVolleyListener I;

    @Override
    public void sendToServer(String url, JSONObject newUserObj, String methodType) {

        int method = Request.Method.GET;
        if (methodType.equals("POST")) {
            method = Request.Method.POST;
        }

        JsonObjectRequest registerUserRequest = new JsonObjectRequest(method, url, newUserObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.print(response.toString());
                        if (response != null) {
                            I.onSuccess(response.toString());
                        } else {
                            I.onError("Null response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(registerUserRequest, tag_json_obj);
    }

    public void addVolleyListener(IVolleyListener logic) {
        I = logic;
    }
}
