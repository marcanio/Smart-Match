package com.example.login;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static AppController mInstance;

    /**
     * Initializes the controller
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * Returns the current activity that is being used by the user
     *
     * @return - The Current instance of Activity
     */
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    /**
     * The Queue for the current Volley requests.
     *
     * @return - The response to the volley requests
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * Adds to the request queue using a request and a tag to reference
     *
     * @param req - The Request being completed
     * @param tag - Tag for the request
     * @param <T> - Object Value
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * Adds to the request queue using a request
     *
     * @param req - The Request being completed
     * @param <T> - Object Value
     */
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
