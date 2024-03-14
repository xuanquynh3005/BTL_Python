package com.phonegap;
public class NetworkManager extends com.phonegap.api.Plugin {
    public static final String CDMA = "cdma";
    public static final String EDGE = "edge";
    public static final String GPRS = "gprs";
    public static final String GSM = "gsm";
    private static final String LOG_TAG = "NetworkManager";
    public static final String LTE = "lte";
    public static final String MOBILE = "mobile";
    public static int NOT_REACHABLE = 0;
    public static int REACHABLE_VIA_CARRIER_DATA_NETWORK = 0;
    public static int REACHABLE_VIA_WIFI_NETWORK = 0;
    public static final String TYPE_2G = "2g";
    public static final String TYPE_3G = "3g";
    public static final String TYPE_4G = "4g";
    public static final String TYPE_ETHERNET = "ethernet";
    public static final String TYPE_NONE = "none";
    public static final String TYPE_UNKNOWN = "unknown";
    public static final String TYPE_WIFI = "wifi";
    public static final String UMB = "umb";
    public static final String UMTS = "umts";
    public static final String WIFI = "wifi";
    public static final String WIMAX = "wimax";
    private String connectionCallbackId;
    android.content.BroadcastReceiver receiver;
    android.net.ConnectivityManager sockMan;

    static NetworkManager()
    {
        com.phonegap.NetworkManager.NOT_REACHABLE = 0;
        com.phonegap.NetworkManager.REACHABLE_VIA_CARRIER_DATA_NETWORK = 1;
        com.phonegap.NetworkManager.REACHABLE_VIA_WIFI_NETWORK = 2;
        return;
    }

    public NetworkManager()
    {
        this.receiver = 0;
        return;
    }

    static synthetic void access$000(com.phonegap.NetworkManager p0, android.net.NetworkInfo p1)
    {
        p0.updateConnectionInfo(p1);
        return;
    }

    private String getConnectionInfo(android.net.NetworkInfo p3)
    {
        String v0 = "none";
        if (p3 != null) {
            if (p3.isConnected()) {
                v0 = this.getType(p3);
            } else {
                v0 = "none";
            }
        }
        return v0;
    }

    private String getType(android.net.NetworkInfo p4)
    {
        String v1_0;
        if (p4 == null) {
            v1_0 = "none";
        } else {
            String v0_1 = p4.getTypeName();
            if (!v0_1.toLowerCase().equals("wifi")) {
                if (v0_1.toLowerCase().equals("mobile")) {
                    String v0_0 = p4.getSubtypeName();
                    if ((!v0_0.toLowerCase().equals("gsm")) && ((!v0_0.toLowerCase().equals("gprs")) && (!v0_0.toLowerCase().equals("edge")))) {
                        if ((!v0_0.toLowerCase().equals("cdma")) && (!v0_0.toLowerCase().equals("umts"))) {
                            if ((v0_0.toLowerCase().equals("lte")) || (v0_0.toLowerCase().equals("umb"))) {
                                v1_0 = "4g";
                                return v1_0;
                            }
                        } else {
                            v1_0 = "3g";
                            return v1_0;
                        }
                    } else {
                        v1_0 = "2g";
                        return v1_0;
                    }
                }
                v1_0 = "unknown";
            } else {
                v1_0 = "wifi";
            }
        }
        return v1_0;
    }

    private void sendUpdate(String p3)
    {
        com.phonegap.api.PluginResult v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, p3);
        v0_1.setKeepCallback(1);
        this.success(v0_1, this.connectionCallbackId);
        return;
    }

    private void updateConnectionInfo(android.net.NetworkInfo p2)
    {
        this.sendUpdate(this.getConnectionInfo(p2));
        return;
    }

    public com.phonegap.api.PluginResult execute(String p10, org.json.JSONArray p11, String p12)
    {
        com.phonegap.api.PluginResult$Status v6 = com.phonegap.api.PluginResult$Status.OK;
        try {
            com.phonegap.api.PluginResult v7_5;
            if (!p10.equals("isAvailable")) {
                if (!p10.equals("isWifiActive")) {
                    if (!p10.equals("isReachable")) {
                        if (!p10.equals("getConnectionInfo")) {
                            v7_5 = new com.phonegap.api.PluginResult(v6, "");
                        } else {
                            this.connectionCallbackId = p12;
                            com.phonegap.api.PluginResult v4_1 = new com.phonegap.api.PluginResult(v6, this.getConnectionInfo(this.sockMan.getActiveNetworkInfo()));
                            v4_1.setKeepCallback(1);
                            v7_5 = v4_1;
                        }
                    } else {
                        v7_5 = new com.phonegap.api.PluginResult(v6, this.isReachable(p11.getString(0), p11.getBoolean(1)));
                    }
                } else {
                    v7_5 = new com.phonegap.api.PluginResult(v6, this.isWifiActive());
                }
            } else {
                v7_5 = new com.phonegap.api.PluginResult(v6, this.isAvailable());
            }
        } catch (com.phonegap.api.PluginResult v7_16) {
            v7_5 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v7_5;
    }

    public boolean isAvailable()
    {
        android.net.NetworkInfo v1 = this.sockMan.getActiveNetworkInfo();
        boolean v0 = 0;
        if (v1 != null) {
            v0 = v1.isConnected();
        }
        return v0;
    }

    public int isReachable(String p7, boolean p8)
    {
        int v3 = com.phonegap.NetworkManager.NOT_REACHABLE;
        if ((p7.indexOf("http://") == -1) && (p7.indexOf("https://") == -1)) {
            p7 = new StringBuilder().append("http://").append(p7).toString();
        }
        try {
            if (this.isAvailable()) {
                new org.apache.http.impl.client.DefaultHttpClient().execute(new org.apache.http.client.methods.HttpGet(p7));
                if (!this.isWifiActive()) {
                    v3 = com.phonegap.NetworkManager.REACHABLE_VIA_CARRIER_DATA_NETWORK;
                } else {
                    v3 = com.phonegap.NetworkManager.REACHABLE_VIA_WIFI_NETWORK;
                }
            }
        } catch (Exception v4_7) {
            v3 = com.phonegap.NetworkManager.NOT_REACHABLE;
        }
        return v3;
    }

    public boolean isSynch(String p2)
    {
        return 0;
    }

    public boolean isWifiActive()
    {
        int v2_1;
        android.net.NetworkInfo v0 = this.sockMan.getActiveNetworkInfo();
        if (v0 == null) {
            v2_1 = 0;
        } else {
            v2_1 = v0.getTypeName().equals("WIFI");
        }
        return v2_1;
    }

    public void onDestroy()
    {
        if (this.receiver != null) {
            try {
                this.ctx.unregisterReceiver(this.receiver);
            } catch (String v1_2) {
                Exception v0 = v1_2;
                android.util.Log.e("NetworkManager", new StringBuilder().append("Error unregistering network receiver: ").append(v0.getMessage()).toString(), v0);
            }
        }
        return;
    }

    public void setContext(com.phonegap.api.PhonegapActivity p3)
    {
        super.setContext(p3);
        this.sockMan = ((android.net.ConnectivityManager) p3.getSystemService("connectivity"));
        this.connectionCallbackId = 0;
        android.content.IntentFilter v0_1 = new android.content.IntentFilter();
        v0_1.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        if (this.receiver == null) {
            this.receiver = new com.phonegap.NetworkManager$1(this);
            p3.registerReceiver(this.receiver, v0_1);
        }
        return;
    }
}
