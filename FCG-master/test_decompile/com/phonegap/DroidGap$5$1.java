package com.phonegap;
 class DroidGap$5$1 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.phonegap.DroidGap$5 this$1;

    DroidGap$5$1(com.phonegap.DroidGap$5 p1)
    {
        this.this$1 = p1;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        p2.dismiss();
        if (this.this$1.val$exit) {
            this.this$1.val$me.finish();
        }
        return;
    }
}
