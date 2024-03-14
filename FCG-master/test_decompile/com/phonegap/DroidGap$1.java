package com.phonegap;
 class DroidGap$1 implements java.lang.Runnable {
    final synthetic com.phonegap.DroidGap this$0;
    final synthetic com.phonegap.DroidGap val$me;
    final synthetic String val$url;

    DroidGap$1(com.phonegap.DroidGap p1, com.phonegap.DroidGap p2, String p3)
    {
        this.this$0 = p1;
        this.val$me = p2;
        this.val$url = p3;
        return;
    }

    public void run()
    {
        com.phonegap.DroidGap.access$000(this.val$me);
        this.val$me.callbackServer.init(this.val$url);
        String v8 = this.val$me.getStringProperty("loadingDialog", 0);
        if (v8 != null) {
            String v13 = "";
            String v9 = "Loading Application...";
            if (v8.length() > 0) {
                int v6 = v8.indexOf(44);
                if (v6 <= 0) {
                    v13 = "";
                    v9 = v8;
                } else {
                    v13 = v8.substring(0, v6);
                    v9 = v8.substring((v6 + 1));
                }
            }
            org.json.JSONArray v10_1 = new org.json.JSONArray();
            v10_1.put(v13);
            v10_1.put(v9);
            this.val$me.pluginManager.exec("Notification", "activityStart", 0, v10_1.toString(), 0);
        }
        new Thread(new com.phonegap.DroidGap$1$1(this, com.phonegap.DroidGap.access$100(this.val$me))).start();
        this.val$me.appView.loadUrl(this.val$url);
        return;
    }
}
