package com.phonegap;
 class DroidGap$LinearLayoutSoftKeyboardDetect extends android.widget.LinearLayout {
    private static final String LOG_TAG = "SoftKeyboardDetect";
    private int oldHeight;
    private int oldWidth;
    private int screenHeight;
    private int screenWidth;
    final synthetic com.phonegap.DroidGap this$0;

    public DroidGap$LinearLayoutSoftKeyboardDetect(com.phonegap.DroidGap p2, android.content.Context p3, int p4, int p5)
    {
        this.this$0 = p2;
        super(p3);
        super.oldHeight = 0;
        super.oldWidth = 0;
        super.screenWidth = 0;
        super.screenHeight = 0;
        super.screenWidth = p4;
        super.screenHeight = p5;
        return;
    }

    protected void onMeasure(int p7, int p8)
    {
        super.onMeasure(p7, p8);
        android.util.Log.d("SoftKeyboardDetect", "We are in our onMeasure method");
        int v0 = android.view.View$MeasureSpec.getSize(p8);
        int v2 = android.view.View$MeasureSpec.getSize(p7);
        android.util.Log.d("SoftKeyboardDetect", new StringBuilder().append("Old Height = ").append(this.oldHeight).toString());
        android.util.Log.d("SoftKeyboardDetect", new StringBuilder().append("Height = ").append(v0).toString());
        android.util.Log.d("SoftKeyboardDetect", new StringBuilder().append("Old Width = ").append(this.oldWidth).toString());
        android.util.Log.d("SoftKeyboardDetect", new StringBuilder().append("Width = ").append(v2).toString());
        if ((this.oldHeight != 0) && (this.oldHeight != v0)) {
            if (this.screenHeight != v2) {
                if (v0 <= this.oldHeight) {
                    if (v0 < this.oldHeight) {
                        android.util.Log.d("SoftKeyboardDetect", "Throw show keyboard event");
                        this.this$0.callbackServer.sendJavascript("PhoneGap.fireEvent(\'showkeyboard\');");
                    }
                } else {
                    android.util.Log.d("SoftKeyboardDetect", "Throw hide keyboard event");
                    this.this$0.callbackServer.sendJavascript("PhoneGap.fireEvent(\'hidekeyboard\');");
                }
            } else {
                int v1 = this.screenHeight;
                this.screenHeight = this.screenWidth;
                this.screenWidth = v1;
                android.util.Log.d("SoftKeyboardDetect", "Orientation Change");
            }
        } else {
            android.util.Log.d("SoftKeyboardDetect", "Ignore this event");
        }
        this.oldHeight = v0;
        this.oldWidth = v2;
        return;
    }
}
