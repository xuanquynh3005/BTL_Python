package com.phonegap;
 class Notification$3$1 implements android.content.DialogInterface$OnCancelListener {
    final synthetic com.phonegap.Notification$3 this$1;

    Notification$3$1(com.phonegap.Notification$3 p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onCancel(android.content.DialogInterface p3)
    {
        this.this$1.val$notification.spinnerDialog = 0;
        return;
    }
}
