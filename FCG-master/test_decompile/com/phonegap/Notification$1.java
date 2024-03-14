package com.phonegap;
 class Notification$1 implements java.lang.Runnable {
    final synthetic com.phonegap.Notification this$0;
    final synthetic String val$buttonLabel;
    final synthetic String val$callbackId;
    final synthetic com.phonegap.api.PhonegapActivity val$ctx;
    final synthetic String val$message;
    final synthetic com.phonegap.Notification val$notification;
    final synthetic String val$title;

    Notification$1(com.phonegap.Notification p1, com.phonegap.api.PhonegapActivity p2, String p3, String p4, String p5, com.phonegap.Notification p6, String p7)
    {
        this.this$0 = p1;
        this.val$ctx = p2;
        this.val$message = p3;
        this.val$title = p4;
        this.val$buttonLabel = p5;
        this.val$notification = p6;
        this.val$callbackId = p7;
        return;
    }

    public void run()
    {
        android.app.AlertDialog$Builder v0_1 = new android.app.AlertDialog$Builder(this.val$ctx);
        v0_1.setMessage(this.val$message);
        v0_1.setTitle(this.val$title);
        v0_1.setCancelable(0);
        v0_1.setPositiveButton(this.val$buttonLabel, new com.phonegap.Notification$1$1(this));
        v0_1.create();
        v0_1.show();
        return;
    }
}
