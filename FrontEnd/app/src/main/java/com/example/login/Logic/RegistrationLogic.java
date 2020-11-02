package com.example.login.Logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.login.AppController;
import com.example.login.IView;
import com.example.login.MainActivity;
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


    /**
     * Handles the logic to the register page. This Fills the JSONobject with relevant information and also changes the activity when need be.
     * @param r - This is the current page (Register page) used for changing the activity
     * @param serverRequest - Needed to submit a server request with Volley
     */
    public RegistrationLogic(IView r, IServerRequest serverRequest) {
        this.r = r;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * Sends the volley request to "ServerRequest()" with all of the relevant information
     * @param firstName - Users First Name
     * @param lastName - Users Last Name
     * @param email - Users Email Address
     * @param password - Users Password
     * @param birthday - Users Birthday
     * @param phoneNumber - Users Phone Number
     * @param gender - Users Gender
     * @throws JSONException = Detects a problem with the JSON before it is sent to the server
     */
    public void registerUser(String firstName, String lastName, String email, String password, String birthday, String phoneNumber, String gender) throws JSONException {

        final String url = "http://coms-309-vb-10.cs.iastate.edu:8080/users/new";
        Map<String, String> params = new HashMap<>();

        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("phoneNumber", phoneNumber);
        params.put("emailaddress", email);
        params.put("gender", gender);
        params.put("userPassword", password);
        params.put("age", birthday);

        JSONObject newUserObj = new JSONObject(params);

        serverRequest.sendToServer(url, newUserObj, "POST");
    }

    /**
     * When the Register page is complete it will call this method to change the activity
     * @param email - Users Email address used to send to the terminal to show the registration was successful
     */
    @Override
    public void onSuccess(String email) {
        if (email.length() > 0) {

            try {
                Context temp = r.getContext();
                Intent mIntent = new Intent(temp, SecondActivity.class);
                temp.startActivity(mIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * When there is an error on the register page this method is called to notify the user there is a problem with there inputs
     * @param error - The error message that is being sent to the user
     */
    @Override
    public void onError(String error) {
        //TODO: Reformat screen to better display error
        //Pop up box
    }


}
