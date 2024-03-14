package com.phonegap;
 class DroidGap$GapClient$4 implements android.content.DialogInterface$OnClickListener {
    final synthetic com.phonegap.DroidGap$GapClient this$1;
    final synthetic android.widget.EditText val$input;
    final synthetic android.webkit.JsPromptResult val$res;

    DroidGap$GapClient$4(com.phonegap.DroidGap$GapClient p1, android.widget.EditText p2, android.webkit.JsPromptResult p3)
    {
        this.this$1 = p1;
        this.val$input = p2;
        this.val$res = p3;
        return;
    }

    public void onClick(android.content.DialogInterface p3, int p4)
    {
        this.val$res.confirm(this.val$input.getText().toString());
        return;
    }
}
