package com.phonegap;
public class Device extends com.phonegap.api.Plugin {
    public static String phonegapVersion;
    public static String platform;
    public static String uuid;

    static Device()
    {
        com.phonegap.Device.phonegapVersion = "0.9.5";
        com.phonegap.Device.platform = "Android";
        return;
    }

    public Device()
    {
        return;
    }

    public com.phonegap.api.PluginResult execute(String p7, org.json.JSONArray p8, String p9)
    {
        com.phonegap.api.PluginResult$Status v3 = com.phonegap.api.PluginResult$Status.OK;
        try {
            com.phonegap.api.PluginResult v4_7;
            if (!p7.equals("getDeviceInfo")) {
                v4_7 = new com.phonegap.api.PluginResult(v3, "");
            } else {
                org.json.JSONObject v1_1 = new org.json.JSONObject();
                v1_1.put("uuid", com.phonegap.Device.uuid);
                v1_1.put("version", this.getOSVersion());
                v1_1.put("platform", com.phonegap.Device.platform);
                v1_1.put("name", this.getProductName());
                v1_1.put("phonegap", com.phonegap.Device.phonegapVersion);
                v4_7 = new com.phonegap.api.PluginResult(v3, v1_1);
            }
        } catch (com.phonegap.api.PluginResult v4_8) {
            v4_7 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v4_7;
    }

    public String getModel()
    {
        return android.os.Build.MODEL;
    }

    public String getOSVersion()
    {
        return android.os.Build$VERSION.RELEASE;
    }

    public String getPhonegapVersion()
    {
        return com.phonegap.Device.phonegapVersion;
    }

    public String getPlatform()
    {
        return com.phonegap.Device.platform;
    }

    public String getProductName()
    {
        return android.os.Build.PRODUCT;
    }

    public String getSDKVersion()
    {
        return android.os.Build$VERSION.SDK;
    }

    public String getTimeZoneID()
    {
        return java.util.TimeZone.getDefault().getID();
    }

    public String getUuid()
    {
        return android.provider.Settings$Secure.getString(this.ctx.getContentResolver(), "android_id");
    }

    public boolean isSynch(String p2)
    {
        int v0_2;
        if (!p2.equals("getDeviceInfo")) {
            v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        return v0_2;
    }

    public void setContext(com.phonegap.api.PhonegapActivity p2)
    {
        super.setContext(p2);
        com.phonegap.Device.uuid = this.getUuid();
        return;
    }
}
