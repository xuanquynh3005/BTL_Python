package com.phonegap;
public class StandAlone extends com.phonegap.DroidGap {

    public StandAlone()
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
