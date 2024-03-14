package com.phonegap;
public class GeoBroker extends com.phonegap.api.Plugin {
    private java.util.HashMap geoListeners;
    private com.phonegap.GeoListener global;

    public GeoBroker()
    {
        this.geoListeners = new java.util.HashMap();
        return;
    }

    public com.phonegap.api.PluginResult execute(String p9, org.json.JSONArray p10, String p11)
    {
        com.phonegap.api.PluginResult$Status v3 = com.phonegap.api.PluginResult$Status.OK;
        try {
            String v4_8;
            if (!p9.equals("getCurrentLocation")) {
                if (!p9.equals("start")) {
                    if (!p9.equals("stop")) {
                        v4_8 = new com.phonegap.api.PluginResult(v3, "");
                    } else {
                        this.stop(p10.getString(0));
                    }
                } else {
                    v4_8 = new com.phonegap.api.PluginResult(v3, this.start(p10.getString(0), p10.getBoolean(1), p10.getInt(2), p10.getInt(3)));
                }
            } else {
                this.getCurrentLocation(p10.getBoolean(0), p10.getInt(1), p10.getInt(2));
            }
        } catch (String v4_13) {
            v4_8 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v4_8;
    }

    public void getCurrentLocation(boolean p3, int p4, int p5)
    {
        if (this.global != null) {
            this.global.start(p5);
        } else {
            this.global = new com.phonegap.GeoListener(this, "global", p5);
        }
        return;
    }

    public boolean isSynch(String p2)
    {
        return 1;
    }

    public void onDestroy()
    {
        java.util.Iterator v1 = this.geoListeners.entrySet().iterator();
        while (v1.hasNext()) {
            ((com.phonegap.GeoListener) ((java.util.Map$Entry) v1.next()).getValue()).destroy();
        }
        this.geoListeners.clear();
        if (this.global != null) {
            this.global.destroy();
        }
        this.global = 0;
        return;
    }

    public String start(String p3, boolean p4, int p5, int p6)
    {
        com.phonegap.GeoListener v0_1 = ((com.phonegap.GeoListener) this.geoListeners.get(p3));
        if (v0_1 == null) {
            v0_1 = new com.phonegap.GeoListener(this, p3, p6);
            this.geoListeners.put(p3, v0_1);
        }
        v0_1.start(p6);
        return p3;
    }

    public void stop(String p3)
    {
        com.phonegap.GeoListener v0_1 = ((com.phonegap.GeoListener) this.geoListeners.remove(p3));
        if (v0_1 != null) {
            v0_1.stop();
        }
        return;
    }
}
