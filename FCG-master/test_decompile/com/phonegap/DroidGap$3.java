package com.phonegap;
 class DroidGap$3 implements java.lang.Runnable {
    final synthetic com.phonegap.DroidGap this$0;
    final synthetic com.phonegap.DroidGap val$me;
    final synthetic int val$time;
    final synthetic String val$url;

    DroidGap$3(com.phonegap.DroidGap p1, int p2, com.phonegap.DroidGap p3, String p4)
    {
        this.this$0 = p1;
        this.val$time = p2;
        this.val$me = p3;
        this.val$url = p4;
        return;
    }

    public void run()
    {
        try {
        } catch (java.io.PrintStream v1_6) {
            v1_6.printStackTrace();
            if (this.val$me.cancelLoadUrl) {
                this.val$me.cancelLoadUrl = 0;
                System.out.println(new StringBuilder().append("Aborting loadUrl(").append(this.val$url).append("): Another URL was loaded before timer expired.").toString());
            } else {
                this.val$me.loadUrl(this.val$url);
            }
            return;
        }
        this.wait(((long) this.val$time));
    }
}
