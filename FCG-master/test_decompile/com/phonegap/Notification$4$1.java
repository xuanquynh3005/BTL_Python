package com.phonegap;
 class Notification$4$1 implements android.content.DialogInterface$OnCancelListener {
    final synthetic com.phonegap.Notification$4 this$1;

    Notification$4$1(com.phonegap.Notification$4 p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onCancel(android.content.DialogInterface p3)
    {
        this.this$1.val$notification.progressDialog = 0;
        return;
    }
}
