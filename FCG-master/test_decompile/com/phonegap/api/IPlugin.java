package com.phonegap.api;
public interface IPlugin {

    public abstract com.phonegap.api.PluginResult execute();

    public abstract boolean isSynch();

    public abstract void onActivityResult();

    public abstract void onDestroy();

    public abstract void onPause();

    public abstract void onResume();

    public abstract void setContext();

    public abstract void setView();
}
