package com.phonegap;
public class HttpHandler {

    public HttpHandler()
    {
        return;
    }

    private org.apache.http.HttpEntity getHttpEntity(String p7)
    {
        try {
            int v5_1 = new org.apache.http.impl.client.DefaultHttpClient().execute(new org.apache.http.client.methods.HttpGet(p7)).getEntity();
        } catch (int v5_0) {
            v5_0.printStackTrace();
            v5_1 = 0;
        }
        return v5_1;
    }

    private void writeToDisk(org.apache.http.HttpEntity p10, String p11)
    {
        int v3 = 0;
        String v0 = new StringBuilder().append("/sdcard/").append(p11).toString();
        try {
            java.io.InputStream v4 = p10.getContent();
            byte[] v1 = new byte[1024];
            java.io.FileOutputStream v6_1 = new java.io.FileOutputStream(v0);
        } catch (int v7_4) {
            v7_4.printStackTrace();
            return;
        }
        while(true) {
            int v5 = v4.read(v1);
            if (v5 <= 0) {
                break;
            }
            v6_1.write(v1, 0, v5);
            v3++;
        }
        v6_1.flush();
        v6_1.close();
        return;
    }

    protected Boolean get(String p4, String p5)
    {
        org.apache.http.HttpEntity v1 = this.getHttpEntity(p4);
        try {
            this.writeToDisk(v1, p5);
            try {
                v1.consumeContent();
                Boolean v2_1 = Boolean.valueOf(1);
            } catch (Exception v0_0) {
                v0_0.printStackTrace();
                v2_1 = Boolean.valueOf(v2_1);
            }
            return v2_1;
        } catch (Exception v0_1) {
            v0_1.printStackTrace();
            v2_1 = Boolean.valueOf(0);
            return v2_1;
        }
    }
}
