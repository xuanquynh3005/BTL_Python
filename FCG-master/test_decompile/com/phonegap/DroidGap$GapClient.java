package com.phonegap;
public class DroidGap$GapClient extends android.webkit.WebChromeClient {
    private com.phonegap.DroidGap ctx;
    final synthetic com.phonegap.DroidGap this$0;

    public DroidGap$GapClient(com.phonegap.DroidGap p1, android.content.Context p2)
    {
        this.this$0 = p1;
        this.ctx = ((com.phonegap.DroidGap) p2);
        return;
    }

    public boolean onJsAlert(android.webkit.WebView p4, String p5, String p6, android.webkit.JsResult p7)
    {
        android.app.AlertDialog$Builder v0_1 = new android.app.AlertDialog$Builder(this.ctx);
        v0_1.setMessage(p6);
        v0_1.setTitle("Alert");
        v0_1.setCancelable(0);
        v0_1.setPositiveButton(17039370, new com.phonegap.DroidGap$GapClient$1(this, p7));
        v0_1.create();
        v0_1.show();
        return 1;
    }

    public boolean onJsConfirm(android.webkit.WebView p4, String p5, String p6, android.webkit.JsResult p7)
    {
        android.app.AlertDialog$Builder v0_1 = new android.app.AlertDialog$Builder(this.ctx);
        v0_1.setMessage(p6);
        v0_1.setTitle("Confirm");
        v0_1.setCancelable(0);
        v0_1.setPositiveButton(17039370, new com.phonegap.DroidGap$GapClient$2(this, p7));
        v0_1.setNegativeButton(17039360, new com.phonegap.DroidGap$GapClient$3(this, p7));
        v0_1.create();
        v0_1.show();
        return 1;
    }

    public boolean onJsPrompt(android.webkit.WebView p18, String p19, String p20, String p21, android.webkit.JsPromptResult p22)
    {
        int v15 = 0;
        if (com.phonegap.DroidGap.access$300(this.ctx).equals(com.phonegap.DroidGap.access$200(this.ctx, p19))) {
            v15 = 1;
        }
        if ((v15 == 0) || ((p21 == null) || ((p21.length() <= 3) || (!p21.substring(0, 4).equals("gap:"))))) {
            if ((v15 == 0) || (!p21.equals("gap_poll:"))) {
                if ((v15 == 0) || (!p21.equals("gap_callbackServer:"))) {
                    android.app.AlertDialog$Builder v11_1 = new android.app.AlertDialog$Builder(this.ctx);
                    v11_1.setMessage(p20);
                    android.widget.EditText v13_1 = new android.widget.EditText(this.ctx);
                    v11_1.setView(v13_1);
                    v11_1.setCancelable(0);
                    boolean v5_9 = new com.phonegap.DroidGap$GapClient$4;
                    v5_9(this, v13_1, p22);
                    v11_1.setPositiveButton(17039370, v5_9);
                    boolean v5_10 = new com.phonegap.DroidGap$GapClient$5;
                    v5_10(this, p22);
                    v11_1.setNegativeButton(17039360, v5_10);
                    v11_1.create();
                    v11_1.show();
                } else {
                    String v14_0 = "";
                    if (!p20.equals("usePolling")) {
                        if (!p20.equals("restartServer")) {
                            if (!p20.equals("getPort")) {
                                if (p20.equals("getToken")) {
                                    v14_0 = this.this$0.callbackServer.getToken();
                                }
                            } else {
                                v14_0 = Integer.toString(this.this$0.callbackServer.getPort());
                            }
                        } else {
                            this.this$0.callbackServer.restartServer();
                        }
                    } else {
                        v14_0 = new StringBuilder().append("").append(this.this$0.callbackServer.usePolling()).toString();
                    }
                    p22.confirm(v14_0);
                }
            } else {
                p22.confirm(this.this$0.callbackServer.getJavascript());
            }
        } else {
            try {
                org.json.JSONArray v10_1 = new org.json.JSONArray(p21.substring(4));
                p22.confirm(this.this$0.pluginManager.exec(v10_1.getString(0), v10_1.getString(1), v10_1.getString(2), p20, v10_1.getBoolean(3)));
            } catch (com.phonegap.CallbackServer v4_24) {
                v4_24.printStackTrace();
            }
        }
        return 1;
    }
}
