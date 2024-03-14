package com.phonegap;
public class FileTransfer extends com.phonegap.api.Plugin {
    private static final String BOUNDRY = "*****";
    public static int CONNECTION_ERR = 0;
    static final javax.net.ssl.HostnameVerifier DO_NOT_VERIFY = None;
    public static int FILE_NOT_FOUND_ERR = 0;
    public static int INVALID_URL_ERR = 0;
    private static final String LINE_END = "\r\n";
    private static final String LINE_START = "--";
    private static final String LOG_TAG = "FileUploader";
    private javax.net.ssl.HostnameVerifier defaultHostnameVerifier;
    private javax.net.ssl.SSLSocketFactory defaultSSLSocketFactory;

    static FileTransfer()
    {
        com.phonegap.FileTransfer.FILE_NOT_FOUND_ERR = 1;
        com.phonegap.FileTransfer.INVALID_URL_ERR = 2;
        com.phonegap.FileTransfer.CONNECTION_ERR = 3;
        com.phonegap.FileTransfer.DO_NOT_VERIFY = new com.phonegap.FileTransfer$1();
        return;
    }

    public FileTransfer()
    {
        this.defaultSSLSocketFactory = 0;
        this.defaultHostnameVerifier = 0;
        return;
    }

    private org.json.JSONObject createFileUploadError(int p6)
    {
        org.json.JSONObject v1 = 0;
        try {
            org.json.JSONObject v2_1 = new org.json.JSONObject();
            try {
                v2_1.put("code", p6);
                v1 = v2_1;
            } catch (org.json.JSONException v3_3) {
                org.json.JSONException v0 = v3_3;
                v1 = v2_1;
                android.util.Log.e("FileUploader", v0.getMessage(), v0);
            }
            return v1;
        } catch (org.json.JSONException v3_1) {
            v0 = v3_1;
        }
    }

    private String getArgument(org.json.JSONArray p3, int p4, String p5)
    {
        String v0 = p5;
        if (p3.length() >= p4) {
            v0 = p3.optString(p4);
            if ((v0 == null) || ("null".equals(v0))) {
                v0 = p5;
            }
        }
        return v0;
    }

    private java.io.InputStream getPathFromUri(String p3)
    {
        java.io.FileInputStream v1_2;
        if (!p3.startsWith("content:")) {
            v1_2 = new java.io.FileInputStream(p3);
        } else {
            v1_2 = this.ctx.getContentResolver().openInputStream(android.net.Uri.parse(p3));
        }
        return v1_2;
    }

    private void trustAllHosts()
    {
        javax.net.ssl.TrustManager[] v2 = new javax.net.ssl.TrustManager[1];
        v2[0] = new com.phonegap.FileTransfer$2(this);
        try {
            this.defaultSSLSocketFactory = javax.net.ssl.HttpsURLConnection.getDefaultSSLSocketFactory();
            javax.net.ssl.SSLContext v1 = javax.net.ssl.SSLContext.getInstance("TLS");
            v1.init(0, v2, new java.security.SecureRandom());
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(v1.getSocketFactory());
        } catch (String v3_3) {
            Exception v0 = v3_3;
            android.util.Log.e("FileUploader", v0.getMessage(), v0);
        }
        return;
    }

