package com.phonegap;
 class DroidGap$5 implements java.lang.Runnable {
    final synthetic com.phonegap.DroidGap this$0;
    final synthetic String val$button;
    final synthetic boolean val$exit;
    final synthetic com.phonegap.DroidGap val$me;
    final synthetic String val$message;
    final synthetic String val$title;

    DroidGap$5(com.phonegap.DroidGap p1, com.phonegap.DroidGap p2, String p3, String p4, String p5, boolean p6)
    {
        this.this$0 = p1;
        this.val$me = p2;
        this.val$message = p3;
        this.val$title = p4;
        this.val$button = p5;
        this.val$exit = p6;
        return;
    }

    public void run()
    {
        android.app.AlertDialog$Builder v0_1 = new android.app.AlertDialog$Builder(this.val$me);
        v0_1.setMessage(this.val$message);
        v0_1.setTitle(this.val$title);
        v0_1.setCancelable(0);
        v0_1.setPositiveButton(this.val$button, new com.phonegap.DroidGap$5$1(this));
        v0_1.create();
        v0_1.show();
        return;
    }
}
