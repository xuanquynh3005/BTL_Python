package com.phonegap.api;
public abstract class Plugin implements com.phonegap.api.IPlugin {
    public com.phonegap.api.PhonegapActivity ctx;
    public android.webkit.WebView webView;

    public Plugin()
    {
        return;
    }

    public void error(com.phonegap.api.PluginResult p3, String p4)
    {
        this.ctx.sendJavascript(p3.toErrorCallbackString(p4));
        return;
    }

    public abstract com.phonegap.api.PluginResult execute();

    public boolean isSynch(String p2)
    {
        return 0;
    }

    public void onActivityResult(int p1, int p2, android.content.Intent p3)
    {
        return;
    }

    public void onDestroy()
    {
        return;
    }

    public void onNewIntent(android.content.Intent p1)
    {
        return;
    }

    public void onPause(boolean p1)
    {
        return;
    }

    public void onResume(boolean p1)
    {
        return;
    }

    public void sendJavascript(String p2)
    {
        this.ctx.sendJavascript(p2);
        return;
    }

    public void setContext(com.phonegap.api.PhonegapActivity p1)
    {
        this.ctx = p1;
        return;
    }

    public void setView(android.webkit.WebView p1)
    {
        this.webView = p1;
        return;
    }

    public void success(com.phonegap.api.PluginResult p3, String p4)
    {
        this.ctx.sendJavascript(p3.toSuccessCallbackString(p4));
        return;
    }
}
