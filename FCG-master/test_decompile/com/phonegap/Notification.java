package com.phonegap;
public class Notification extends com.phonegap.api.Plugin {
    public int confirmResult;
    public android.app.ProgressDialog progressDialog;
    public android.app.ProgressDialog spinnerDialog;

    public Notification()
    {
        this.confirmResult = -1;
        this.spinnerDialog = 0;
        this.progressDialog = 0;
        return;
    }

    public declared_synchronized void activityStart(String p7, String p8)
    {
        try {
            if (this.spinnerDialog != null) {
                this.spinnerDialog.dismiss();
                this.spinnerDialog = 0;
            }
        } catch (com.phonegap.api.PhonegapActivity v1_5) {
            throw v1_5;
        }
        this.ctx.runOnUiThread(new com.phonegap.Notification$3(this, this, this.ctx, p7, p8));
        return;
    }

    public declared_synchronized void activityStop()
    {
        try {
            if (this.spinnerDialog != null) {
                this.spinnerDialog.dismiss();
                this.spinnerDialog = 0;
            }
        } catch (int v0_3) {
            throw v0_3;
        }
        return;
    }

    public declared_synchronized void alert(String p9, String p10, String p11, String p12)
    {
        try {
            this.ctx.runOnUiThread(new com.phonegap.Notification$1(this, this.ctx, p9, p10, p11, this, p12));
            return;
        } catch (Throwable v1_0) {
            throw v1_0;
        }
    }

    public void beep(long p11)
    {
        android.media.Ringtone v2 = android.media.RingtoneManager.getRingtone(this.ctx, android.media.RingtoneManager.getDefaultUri(2));
        if (v2 != null) {
            long v0 = 0;
            while (v0 < p11) {
                v2.play();
                long v4 = 5000;
                while ((v2.isPlaying()) && (v4 > 0)) {
                    v4 -= 100;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException v6) {
                    }
                }
                v0++;
            }
        }
        return;
    }

    public declared_synchronized void confirm(String p9, String p10, String p11, String p12)
    {
        try {
            this.ctx.runOnUiThread(new com.phonegap.Notification$2(this, this.ctx, p9, p10, p11.split(","), this, p12));
            return;
        } catch (Throwable v1_1) {
            throw v1_1;
        }
    }

    public com.phonegap.api.PluginResult execute(String p8, org.json.JSONArray p9, String p10)
    {
        try {
            boolean v4_3;
            if (!p8.equals("beep")) {
                if (!p8.equals("vibrate")) {
                    if (!p8.equals("alert")) {
                        if (!p8.equals("confirm")) {
                            if (!p8.equals("activityStart")) {
                                if (!p8.equals("activityStop")) {
                                    if (!p8.equals("progressStart")) {
                                        if (!p8.equals("progressValue")) {
                                            if (!p8.equals("progressStop")) {
                                                v4_3 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, "");
                                            } else {
                                                this.progressStop();
                                            }
                                        } else {
                                            this.progressValue(p9.getInt(0));
                                        }
                                    } else {
                                        this.progressStart(p9.getString(0), p9.getString(1));
                                    }
                                } else {
                                    this.activityStop();
                                }
                            } else {
                                this.activityStart(p9.getString(0), p9.getString(1));
                            }
                        } else {
                            this.confirm(p9.getString(0), p9.getString(1), p9.getString(2), p10);
                            com.phonegap.api.PluginResult v1_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.NO_RESULT);
                            v1_1.setKeepCallback(1);
                            v4_3 = v1_1;
                        }
                    } else {
                        this.alert(p9.getString(0), p9.getString(1), p9.getString(2), p10);
                        com.phonegap.api.PluginResult v1_3 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.NO_RESULT);
                        v1_3.setKeepCallback(1);
                        v4_3 = v1_3;
                    }
                } else {
                    this.vibrate(p9.getLong(0));
                }
            } else {
                this.beep(p9.getLong(0));
            }
        } catch (boolean v4_1) {
            v4_3 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v4_3;
    }

    public boolean isSynch(String p3)
    {
        int v0_10;
        if (!p3.equals("alert")) {
            if (!p3.equals("confirm")) {
                if (!p3.equals("activityStart")) {
                    if (!p3.equals("activityStop")) {
                        if (!p3.equals("progressStart")) {
                            if (!p3.equals("progressValue")) {
                                if (!p3.equals("progressStop")) {
                                    v0_10 = 0;
                                } else {
                                    v0_10 = 1;
                                }
                            } else {
                                v0_10 = 1;
                            }
                        } else {
                            v0_10 = 1;
                        }
                    } else {
                        v0_10 = 1;
                    }
                } else {
                    v0_10 = 1;
                }
            } else {
                v0_10 = 1;
            }
        } else {
            v0_10 = 1;
        }
        return v0_10;
    }

    public declared_synchronized void progressStart(String p7, String p8)
    {
        try {
            if (this.progressDialog != null) {
                this.progressDialog.dismiss();
                this.progressDialog = 0;
            }
        } catch (com.phonegap.api.PhonegapActivity v1_5) {
            throw v1_5;
        }
        this.ctx.runOnUiThread(new com.phonegap.Notification$4(this, this, this.ctx, p7, p8));
        return;
    }

    public declared_synchronized void progressStop()
    {
        try {
            if (this.progressDialog != null) {
                this.progressDialog.dismiss();
                this.progressDialog = 0;
            }
        } catch (int v0_3) {
            throw v0_3;
        }
        return;
    }

    public declared_synchronized void progressValue(int p2)
    {
        try {
            if (this.progressDialog != null) {
                this.progressDialog.setProgress(p2);
            }
        } catch (android.app.ProgressDialog v0_2) {
            throw v0_2;
        }
        return;
    }

    public void vibrate(long p4)
    {
        if (p4 == 0) {
            p4 = 500;
        }
        ((android.os.Vibrator) this.ctx.getSystemService("vibrator")).vibrate(p4);
        return;
    }
}
