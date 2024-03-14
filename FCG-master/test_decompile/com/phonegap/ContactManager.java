package com.phonegap;
public class ContactManager extends com.phonegap.api.Plugin {
    private static final String LOG_TAG = "Contact Query";
    private static com.phonegap.ContactAccessor contactAccessor;

    public ContactManager()
    {
        return;
    }

    public com.phonegap.api.PluginResult execute(String p9, org.json.JSONArray p10, String p11)
    {
        if (com.phonegap.ContactManager.contactAccessor == null) {
            com.phonegap.ContactManager.contactAccessor = com.phonegap.ContactAccessor.getInstance(this.webView, this.ctx);
        }
        com.phonegap.api.PluginResult$Status v4 = com.phonegap.api.PluginResult$Status.OK;
        try {
            com.phonegap.api.PluginResult v5_8;
            if (!p9.equals("search")) {
                if (!p9.equals("save")) {
                    if (!p9.equals("remove")) {
                        v5_8 = new com.phonegap.api.PluginResult(v4, "");
                    } else {
                        if (!com.phonegap.ContactManager.contactAccessor.remove(p10.getString(0))) {
                            org.json.JSONObject v1_1 = new org.json.JSONObject();
                            v1_1.put("code", 2);
                            v5_8 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.ERROR, v1_1);
                        } else {
                            v5_8 = new com.phonegap.api.PluginResult(v4, "");
                        }
                    }
                } else {
                    if (!com.phonegap.ContactManager.contactAccessor.save(p10.getJSONObject(0))) {
                        org.json.JSONObject v1_3 = new org.json.JSONObject();
                        v1_3.put("code", 0);
                        v5_8 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.ERROR, v1_3);
                    } else {
                        v5_8 = new com.phonegap.api.PluginResult(v4, "");
                    }
                }
            } else {
                v5_8 = new com.phonegap.api.PluginResult(v4, com.phonegap.ContactManager.contactAccessor.search(p10.getJSONArray(0), p10.optJSONObject(1)), "navigator.service.contacts.cast");
            }
        } catch (com.phonegap.api.PluginResult v5_22) {
            org.json.JSONException v0 = v5_22;
            android.util.Log.e("Contact Query", v0.getMessage(), v0);
            v5_8 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v5_8;
    }
}
