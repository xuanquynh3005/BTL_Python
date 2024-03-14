package com.phonegap;
 class Notification$1$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.phonegap.Notification$1 this$1;

    Notification$1$1(com.phonegap.Notification$1 p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p5, int p6)
    {
        p5.dismiss();
        this.this$1.val$notification.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, 0), this.this$1.val$callbackId);
        return;
    }
}
