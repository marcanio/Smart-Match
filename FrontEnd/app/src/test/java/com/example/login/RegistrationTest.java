package com.example.login;

import android.content.Context;
import android.os.Build;

import com.example.login.Logic.RegistrationLogic;
import com.example.login.Network.ServerRequest;
import com.google.common.base.Verify;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class RegistrationTest {
    @Mock
    private ServerRequest server;
    @Mock
    private Registration view;
    @Mock
    private Context context;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void regLogic() throws JSONException {

        String firstName = "First";
        String lastName = "LastName";
        String email = "email";
        String password = "Password";
        String birthday = "birth";
        String phoneNumber = "number";
        String gender = "male";

        String url = "http://coms-309-vb-10.cs.iastate.edu:8080/users/new";
        String post = "POST";

        Map<String, String> params = new HashMap<>();

        params.put("firstName", firstName);
        params.put("lastName", lastName);
        params.put("phoneNumber", phoneNumber);
        params.put("emailaddress", email);
        params.put("gender", gender);
        params.put("userPassword", password);
        params.put("age", birthday);

        JSONObject newUserObj = new JSONObject(params);


        RegistrationLogic presenter = new RegistrationLogic(view, server);
        presenter.registerUser("First", "LastName", "email", "Password", "birth", "number", "male");

        verify(server, times(1)).sendToServer(refEq(url), refEq(newUserObj), refEq(post));

    }


}
