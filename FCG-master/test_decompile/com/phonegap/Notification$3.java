package com.phonegap;
 class Notification$3 implements java.lang.Runnable {
    final synthetic com.phonegap.Notification this$0;
    final synthetic com.phonegap.api.PhonegapActivity val$ctx;
    final synthetic String val$message;
    final synthetic com.phonegap.Notification val$notification;
    final synthetic String val$title;

    Notification$3(com.phonegap.Notification p1, com.phonegap.Notification p2, com.phonegap.api.PhonegapActivity p3, String p4, String p5)
    {
        this.this$0 = p1;
        this.val$notification = p2;
        this.val$ctx = p3;
        this.val$title = p4;
        this.val$message = p5;
        return;
    }

    public void run()
    {
        this.val$notification.spinnerDialog = android.app.ProgressDialog.show(this.val$ctx, this.val$title, this.val$message, 1, 1, new com.phonegap.Notification$3$1(this));
        return;
    }
}
