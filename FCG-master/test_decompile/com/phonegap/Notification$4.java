package com.phonegap;
 class Notification$4 implements java.lang.Runnable {
    final synthetic com.phonegap.Notification this$0;
    final synthetic com.phonegap.api.PhonegapActivity val$ctx;
    final synthetic String val$message;
    final synthetic com.phonegap.Notification val$notification;
    final synthetic String val$title;

    Notification$4(com.phonegap.Notification p1, com.phonegap.Notification p2, com.phonegap.api.PhonegapActivity p3, String p4, String p5)
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
        this.val$notification.progressDialog = new android.app.ProgressDialog(this.val$ctx);
        this.val$notification.progressDialog.setProgressStyle(1);
        this.val$notification.progressDialog.setTitle(this.val$title);
        this.val$notification.progressDialog.setMessage(this.val$message);
        this.val$notification.progressDialog.setCancelable(1);
        this.val$notification.progressDialog.setMax(100);
        this.val$notification.progressDialog.setProgress(0);
        this.val$notification.progressDialog.setOnCancelListener(new com.phonegap.Notification$4$1(this));
        this.val$notification.progressDialog.show();
        return;
    }
}
