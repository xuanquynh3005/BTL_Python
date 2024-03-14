package com.phonegap;
public class FileUploadResult {
    private long bytesSent;
    private String response;
    private int responseCode;

    public FileUploadResult()
    {
        this.bytesSent = 0;
        this.responseCode = -1;
        this.response = 0;
        return;
    }

    public long getBytesSent()
    {
        return this.bytesSent;
    }

    public String getResponse()
    {
        return this.response;
    }

    public int getResponseCode()
    {
        return this.responseCode;
    }

    public void setBytesSent(long p1)
    {
        this.bytesSent = p1;
        return;
    }

    public void setResponse(String p1)
    {
        this.response = p1;
        return;
    }

    public void setResponseCode(int p1)
    {
        this.responseCode = p1;
        return;
    }

    public org.json.JSONObject toJSONObject()
    {
        return new org.json.JSONObject(new StringBuilder().append("{bytesSent:").append(this.bytesSent).append(",responseCode:").append(this.responseCode).append(",response:").append(org.json.JSONObject.quote(this.response)).append("}").toString());
    }
}
