package com.example.login.Logic;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.login.IView;
import com.example.login.Network.IServerRequest;
import com.example.login.Network.ServerRequest;
import com.example.login.Registration;
import com.example.login.SecondActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContextCompat.startActivity;
import static java.security.AccessController.getContext;

public class RegistrationLogic implements IVolleyListener {
    IView r;
    IServerRequest serverRequest;

    public RegistrationLogic(IView r, IServerRequest serverRequest){
        this.r =r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    public void registerUser(String firstName, String lastName, String email, String password, String birthday, String phoneNumber, String gender) throws JSONException {
        final String url = "http://coms-309-vb-10.cs.iastate.edu:8080/users/new";
        Map<String,String> params = new HashMap<>();

        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("phoneNumber", phoneNumber);
        params.put("emailaddress", email);
        params.put("gender", gender);
        params.put("userPassword", password);
        params.put("age",birthday);

        JSONObject newUserObj = new JSONObject(params);

        serverRequest.sendToServer(url,newUserObj,"POST");
    }

    @Override
    public void onSuccess(String email){
        if(email.length() > 0){
            //Intent intent = new Intent(getActivity(), SecondActivity.class);
            //startActivity(intent);
        }
    }

    @Override
    public void onError(String error) {

        //Toast.makeText(r.getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

}
