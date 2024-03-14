package com.phonegap.api;
public class PluginResult {
    public static String[] StatusMessages;
    private String cast;
    private boolean keepCallback;
    private final String message;
    private final int status;

    static PluginResult()
    {
        String[] v0_1 = new String[10];
        v0_1[0] = "No result";
        v0_1[1] = "OK";
        v0_1[2] = "Class not found";
        v0_1[3] = "Illegal access";
        v0_1[4] = "Instantiation error";
        v0_1[5] = "Malformed url";
        v0_1[6] = "IO error";
        v0_1[7] = "Invalid action";
        v0_1[8] = "JSON error";
        v0_1[9] = "Error";
        com.phonegap.api.PluginResult.StatusMessages = v0_1;
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p4)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p4.ordinal();
        this.message = new StringBuilder().append("\'").append(com.phonegap.api.PluginResult.StatusMessages[this.status]).append("\'").toString();
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p3, float p4)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p3.ordinal();
        this.message = new StringBuilder().append("").append(p4).toString();
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p3, int p4)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p3.ordinal();
        this.message = new StringBuilder().append("").append(p4).toString();
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p2, String p3)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p2.ordinal();
        this.message = org.json.JSONObject.quote(p3);
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p2, org.json.JSONArray p3)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p2.ordinal();
        this.message = p3.toString();
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p2, org.json.JSONArray p3, String p4)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p2.ordinal();
        this.message = p3.toString();
        this.cast = p4;
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p2, org.json.JSONObject p3)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p2.ordinal();
        this.message = p3.toString();
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p2, org.json.JSONObject p3, String p4)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p2.ordinal();
        this.message = p3.toString();
        this.cast = p4;
        return;
    }

    public PluginResult(com.phonegap.api.PluginResult$Status p3, boolean p4)
    {
        this.keepCallback = 0;
        this.cast = 0;
        this.status = p3.ordinal();
        this.message = new StringBuilder().append("").append(p4).toString();
        return;
    }

    public String getJSONString()
    {
        return new StringBuilder().append("{status:").append(this.status).append(",message:").append(this.message).append(",keepCallback:").append(this.keepCallback).append("}").toString();
    }

    public boolean getKeepCallback()
    {
        return this.keepCallback;
    }

    public String getMessage()
    {
        return this.message;
    }

    public int getStatus()
    {
        return this.status;
    }

    public void setKeepCallback(boolean p1)
    {
        this.keepCallback = p1;
        return;
    }

    public String toErrorCallbackString(String p3)
    {
        return new StringBuilder().append("PhoneGap.callbackError(\'").append(p3).append("\', ").append(this.getJSONString()).append(");").toString();
    }

    public String toSuccessCallbackString(String p4)
    {
        StringBuffer v0_1 = new StringBuffer("");
        if (this.cast == null) {
            v0_1.append(new StringBuilder().append("PhoneGap.callbackSuccess(\'").append(p4).append("\',").append(this.getJSONString()).append(");").toString());
        } else {
            v0_1.append(new StringBuilder().append("var temp = ").append(this.cast).append("(").append(this.getJSONString()).append(");\n").toString());
            v0_1.append(new StringBuilder().append("PhoneGap.callbackSuccess(\'").append(p4).append("\',temp);").toString());
        }
        return v0_1.toString();
    }
}
