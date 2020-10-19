package com.example.login;

import android.content.Context;

public interface IView {
    public void showText(String s);
    public void toastText (String s);
    public Context getContext();
}
