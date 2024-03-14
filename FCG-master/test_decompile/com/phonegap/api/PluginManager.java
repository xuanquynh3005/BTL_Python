package com.phonegap.api;
public final class PluginManager {
    private final android.webkit.WebView app;
    private final com.phonegap.api.PhonegapActivity ctx;
    private java.util.HashMap plugins;
    private java.util.HashMap services;

    public PluginManager(android.webkit.WebView p2, com.phonegap.api.PhonegapActivity p3)
    {
        this.plugins = new java.util.HashMap();
        this.services = new java.util.HashMap();
        this.ctx = p3;
        this.app = p2;
        return;
    }

    private com.phonegap.api.Plugin addPlugin(String p6, Class p7)
    {
        int v2_2;
        if (!this.plugins.containsKey(p6)) {
            try {
                com.phonegap.api.Plugin v1_1 = ((com.phonegap.api.Plugin) p7.newInstance());
                this.plugins.put(p6, v1_1);
                v1_1.setContext(this.ctx);
                v1_1.setView(this.app);
                v2_2 = v1_1;
            } catch (int v2_3) {
                v2_3.printStackTrace();
                System.out.println(new StringBuilder().append("Error adding plugin ").append(p6).append(".").toString());
                v2_2 = 0;
            }
        } else {
            v2_2 = this.getPlugin(p6);
        }
        return v2_2;
    }

    private Class getClassByName(String p2)
    {
        return Class.forName(p2);
    }

    private com.phonegap.api.Plugin getPlugin(String p3)
    {
        return ((com.phonegap.api.Plugin) this.plugins.get(p3));
    }

    private boolean isPhoneGapPlugin(Class p3)
    {
        int v0_0;
        if (p3 == null) {
            v0_0 = 0;
        } else {
            if ((!com.phonegap.api.Plugin.isAssignableFrom(p3)) && (!com.phonegap.api.IPlugin.isAssignableFrom(p3))) {
                v0_0 = 0;
            } else {
                v0_0 = 1;
            }
        }
        return v0_0;
    }

    public com.phonegap.api.Plugin addPlugin(String p5)
    {
        try {
            int v1_1 = this.addPlugin(p5, this.getClassByName(p5));
        } catch (int v1_2) {
            v1_2.printStackTrace();
            System.out.println(new StringBuilder().append("Error adding plugin ").append(p5).append(".").toString());
            v1_1 = 0;
        }
        return v1_1;
    }

    public void addService(String p2, String p3)
    {
        this.services.put(p2, p3);
        return;
    }

    public String exec(String p18, String p19, String p20, String p21, boolean p22)
    {
        com.phonegap.api.PluginResult v13_0 = 0;
        int v15 = p22;
        try {
            String v4_1;
            org.json.JSONArray v8 = new org.json.JSONArray;
            v8(p21);
            String v12_1 = ((String) this.services.get(p18));
            Class v11 = 0;
        } catch (String v4_14) {
            v13_0 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.CLASS_NOT_FOUND_EXCEPTION);
            if (v15 != 0) {
                if (v13_0 == null) {
                    v13_0 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.CLASS_NOT_FOUND_EXCEPTION);
                }
                this.ctx.sendJavascript(v13_0.toErrorCallbackString(p20));
            }
            if (v13_0 == null) {
                v4_1 = "{ status: 0, message: \'all good\' }";
                return v4_1;
            } else {
                v4_1 = v13_0.getJSONString();
                return v4_1;
            }
        } catch (String v4_10) {
            System.out.println(new StringBuilder().append("ERROR: ").append(v4_10.toString()).toString());
            v13_0 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        if (v12_1 != null) {
            v11 = this.getClassByName(v12_1);
        }
        if (!this.isPhoneGapPlugin(v11)) {
        } else {
            com.phonegap.api.Plugin v6_0 = this.addPlugin(v12_1, v11);
            if ((!p22) || (v6_0.isSynch(p19))) {
                v15 = 0;
            } else {
                v15 = 1;
            }
            if (v15 == 0) {
                v13_0 = v6_0.execute(p19, v8, p20);
                if ((v13_0.getStatus() != com.phonegap.api.PluginResult$Status.NO_RESULT.ordinal()) || (!v13_0.getKeepCallback())) {
                } else {
                    v4_1 = "";
                    return v4_1;
                }
            } else {
                Thread v16 = new Thread;
                v16(new com.phonegap.api.PluginManager$1(this, v6_0, p19, v8, p20, this.ctx));
                v16.start();
                v4_1 = "";
                return v4_1;
            }
        }
    }

    public void onDestroy()
    {
        java.util.Iterator v1 = this.plugins.entrySet().iterator();
        while (v1.hasNext()) {
            ((com.phonegap.api.Plugin) ((java.util.Map$Entry) v1.next()).getValue()).onDestroy();
        }
        return;
    }

    public void onNewIntent(android.content.Intent p6)
    {
        java.util.Iterator v1 = this.plugins.entrySet().iterator();
        while (v1.hasNext()) {
            ((com.phonegap.api.Plugin) ((java.util.Map$Entry) v1.next()).getValue()).onNewIntent(p6);
        }
        return;
    }

    public void onPause(boolean p6)
    {
        java.util.Iterator v1 = this.plugins.entrySet().iterator();
        while (v1.hasNext()) {
            ((com.phonegap.api.Plugin) ((java.util.Map$Entry) v1.next()).getValue()).onPause(p6);
        }
        return;
    }

    public void onResume(boolean p6)
    {
        java.util.Iterator v1 = this.plugins.entrySet().iterator();
        while (v1.hasNext()) {
            ((com.phonegap.api.Plugin) ((java.util.Map$Entry) v1.next()).getValue()).onResume(p6);
        }
        return;
    }
}
