package com.example.helloworld;
public class HelloWorldActivity extends com.phonegap.DroidGap {

    public HelloWorldActivity()
    {
        return;
    }

    public void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        super.loadUrl("file:///android_asset/www/index.html");
        return;
    }
}
