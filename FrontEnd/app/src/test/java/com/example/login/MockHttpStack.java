package com.example.login;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.BaseHttpStack;
import com.android.volley.toolbox.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MockHttpStack extends BaseHttpStack {

    private HttpResponse mResponseToReturn;

    private IOException mExceptionToThrow;

    private String mLastUrl;

    private Map<String, String> mLastHeaders;

    private byte[] mLastPostBody;

    public String getLastUrl() {
        return mLastUrl;
    }

    public Map<String, String> getLastHeaders() {
        return mLastHeaders;
    }

    public byte[] getLastPostBody() {
        return mLastPostBody;
    }

    public void setResponseToReturn(HttpResponse response) {
        mResponseToReturn = response;
    }

    public void setExceptionToThrow(IOException exception) {
        mExceptionToThrow = exception;
    }

    @Override
    public HttpResponse executeRequest(Request<?> request, Map<String, String> additionalHeaders)
            throws IOException, AuthFailureError {
        if (mExceptionToThrow != null) {
            throw mExceptionToThrow;
        }
        mLastUrl = request.getUrl();
        mLastHeaders = new HashMap<>();
        if (request.getHeaders() != null) {
            mLastHeaders.putAll(request.getHeaders());
        }
        if (additionalHeaders != null) {
            mLastHeaders.putAll(additionalHeaders);
        }
        try {
            mLastPostBody = request.getBody();
        } catch (AuthFailureError e) {
            mLastPostBody = null;
        }
        return mResponseToReturn;
    }
}