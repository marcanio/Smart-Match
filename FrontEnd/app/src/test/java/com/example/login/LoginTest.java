package com.example.login;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

import org.robolectric.Robolectric;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;




public class LoginTest {

    MainActivity activity;
    private EditText editName;
    private EditText editPassword;
    private Button Login;

    @Before
    public void setUp(){
        activity = Robolectric.setupActivity(MainActivity.class);
        editName = (EditText)activity.findViewById(R.id.LoginUsername);
        editPassword = (EditText)activity.findViewById(R.id.LoginPassword);
        Login = (Button)activity.findViewById(R.id.btnReg);

    }
    @Test
    public void testLogin(){
        editName.setText("up@me.com");
        editPassword.setText("test");

        Login.performClick();

        Intent expected = new Intent(activity,Profile.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertThat(expected.getComponent(),is(actual.getComponent()));

    }

}
