package com.phonegap.api;
 class PluginManager$1 implements java.lang.Runnable {
    final synthetic com.phonegap.api.PluginManager this$0;
    final synthetic String val$action;
    final synthetic org.json.JSONArray val$args;
    final synthetic String val$callbackId;
    final synthetic com.phonegap.api.PhonegapActivity val$ctx;
    final synthetic com.phonegap.api.Plugin val$plugin;

    PluginManager$1(com.phonegap.api.PluginManager p1, com.phonegap.api.Plugin p2, String p3, org.json.JSONArray p4, String p5, com.phonegap.api.PhonegapActivity p6)
    {
        this.this$0 = p1;
        this.val$plugin = p2;
        this.val$action = p3;
        this.val$args = p4;
        this.val$callbackId = p5;
        this.val$ctx = p6;
        return;
    }

    public void run()
    {
        try {
            com.phonegap.api.PluginResult v0_2 = this.val$plugin.execute(this.val$action, this.val$args, this.val$callbackId);
            int v2 = v0_2.getStatus();
        } catch (com.phonegap.api.PhonegapActivity v3_9) {
            this.val$ctx.sendJavascript(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.ERROR).toErrorCallbackString(this.val$callbackId));
            return;
        }
        if ((v2 != com.phonegap.api.PluginResult$Status.NO_RESULT.ordinal()) || (!v0_2.getKeepCallback())) {
            if ((v2 != com.phonegap.api.PluginResult$Status.OK.ordinal()) && (v2 != com.phonegap.api.PluginResult$Status.NO_RESULT.ordinal())) {
                this.val$ctx.sendJavascript(v0_2.toErrorCallbackString(this.val$callbackId));
                return;
            } else {
                this.val$ctx.sendJavascript(v0_2.toSuccessCallbackString(this.val$callbackId));
                return;
            }
        } else {
            return;
        }
    }
}
