package com.phonegap;
 class DroidGap$GapClient$5 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.phonegap.DroidGap$GapClient this$1;
    final synthetic android.webkit.JsPromptResult val$res;

    DroidGap$GapClient$5(com.phonegap.DroidGap$GapClient p1, android.webkit.JsPromptResult p2)
    {
        this.this$1 = p1;
        this.val$res = p2;
        return;
    }

    public void onClick(android.content.DialogInterface p2, int p3)
    {
        this.val$res.cancel();
        return;
    }
}
