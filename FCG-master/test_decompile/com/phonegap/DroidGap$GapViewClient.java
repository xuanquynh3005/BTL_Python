package com.phonegap;
public class DroidGap$GapViewClient extends android.webkit.WebViewClient {
    com.phonegap.DroidGap ctx;
    final synthetic com.phonegap.DroidGap this$0;

    public DroidGap$GapViewClient(com.phonegap.DroidGap p1, com.phonegap.DroidGap p2)
    {
        this.this$0 = p1;
        this.ctx = p2;
        return;
    }

    public void onPageFinished(android.webkit.WebView p7, String p8)
    {
        super.onPageFinished(p7, p8);
        com.phonegap.DroidGap.access$108(this.ctx);
        if (!p8.equals("about:blank")) {
            this.this$0.appView.loadUrl("javascript:try{ PhoneGap.onNativeReady.fire();}catch(e){_nativeReady = true;}");
        }
        this.this$0.appView.setVisibility(0);
        if (this.ctx.hideLoadingDialogOnPageLoad) {
            this.ctx.hideLoadingDialogOnPageLoad = 0;
            this.ctx.pluginManager.exec("Notification", "activityStop", 0, "[]", 0);
        }
        if (this.ctx.clearHistory) {
            this.ctx.clearHistory = 0;
            this.ctx.appView.clearHistory();
        }
        if ((p8.equals("about:blank")) && (this.ctx.callbackServer != null)) {
            this.ctx.callbackServer.destroy();
        }
        return;
    }

    public void onReceivedError(android.webkit.WebView p7, int p8, String p9, String p10)
    {
        System.out.println(new StringBuilder().append("onReceivedError: Error code=").append(p8).append(" Description=").append(p9).append(" URL=").append(p10).toString());
        com.phonegap.DroidGap.access$108(this.ctx);
        this.ctx.pluginManager.exec("Notification", "activityStop", 0, "[]", 0);
        this.ctx.onReceivedError(p8, p9, p10);
        return;
    }

    public void onReceivedSslError(android.webkit.WebView p6, android.webkit.SslErrorHandler p7, android.net.http.SslError p8)
    {
        try {
            if ((this.ctx.getPackageManager().getApplicationInfo(this.ctx.getPackageName(), 128).flags & 2) == 0) {
                super.onReceivedSslError(p6, p7, p8);
            } else {
                p7.proceed();
            }
        } catch (android.content.pm.PackageManager$NameNotFoundException v4_2) {
            super.onReceivedSslError(p6, p7, p8);
        }
        return;
    }

    public boolean shouldOverrideUrlLoading(android.webkit.WebView p13, String p14)
    {
        java.io.PrintStream v8_10;
        if (!p14.startsWith("tel:")) {
            if (!p14.startsWith("geo:")) {
                if (!p14.startsWith("mailto:")) {
                    if (!p14.startsWith("sms:")) {
                        int v2 = p14.lastIndexOf(47);
                        String v4 = p14;
                        if (v2 > 0) {
                            v4 = p14.substring(0, v2);
                        }
                        if ((!this.ctx.loadInWebView) && ((!p14.startsWith("file://")) && (!com.phonegap.DroidGap.access$400(this.ctx).equals(v4)))) {
                            try {
                                android.content.Intent v3_7 = new android.content.Intent("android.intent.action.VIEW");
                                v3_7.setData(android.net.Uri.parse(p14));
                                this.this$0.startActivity(v3_7);
                            } catch (java.io.PrintStream v8_43) {
                                System.out.println(new StringBuilder().append("Error loading url ").append(p14).append(":").append(v8_43.toString()).toString());
                            }
                        } else {
                            this.ctx.appView.loadUrl(p14);
                        }
                        v8_10 = 1;
                    } else {
                        try {
                            String v0;
                            android.content.Intent v3_9 = new android.content.Intent("android.intent.action.VIEW");
                            int v5 = p14.indexOf(63);
                        } catch (java.io.PrintStream v8_11) {
                            System.out.println(new StringBuilder().append("Error sending sms ").append(p14).append(":").append(v8_11.toString()).toString());
                            v8_10 = 1;
                        }
                        if (v5 != -1) {
                            v0 = p14.substring(4, v5);
                            String v6 = android.net.Uri.parse(p14).getQuery();
                            if ((v6 != null) && (v6.startsWith("body="))) {
                                v3_9.putExtra("sms_body", v6.substring(5));
                            }
                        } else {
                            v0 = p14.substring(4);
                        }
                        v3_9.setData(android.net.Uri.parse(new StringBuilder().append("sms:").append(v0).toString()));
                        v3_9.putExtra("address", v0);
                        v3_9.setType("vnd.android-dir/mms-sms");
                        this.this$0.startActivity(v3_9);
                    }
                } else {
                    try {
                        android.content.Intent v3_1 = new android.content.Intent("android.intent.action.VIEW");
                        v3_1.setData(android.net.Uri.parse(p14));
                        this.this$0.startActivity(v3_1);
                    } catch (java.io.PrintStream v8_17) {
                        System.out.println(new StringBuilder().append("Error sending email ").append(p14).append(": ").append(v8_17.toString()).toString());
                    }
                    v8_10 = 1;
                }
            } else {
                try {
                    android.content.Intent v3_3 = new android.content.Intent("android.intent.action.VIEW");
                    v3_3.setData(android.net.Uri.parse(p14));
                    this.this$0.startActivity(v3_3);
                } catch (java.io.PrintStream v8_24) {
                    System.out.println(new StringBuilder().append("Error showing map ").append(p14).append(": ").append(v8_24.toString()).toString());
                }
                v8_10 = 1;
            }
        } else {
            try {
                android.content.Intent v3_5 = new android.content.Intent("android.intent.action.DIAL");
                v3_5.setData(android.net.Uri.parse(p14));
                this.this$0.startActivity(v3_5);
            } catch (java.io.PrintStream v8_29) {
                System.out.println(new StringBuilder().append("Error dialing ").append(p14).append(": ").append(v8_29.toString()).toString());
            }
            v8_10 = 1;
        }
        return v8_10;
    }
}
