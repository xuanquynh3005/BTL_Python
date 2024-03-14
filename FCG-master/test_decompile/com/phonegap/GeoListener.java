package com.phonegap;
public class GeoListener {
    public static int PERMISSION_DENIED;
    public static int POSITION_UNAVAILABLE;
    public static int TIMEOUT;
    private com.phonegap.GeoBroker broker;
    String failCallback;
    String id;
    int interval;
    com.phonegap.GpsListener mGps;
    android.location.LocationManager mLocMan;
    com.phonegap.NetworkListener mNetwork;
    String successCallback;

    static GeoListener()
    {
        com.phonegap.GeoListener.PERMISSION_DENIED = 1;
        com.phonegap.GeoListener.POSITION_UNAVAILABLE = 2;
        com.phonegap.GeoListener.TIMEOUT = 3;
        return;
    }

    GeoListener(com.phonegap.GeoBroker p3, String p4, int p5)
    {
        this.id = p4;
        this.interval = p5;
        this.broker = p3;
        this.mGps = 0;
        this.mNetwork = 0;
        this.mLocMan = ((android.location.LocationManager) p3.ctx.getSystemService("location"));
        if (this.mLocMan.getProvider("gps") != null) {
            this.mGps = new com.phonegap.GpsListener(p3.ctx, p5, this);
        }
        if (this.mLocMan.getProvider("network") != null) {
            this.mNetwork = new com.phonegap.NetworkListener(p3.ctx, p5, this);
        }
        return;
    }

    public void destroy()
    {
        this.stop();
        return;
    }

    void fail(int p4, String p5)
    {
        this.broker.sendJavascript(new StringBuilder().append("navigator._geo.fail(\'").append(this.id).append("\', \'").append(p4).append("\', \'").append(p5).append("\');").toString());
        this.stop();
        return;
    }

    void start(int p3)
    {
        if (this.mGps != null) {
            this.mGps.start(p3);
        }
        if (this.mNetwork != null) {
            this.mNetwork.start(p3);
        }
        if ((this.mNetwork == null) && (this.mGps == null)) {
            this.fail(com.phonegap.GeoListener.POSITION_UNAVAILABLE, "No location providers available.");
        }
        return;
    }

    void stop()
    {
        if (this.mGps != null) {
            this.mGps.stop();
        }
        if (this.mNetwork != null) {
            this.mNetwork.stop();
        }
        return;
    }

    void success(android.location.Location p5)
    {
        String v0 = new StringBuilder().append(p5.getLatitude()).append(",").append(p5.getLongitude()).append(", ").append(p5.getAltitude()).append(",").append(p5.getAccuracy()).append(",").append(p5.getBearing()).append(",").append(p5.getSpeed()).append(",").append(p5.getTime()).toString();
        if (this.id == "global") {
            this.stop();
        }
        this.broker.sendJavascript(new StringBuilder().append("navigator._geo.success(\'").append(this.id).append("\',").append(v0).append(");").toString());
        return;
    }
}
