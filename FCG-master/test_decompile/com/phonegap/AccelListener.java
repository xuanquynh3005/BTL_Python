package com.phonegap;
public class AccelListener extends com.phonegap.api.Plugin implements android.hardware.SensorEventListener {
    public static int ERROR_FAILED_TO_START;
    public static int RUNNING;
    public static int STARTING;
    public static int STOPPED;
    public float TIMEOUT;
    long lastAccessTime;
    android.hardware.Sensor mSensor;
    private android.hardware.SensorManager sensorManager;
    int status;
    long timestamp;
    float x;
    float y;
    float z;

    static AccelListener()
    {
        com.phonegap.AccelListener.STOPPED = 0;
        com.phonegap.AccelListener.STARTING = 1;
        com.phonegap.AccelListener.RUNNING = 2;
        com.phonegap.AccelListener.ERROR_FAILED_TO_START = 3;
        return;
    }

    public AccelListener()
    {
        this.TIMEOUT = 1189765120;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.timestamp = 0;
        this.setStatus(com.phonegap.AccelListener.STOPPED);
        return;
    }

    private void setStatus(int p1)
    {
        this.status = p1;
        return;
    }

    public com.phonegap.api.PluginResult execute(String p12, org.json.JSONArray p13, String p14)
    {
        com.phonegap.api.PluginResult$Status v5 = com.phonegap.api.PluginResult$Status.OK;
        try {
            com.phonegap.api.PluginResult v8_1;
            if (!p12.equals("getStatus")) {
                if (!p12.equals("start")) {
                    if (!p12.equals("stop")) {
                        if (!p12.equals("getAcceleration")) {
                            if (!p12.equals("setTimeout")) {
                                if (p12.equals("getTimeout")) {
                                    v8_1 = new com.phonegap.api.PluginResult(v5, this.getTimeout());
                                }
                            } else {
                                try {
                                    this.setTimeout(Float.parseFloat(p13.getString(0)));
                                    v8_1 = new com.phonegap.api.PluginResult(v5, 0);
                                } catch (com.phonegap.api.PluginResult v8_21) {
                                    v5 = com.phonegap.api.PluginResult$Status.INVALID_ACTION;
                                    v8_21.printStackTrace();
                                }
                            }
                            v8_1 = new com.phonegap.api.PluginResult(v5, "");
                        } else {
                            if (this.status != com.phonegap.AccelListener.RUNNING) {
                                if (this.start() != com.phonegap.AccelListener.ERROR_FAILED_TO_START) {
                                    long v6_1 = 2000;
                                    while ((this.status == com.phonegap.AccelListener.STARTING) && (v6_1 > 0)) {
                                        v6_1 -= 100;
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException v0_2) {
                                            v0_2.printStackTrace();
                                        }
                                    }
                                    if (v6_1 == 0) {
                                        v8_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.IO_EXCEPTION, com.phonegap.AccelListener.ERROR_FAILED_TO_START);
                                    }
                                } else {
                                    v8_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.IO_EXCEPTION, com.phonegap.AccelListener.ERROR_FAILED_TO_START);
                                }
                            }
                            this.lastAccessTime = System.currentTimeMillis();
                            org.json.JSONObject v3_2 = new org.json.JSONObject();
                            v3_2.put("x", ((double) this.x));
                            v3_2.put("y", ((double) this.y));
                            v3_2.put("z", ((double) this.z));
                            v3_2.put("timestamp", this.timestamp);
                            v8_1 = new com.phonegap.api.PluginResult(v5, v3_2);
                        }
                    } else {
                        this.stop();
                        v8_1 = new com.phonegap.api.PluginResult(v5, 0);
                    }
                } else {
                    v8_1 = new com.phonegap.api.PluginResult(v5, this.start());
                }
            } else {
                v8_1 = new com.phonegap.api.PluginResult(v5, this.getStatus());
            }
        } catch (com.phonegap.api.PluginResult v8_5) {
            v8_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v8_1;
    }

    public int getStatus()
    {
        return this.status;
    }

    public float getTimeout()
    {
        return this.TIMEOUT;
    }

    public boolean isSynch(String p4)
    {
        int v0_2;
        if (!p4.equals("getStatus")) {
            if (!p4.equals("getAcceleration")) {
                if (p4.equals("getTimeout")) {
                    v0_2 = 1;
                    return v0_2;
                }
            } else {
                if (this.status == com.phonegap.AccelListener.RUNNING) {
                    v0_2 = 1;
                    return v0_2;
                }
            }
            v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        return v0_2;
    }

    public void onAccuracyChanged(android.hardware.Sensor p1, int p2)
    {
        return;
    }

    public void onDestroy()
    {
        this.stop();
        return;
    }

    public void onSensorChanged(android.hardware.SensorEvent p5)
    {
        if ((p5.sensor.getType() == 1) && (this.status != com.phonegap.AccelListener.STOPPED)) {
            this.timestamp = System.currentTimeMillis();
            this.x = p5.values[0];
            this.y = p5.values[1];
            this.z = p5.values[2];
            this.setStatus(com.phonegap.AccelListener.RUNNING);
            if (((float) (this.timestamp - this.lastAccessTime)) > this.TIMEOUT) {
                this.stop();
            }
        }
        return;
    }

    public void setContext(com.phonegap.api.PhonegapActivity p2)
    {
        super.setContext(p2);
        this.sensorManager = ((android.hardware.SensorManager) p2.getSystemService("sensor"));
        return;
    }

    public void setTimeout(float p1)
    {
        this.TIMEOUT = p1;
        return;
    }

    public int start()
    {
        if ((this.status != com.phonegap.AccelListener.RUNNING) && (this.status != com.phonegap.AccelListener.STARTING)) {
            java.util.List v0 = this.sensorManager.getSensorList(1);
            if ((v0 == null) || (v0.size() <= 0)) {
                this.setStatus(com.phonegap.AccelListener.ERROR_FAILED_TO_START);
            } else {
                this.mSensor = ((android.hardware.Sensor) v0.get(0));
                this.sensorManager.registerListener(this, this.mSensor, 0);
                this.setStatus(com.phonegap.AccelListener.STARTING);
                this.lastAccessTime = System.currentTimeMillis();
            }
            int v1_8 = this.status;
        } else {
            v1_8 = this.status;
        }
        return v1_8;
    }

    public void stop()
    {
        if (this.status != com.phonegap.AccelListener.STOPPED) {
            this.sensorManager.unregisterListener(this);
        }
        this.setStatus(com.phonegap.AccelListener.STOPPED);
        return;
    }
}
