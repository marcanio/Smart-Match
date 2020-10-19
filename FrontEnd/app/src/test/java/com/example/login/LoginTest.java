package com.example.login;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import static org.mockito.MockitoAnnotations.initMocks;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;




@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class LoginTest {

    @Mock MainActivity main;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void LoginCredentials(){

        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .resume()
                .get();
        //Enter text
        EditText userName = activity.findViewById(R.id.LoginUsername);
        EditText userPass = activity.findViewById(R.id.LoginPassword);
        userName.setText("Eric");
        userPass.setText("password");

        //Click button
        activity.findViewById(R.id.btnLogin).callOnClick();

        assertThat("Eric", is(userName.getText().toString().trim()));
        assertThat("password",is(userPass.getText().toString().trim()));

    }

    @Test
    public void login(){
        MainActivity activity = Mockito.mock(MainActivity.class);
        Mockito.when(activity.validate("Eric","Password")).thenReturn("Error");


    }

}


