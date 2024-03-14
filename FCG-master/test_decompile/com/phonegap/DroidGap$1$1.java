package com.phonegap;
 class DroidGap$1$1 implements java.lang.Runnable {
    final synthetic com.phonegap.DroidGap$1 this$1;
    final synthetic int val$currentLoadUrlTimeout;

    DroidGap$1$1(com.phonegap.DroidGap$1 p1, int p2)
    {
        this.this$1 = p1;
        this.val$currentLoadUrlTimeout = p2;
        return;
    }

    public void run()
    {
        try {
        } catch (android.webkit.WebViewClient v1_14) {
            v1_14.printStackTrace();
            if (com.phonegap.DroidGap.access$100(this.this$1.val$me) == this.val$currentLoadUrlTimeout) {
                this.this$1.val$me.appView.stopLoading();
                this.this$1.val$me.webViewClient.onReceivedError(this.this$1.val$me.appView, -6, "The connection to the server was unsuccessful.", this.this$1.val$url);
            }
            return;
        }
        this.wait(((long) this.this$1.val$me.loadUrlTimeoutValue));
    }
}
