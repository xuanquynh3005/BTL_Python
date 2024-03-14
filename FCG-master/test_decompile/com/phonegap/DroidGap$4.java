package com.phonegap;
 class DroidGap$4 implements java.lang.Runnable {
    final synthetic com.phonegap.DroidGap this$0;
    final synthetic String val$errorUrl;
    final synthetic com.phonegap.DroidGap val$me;

    DroidGap$4(com.phonegap.DroidGap p1, com.phonegap.DroidGap p2, String p3)
    {
        this.this$0 = p1;
        this.val$me = p2;
        this.val$errorUrl = p3;
        return;
    }

    public void run()
    {
        this.val$me.appView.loadUrl(this.val$errorUrl);
        return;
    }
}
