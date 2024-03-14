package com.phonegap;
public class TempListener extends com.phonegap.api.Plugin implements android.hardware.SensorEventListener {
    android.hardware.Sensor mSensor;
    private android.hardware.SensorManager sensorManager;

    public TempListener()
    {
        return;
    }

    public com.phonegap.api.PluginResult execute(String p4, org.json.JSONArray p5, String p6)
    {
        if (!p4.equals("start")) {
            if (p4.equals("stop")) {
                this.stop();
            }
        } else {
            this.start();
        }
        return new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, "");
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

    public void onSensorChanged(android.hardware.SensorEvent p4)
    {
        this.sendJavascript(new StringBuilder().append("gotTemp(").append(p4.values[0]).append(");").toString());
        return;
    }

    public void setContext(com.phonegap.api.PhonegapActivity p2)
    {
        super.setContext(p2);
        this.sensorManager = ((android.hardware.SensorManager) p2.getSystemService("sensor"));
        return;
    }

    public void start()
    {
        java.util.List v0 = this.sensorManager.getSensorList(7);
        if (v0.size() > 0) {
            this.mSensor = ((android.hardware.Sensor) v0.get(0));
            this.sensorManager.registerListener(this, this.mSensor, 3);
        }
        return;
    }

    public void stop()
    {
        this.sensorManager.unregisterListener(this);
        return;
    }
}
