package com.phonegap;
public class CompassListener extends com.phonegap.api.Plugin implements android.hardware.SensorEventListener {
    public static int ERROR_FAILED_TO_START;
    public static int RUNNING;
    public static int STARTING;
    public static int STOPPED;
    public long TIMEOUT;
    float heading;
    long lastAccessTime;
    android.hardware.Sensor mSensor;
    private android.hardware.SensorManager sensorManager;
    int status;
    long timeStamp;

    static CompassListener()
    {
        com.phonegap.CompassListener.STOPPED = 0;
        com.phonegap.CompassListener.STARTING = 1;
        com.phonegap.CompassListener.RUNNING = 2;
        com.phonegap.CompassListener.ERROR_FAILED_TO_START = 3;
        return;
    }

    public CompassListener()
    {
        this.TIMEOUT = 30000;
        this.timeStamp = 0;
        this.setStatus(com.phonegap.CompassListener.STOPPED);
        return;
    }

    private void setStatus(int p1)
    {
        this.status = p1;
        return;
    }

    public com.phonegap.api.PluginResult execute(String p14, org.json.JSONArray p15, String p16)
    {
        com.phonegap.api.PluginResult$Status v7 = com.phonegap.api.PluginResult$Status.OK;
        try {
            com.phonegap.api.PluginResult v10_10;
            if (!p14.equals("start")) {
                if (!p14.equals("stop")) {
                    if (!p14.equals("getStatus")) {
                        if (!p14.equals("getHeading")) {
                            if (!p14.equals("setTimeout")) {
                                if (!p14.equals("getTimeout")) {
                                    v10_10 = new com.phonegap.api.PluginResult(v7, "");
                                } else {
                                    v10_10 = new com.phonegap.api.PluginResult(v7, ((float) this.getTimeout()));
                                }
                            } else {
                                this.setTimeout(p15.getLong(0));
                            }
                        } else {
                            if (this.status != com.phonegap.CompassListener.RUNNING) {
                                if (this.start() != com.phonegap.CompassListener.ERROR_FAILED_TO_START) {
                                    long v8 = 2000;
                                    while ((this.status == com.phonegap.CompassListener.STARTING) && (v8 > 0)) {
                                        v8 -= 100;
                                        try {
                                            Thread.sleep(100);
                                        } catch (InterruptedException v0_0) {
                                            v0_0.printStackTrace();
                                        }
                                    }
                                    if (v8 == 0) {
                                        v10_10 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.IO_EXCEPTION, com.phonegap.AccelListener.ERROR_FAILED_TO_START);
                                    }
                                } else {
                                    v10_10 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.IO_EXCEPTION, com.phonegap.CompassListener.ERROR_FAILED_TO_START);
                                }
                            }
                            v10_10 = new com.phonegap.api.PluginResult(v7, this.getHeading());
                        }
                    } else {
                        v10_10 = new com.phonegap.api.PluginResult(v7, this.getStatus());
                    }
                } else {
                    this.stop();
                }
            } else {
                this.start();
            }
        } catch (com.phonegap.api.PluginResult v10_29) {
            v10_29.printStackTrace();
            v10_10 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v10_10;
    }

    public float getHeading()
    {
        this.lastAccessTime = System.currentTimeMillis();
        return this.heading;
    }

    public int getStatus()
    {
        return this.status;
    }

    public long getTimeout()
    {
        return this.TIMEOUT;
    }

    public boolean isSynch(String p4)
    {
        int v0_2;
        if (!p4.equals("getStatus")) {
            if (!p4.equals("getHeading")) {
                if (p4.equals("getTimeout")) {
                    v0_2 = 1;
                    return v0_2;
                }
            } else {
                if (this.status == com.phonegap.CompassListener.RUNNING) {
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

    public void onSensorChanged(android.hardware.SensorEvent p6)
    {
        float v0 = p6.values[0];
        this.timeStamp = System.currentTimeMillis();
        this.heading = v0;
        this.setStatus(com.phonegap.CompassListener.RUNNING);
        if ((this.timeStamp - this.lastAccessTime) > this.TIMEOUT) {
            this.stop();
        }
        return;
    }

    public void setContext(com.phonegap.api.PhonegapActivity p2)
    {
        super.setContext(p2);
        this.sensorManager = ((android.hardware.SensorManager) p2.getSystemService("sensor"));
        return;
    }

    public void setTimeout(long p1)
    {
        this.TIMEOUT = p1;
        return;
    }

    public int start()
    {
        if ((this.status != com.phonegap.CompassListener.RUNNING) && (this.status != com.phonegap.CompassListener.STARTING)) {
            java.util.List v0 = this.sensorManager.getSensorList(3);
            if (v0.size() <= 0) {
                this.setStatus(com.phonegap.CompassListener.ERROR_FAILED_TO_START);
            } else {
                this.mSensor = ((android.hardware.Sensor) v0.get(0));
                this.sensorManager.registerListener(this, this.mSensor, 3);
                this.lastAccessTime = System.currentTimeMillis();
                this.setStatus(com.phonegap.CompassListener.STARTING);
            }
            int v1_9 = this.status;
        } else {
            v1_9 = this.status;
        }
        return v1_9;
    }

    public void stop()
    {
        if (this.status != com.phonegap.CompassListener.STOPPED) {
            this.sensorManager.unregisterListener(this);
        }
        this.setStatus(com.phonegap.CompassListener.STOPPED);
        return;
    }
}
