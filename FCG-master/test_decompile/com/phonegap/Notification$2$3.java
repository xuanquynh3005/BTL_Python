package com.phonegap;
 class Notification$2$3 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.phonegap.Notification$2 this$1;

    Notification$2$3(com.phonegap.Notification$2 p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p5, int p6)
    {
        p5.dismiss();
        this.this$1.val$notification.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, 3), this.this$1.val$callbackId);
        return;
    }
}
