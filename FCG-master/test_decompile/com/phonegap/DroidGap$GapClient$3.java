package com.phonegap;
 class DroidGap$GapClient$3 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.phonegap.DroidGap$GapClient this$1;
    final synthetic android.webkit.JsResult val$result;

    DroidGap$GapClient$3(com.phonegap.DroidGap$GapClient p1, android.webkit.JsResult p2)
    {
        this.this$1 = p1;
        this.val$result = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        this.val$result.cancel();
        return;
    }
}
