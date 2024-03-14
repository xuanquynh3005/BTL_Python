package com.phonegap;
public class NetworkListener implements android.location.LocationListener {
    private android.location.Location cLoc;
    private boolean hasData;
    private com.phonegap.api.PhonegapActivity mCtx;
    private android.location.LocationManager mLocMan;
    private com.phonegap.GeoListener owner;
    private boolean running;

    public NetworkListener(com.phonegap.api.PhonegapActivity p4, int p5, com.phonegap.GeoListener p6)
    {
        this.hasData = 0;
        this.running = 0;
        this.owner = p6;
        this.mCtx = p4;
        this.mLocMan = ((android.location.LocationManager) this.mCtx.getSystemService("location"));
        this.running = 0;
        this.start(p5);
        return;
    }

    public android.location.Location getLocation()
    {
        this.cLoc = this.mLocMan.getLastKnownLocation("network");
        if (this.cLoc != null) {
            this.hasData = 1;
        }
        return this.cLoc;
    }

    public void onLocationChanged(android.location.Location p3)
    {
        System.out.println("NetworkListener: The location has been updated!");
        this.hasData = 1;
        this.cLoc = p3;
        if (!this.owner.mGps.hasLocation()) {
            this.owner.success(p3);
        }
        return;
    }

    public void onProviderDisabled(String p4)
    {
        System.out.println(new StringBuilder().append("NetworkListener: The provider ").append(p4).append(" is disabled").toString());
        return;
    }

    public void onProviderEnabled(String p4)
    {
        System.out.println(new StringBuilder().append("NetworkListener: The provider ").append(p4).append(" is enabled").toString());
        return;
    }

    public void onStatusChanged(String p4, int p5, android.os.Bundle p6)
    {
        System.out.println(new StringBuilder().append("NetworkListener: The status of the provider ").append(p4).append(" has changed").toString());
        if (p5 != 0) {
            if (p5 != 1) {
                System.out.println(new StringBuilder().append("NetworkListener: ").append(p4).append(" is Available").toString());
            } else {
                System.out.println(new StringBuilder().append("NetworkListener: ").append(p4).append(" is TEMPORARILY_UNAVAILABLE").toString());
            }
        } else {
            System.out.println(new StringBuilder().append("NetworkListener: ").append(p4).append(" is OUT OF SERVICE").toString());
        }
        return;
    }

    public void start(int p7)
    {
        if (!this.running) {
            this.running = 1;
            this.mLocMan.requestLocationUpdates("network", ((long) p7), 0, this);
            this.getLocation();
            if ((this.hasData) && (!this.owner.mGps.hasLocation())) {
                this.owner.success(this.cLoc);
            }
        }
        return;
    }

    public void stop()
    {
        if (this.running) {
            this.mLocMan.removeUpdates(this);
        }
        this.running = 0;
        return;
    }
}
