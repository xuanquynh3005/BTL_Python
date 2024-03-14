package com.phonegap;
 class NetworkManager$1 extends android.content.BroadcastReceiver {
    final synthetic com.phonegap.NetworkManager this$0;

    NetworkManager$1(com.phonegap.NetworkManager p1)
    {
        this.this$0 = p1;
        return;
    }

    public void onReceive(android.content.Context p3, android.content.Intent p4)
    {
        com.phonegap.NetworkManager.access$000(this.this$0, ((android.net.NetworkInfo) p4.getParcelableExtra("networkInfo")));
        return;
    }
}
