package com.phonegap;
public class CryptoHandler extends com.phonegap.api.Plugin {

    public CryptoHandler()
    {
        return;
    }

    public void decrypt(String p5, String p6)
    {
        try {
            com.phonegap.SimpleCrypto.decrypt(p5, p6);
            this.sendJavascript(new StringBuilder().append("Crypto.gotPlainString(\'").append(p6).append("\')").toString());
        } catch (Exception v2_2) {
            v2_2.printStackTrace();
        }
        return;
    }

    public void encrypt(String p5, String p6)
    {
        try {
            com.phonegap.SimpleCrypto.encrypt(p5, p6);
            this.sendJavascript(new StringBuilder().append("Crypto.gotCryptedString(\'").append(p6).append("\')").toString());
        } catch (Exception v2_2) {
            v2_2.printStackTrace();
        }
        return;
    }

    public com.phonegap.api.PluginResult execute(String p6, org.json.JSONArray p7, String p8)
    {
        try {
            if (!p6.equals("encrypt")) {
                if (!p6.equals("decrypt")) {
                    String v3_6 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, "");
                } else {
                    this.decrypt(p7.getString(0), p7.getString(1));
                }
            } else {
                this.encrypt(p7.getString(0), p7.getString(1));
            }
        } catch (String v3_7) {
            v3_6 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v3_6;
    }
}
