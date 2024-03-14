package com.phonegap;
public class App extends com.phonegap.api.Plugin {

    public App()
    {
        return;
    }

    public void addService(String p2, String p3)
    {
        this.ctx.addService(p2, p3);
        return;
    }

    public void cancelLoadUrl()
    {
        ((com.phonegap.DroidGap) this.ctx).cancelLoadUrl();
        return;
    }

    public void clearCache()
    {
        ((com.phonegap.DroidGap) this.ctx).clearCache();
        return;
    }

    public void clearHistory()
    {
        ((com.phonegap.DroidGap) this.ctx).clearHistory();
        return;
    }

    public com.phonegap.api.PluginResult execute(String p7, org.json.JSONArray p8, String p9)
    {
        com.phonegap.api.PluginResult$Status v3 = com.phonegap.api.PluginResult$Status.OK;
        try {
            boolean v4_14;
            if (!p7.equals("clearCache")) {
                if (!p7.equals("loadUrl")) {
                    if (!p7.equals("cancelLoadUrl")) {
                        if (!p7.equals("clearHistory")) {
                            if (!p7.equals("addService")) {
                                if (!p7.equals("overrideBackbutton")) {
                                    if (!p7.equals("isBackbuttonOverridden")) {
                                        if (!p7.equals("exitApp")) {
                                            v4_14 = new com.phonegap.api.PluginResult(v3, "");
                                        } else {
                                            this.exitApp();
                                        }
                                    } else {
                                        v4_14 = new com.phonegap.api.PluginResult(v3, this.isBackbuttonOverridden());
                                    }
                                } else {
                                    this.overrideBackbutton(p8.getBoolean(0));
                                }
                            } else {
                                this.addService(p8.getString(0), p8.getString(1));
                            }
                        } else {
                            this.clearHistory();
                        }
                    } else {
                        this.cancelLoadUrl();
                    }
                } else {
                    this.loadUrl(p8.getString(0), p8.optJSONObject(1));
                }
            } else {
                this.clearCache();
            }
        } catch (boolean v4_24) {
            v4_14 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v4_14;
    }

    public void exitApp()
    {
        ((com.phonegap.DroidGap) this.ctx).finish();
        return;
    }

    public boolean isBackbuttonOverridden()
    {
        return ((com.phonegap.DroidGap) this.ctx).bound;
    }

    public void loadUrl(String p9, org.json.JSONObject p10)
    {
        System.out.println(new StringBuilder().append("App.loadUrl(").append(p9).append(",").append(p10).append(")").toString());
        int v4 = 0;
        if (p10 != null) {
            org.json.JSONArray v2 = p10.names();
            int v0 = 0;
            while (v0 < v2.length()) {
                String v1 = v2.getString(v0);
                if (!v1.equals("wait")) {
                    Integer v3_0 = p10.get(v1);
                    if (v3_0 != null) {
                        if (!v3_0.getClass().equals(String)) {
                            if (!v3_0.getClass().equals(Boolean)) {
                                if (v3_0.getClass().equals(Integer)) {
                                    this.ctx.getIntent().putExtra(v1, ((Integer) v3_0));
                                }
                            } else {
                                this.ctx.getIntent().putExtra(v1, ((Boolean) v3_0));
                            }
                        } else {
                            this.ctx.getIntent().putExtra(v1, ((String) v3_0));
                        }
                    }
                } else {
                    v4 = p10.getInt(v1);
                }
                v0++;
            }
        }
        if (v4 <= 0) {
            ((com.phonegap.DroidGap) this.ctx).loadUrl(p9);
        } else {
            ((com.phonegap.DroidGap) this.ctx).loadUrl(p9, v4);
        }
        return;
    }

    public void overrideBackbutton(boolean p3)
    {
        System.out.println("WARNING: Back Button Default Behaviour will be overridden.  The backbutton event will be fired!");
        ((com.phonegap.DroidGap) this.ctx).bound = p3;
        return;
    }
}