    public com.phonegap.api.PluginResult execute(String p12, org.json.JSONArray p13, String p14)
    {
        String v2 = 0;
        try {
            String v1 = p13.getString(0);
            v2 = p13.getString(1);
            org.json.JSONObject v4 = 0;
            com.phonegap.api.PluginResult$Status v3_4 = this.getArgument(p13, 2, "file");
            org.json.JSONObject v4_1 = this.getArgument(p13, 3, "image.jpg");
            String v5_1 = this.getArgument(p13, 4, "image/jpeg");
            try {
                com.phonegap.api.PluginResult v0_1;
                org.json.JSONObject v6 = p13.optJSONObject(5);
                boolean v7 = p13.optBoolean(6);
            } catch (com.phonegap.api.PluginResult v0_2) {
                org.json.JSONException v8_0 = v0_2;
                android.util.Log.e("FileUploader", v8_0.getMessage(), v8_0);
                v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.IO_EXCEPTION, this.createFileUploadError(com.phonegap.FileTransfer.FILE_NOT_FOUND_ERR));
                return v0_1;
            } catch (com.phonegap.api.PluginResult v0_34) {
                org.json.JSONException v8_4 = v0_34;
                android.util.Log.e("FileUploader", v8_4.getMessage(), v8_4);
                v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.IO_EXCEPTION, this.createFileUploadError(com.phonegap.FileTransfer.INVALID_URL_ERR));
                return v0_1;
            } catch (com.phonegap.api.PluginResult v0_29) {
                org.json.JSONException v8_3 = v0_29;
                android.util.Log.e("FileUploader", v8_3.getMessage(), v8_3);
                android.util.Log.d("FileUploader", "Got my ssl exception!!!");
                v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.IO_EXCEPTION, this.createFileUploadError(com.phonegap.FileTransfer.CONNECTION_ERR));
                return v0_1;
            } catch (com.phonegap.api.PluginResult v0_25) {
                org.json.JSONException v8_2 = v0_25;
                android.util.Log.e("FileUploader", v8_2.getMessage(), v8_2);
                v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.IO_EXCEPTION, this.createFileUploadError(com.phonegap.FileTransfer.CONNECTION_ERR));
                return v0_1;
            } catch (com.phonegap.api.PluginResult v0_22) {
                org.json.JSONException v8_1 = v0_22;
                android.util.Log.e("FileUploader", v8_1.getMessage(), v8_1);
                v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
                return v0_1;
            }
            if (!p12.equals("upload")) {
                v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.INVALID_ACTION);
                return v0_1;
            } else {
                com.phonegap.FileUploadResult v10 = this.upload(v1, v2, v3_4, v4_1, v5_1, v6, v7);
                android.util.Log.d("FileUploader", "****** About to return a result from upload");
                v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, v10.toJSONObject());
                return v0_1;
            }
        } catch (com.phonegap.api.PluginResult v0_11) {
            android.util.Log.d("FileUploader", "Missing filename or server name");
            v0_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION, "Missing filename or server name");
            return v0_1;
        }
    }

    public com.phonegap.FileUploadResult upload(String p27, String p28, String p29, String p30, String p31, org.json.JSONObject p32, boolean p33)
    {
        javax.net.ssl.HttpsURLConnection v8_1;
        com.phonegap.FileUploadResult v20_1 = new com.phonegap.FileUploadResult();
        java.io.InputStream v12 = this.getPathFromUri(p27);
        java.io.DataOutputStream v10 = 0;
        java.net.URL v23 = new java.net.URL;
        v23(p28);
        if (!v23.getProtocol().toLowerCase().equals("https")) {
            v8_1 = ((java.net.HttpURLConnection) v23.openConnection());
        } else {
            if (p33) {
                this = this.trustAllHosts();
                javax.net.ssl.HttpsURLConnection v13_1 = ((javax.net.ssl.HttpsURLConnection) v23.openConnection());
                this.defaultHostnameVerifier = v13_1.getHostnameVerifier();
                v13_1.setHostnameVerifier(com.phonegap.FileTransfer.DO_NOT_VERIFY);
                v8_1 = v13_1;
            } else {
                v8_1 = ((javax.net.ssl.HttpsURLConnection) v23.openConnection());
            }
        }
        v8_1.setDoInput(1);
        v8_1.setDoOutput(1);
        v8_1.setUseCaches(0);
        v8_1.setRequestMethod("POST");
        v8_1.setRequestProperty("Connection", "Keep-Alive");
        v8_1.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****");
        String v9 = android.webkit.CookieManager.getInstance().getCookie(p28);
        if (v9 != null) {
            v8_1.setRequestProperty("Cookie", v9);
        }
        v10 = new java.io.DataOutputStream;
        v10(v8_1.getOutputStream());
        try {
            java.util.Iterator v15 = p32.keys();
        } catch (javax.net.ssl.SSLSocketFactory v24_64) {
            java.io.FileNotFoundException v11 = v24_64;
            android.util.Log.e("FileUploader", v11.getMessage(), v11);
            v10.writeBytes("--*****\r\n");
            v10.writeBytes(new StringBuilder().append("Content-Disposition: form-data; name=\"").append(p29).append("\";").append(" filename=\"").append(p30).append("\"").append("\r\n").toString());
            v10.writeBytes(new StringBuilder().append("Content-Type: ").append(p31).append("\r\n").toString());
            v10.writeBytes("\r\n");
            int v5 = Math.min(v12.available(), 8096);
            byte[] v4 = new byte[v5];
            int v7 = v12.read(v4, 0, v5);
            long v21 = 0;
            while (v7 > 0) {
                v21 += ((long) v7);
                v20_1.setBytesSent(v21);
                v10.write(v4, 0, v5);
                v5 = Math.min(v12.available(), 8096);
                v7 = v12.read(v4, 0, v5);
            }
            v10.writeBytes("\r\n");
            v10.writeBytes("--*****--\r\n");
            v12.close();
            v10.flush();
            v10.close();
            StringBuffer v19 = new StringBuffer;
            v19("");
            try {
                java.io.DataInputStream v14 = new java.io.DataInputStream;
                v14(v8_1.getInputStream());
            } catch (javax.net.ssl.SSLSocketFactory v24_26) {
                throw new java.io.IOException("Received error from server");
            }
            while(true) {
                String v17 = v14.readLine();
                if (v17 == null) {
                    break;
                }
                v19.append(v17);
            }
            android.util.Log.d("FileUploader", "got response from server");
            android.util.Log.d("FileUploader", v19.toString());
            v20_1.setResponseCode(v8_1.getResponseCode());
            v20_1.setResponse(v19.toString());
            v14.close();
            v8_1.disconnect();
            if ((p33) && (v23.getProtocol().toLowerCase().equals("https"))) {
                ((javax.net.ssl.HttpsURLConnection) v8_1).setHostnameVerifier(this.defaultHostnameVerifier);
                javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(this.defaultSSLSocketFactory);
            }
            return v20_1;
        }
        while (v15.hasNext()) {
            Object v16 = v15.next();
            v10.writeBytes("--*****\r\n");
            v10.writeBytes(new StringBuilder().append("Content-Disposition: form-data; name=\"").append(v16.toString()).append("\";").toString());
            v10.writeBytes("\r\n\r\n");
            v10.writeBytes(p32.getString(v16.toString()));
            v10.writeBytes("\r\n");
        }
    }
}
