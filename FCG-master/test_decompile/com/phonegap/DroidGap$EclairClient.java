package com.phonegap;
public class DroidGap$EclairClient extends com.phonegap.DroidGap$GapClient {
    private long MAX_QUOTA;
    private String TAG;
    final synthetic com.phonegap.DroidGap this$0;

    public DroidGap$EclairClient(com.phonegap.DroidGap p3, android.content.Context p4)
    {
        this.this$0 = p3;
        super(p3, p4);
        super.TAG = "PhoneGapLog";
        super.MAX_QUOTA = 104857600;
        return;
    }

    public void onConsoleMessage(String p4, int p5, String p6)
    {
        android.util.Log.d(this.TAG, new StringBuilder().append(p6).append(": Line ").append(Integer.toString(p5)).append(" : ").append(p4).toString());
        return;
    }

    public void onExceededDatabaseQuota(String p6, String p7, long p8, long p10, long p12, android.webkit.WebStorage$QuotaUpdater p14)
    {
        android.util.Log.d(this.TAG, new StringBuilder().append("event raised onExceededDatabaseQuota estimatedSize: ").append(Long.toString(p10)).append(" currentQuota: ").append(Long.toString(p8)).append(" totalUsedQuota: ").append(Long.toString(p12)).toString());
        if (p10 >= this.MAX_QUOTA) {
            p14.updateQuota(p8);
        } else {
            android.util.Log.d(this.TAG, new StringBuilder().append("calling quotaUpdater.updateQuota newQuota: ").append(Long.toString(p10)).toString());
            p14.updateQuota(p10);
        }
        return;
    }

    public void onGeolocationPermissionsShowPrompt(String p3, android.webkit.GeolocationPermissions$Callback p4)
    {
        super.onGeolocationPermissionsShowPrompt(p3, p4);
        p4.invoke(p3, 1, 0);
        return;
    }
}
