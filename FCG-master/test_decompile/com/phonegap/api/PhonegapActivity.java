package com.phonegap.api;
public abstract class PhonegapActivity extends android.app.Activity {

    public PhonegapActivity()
    {
        return;
    }

    public abstract void addService();

    public abstract void sendJavascript();

    public abstract void startActivityForResult();
}
