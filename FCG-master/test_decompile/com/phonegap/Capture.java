package com.phonegap;
public class Capture extends com.phonegap.api.Plugin {
    private static final int CAPTURE_AUDIO = 0;
    private static final int CAPTURE_IMAGE = 1;
    private static final int CAPTURE_VIDEO = 2;
    private static final String LOG_TAG = "Capture";
    private static final String _DATA = "_data";
    private String callbackId;
    private double duration;
    private android.net.Uri imageUri;
    private long limit;
    private org.json.JSONArray results;

    public Capture()
    {
        return;
    }

    private void captureAudio()
    {
        this.ctx.startActivityForResult(this, new android.content.Intent("android.provider.MediaStore.RECORD_SOUND"), 0);
        return;
    }

    private void captureImage()
    {
        android.content.Intent v0_1 = new android.content.Intent("android.media.action.IMAGE_CAPTURE");
        java.io.File v1_1 = new java.io.File(android.os.Environment.getExternalStorageDirectory(), "Capture.jpg");
        v0_1.putExtra("output", android.net.Uri.fromFile(v1_1));
        this.imageUri = android.net.Uri.fromFile(v1_1);
        this.ctx.startActivityForResult(this, v0_1, 1);
        return;
    }

    private void captureVideo(double p4)
    {
        this.ctx.startActivityForResult(this, new android.content.Intent("android.media.action.VIDEO_CAPTURE"), 2);
        return;
    }

    private org.json.JSONObject createMediaFile(android.net.Uri p7)
    {
        java.io.File v1_1 = new java.io.File(this.getRealPathFromURI(p7));
        org.json.JSONObject v2_1 = new org.json.JSONObject();
        try {
            v2_1.put("name", v1_1.getName());
            v2_1.put("fullPath", v1_1.getAbsolutePath());
            v2_1.put("type", com.phonegap.FileUtils.getMimeType(v1_1.getAbsolutePath()));
            v2_1.put("lastModifiedDate", v1_1.lastModified());
            v2_1.put("size", v1_1.length());
        } catch (org.json.JSONException v3_5) {
            v3_5.printStackTrace();
        }
        return v2_1;
    }

    private org.json.JSONObject getAudioVideoData(String p5, org.json.JSONObject p6, boolean p7)
    {
        android.media.MediaPlayer v1_1 = new android.media.MediaPlayer();
        try {
            v1_1.setDataSource(p5);
            v1_1.prepare();
            p6.put("duration", v1_1.getDuration());
        } catch (String v2_1) {
            android.util.Log.d("Capture", "Error: loading video file");
            return p6;
        }
        if (!p7) {
            return p6;
        } else {
            p6.put("height", v1_1.getVideoHeight());
            p6.put("width", v1_1.getVideoWidth());
            return p6;
        }
    }

    private org.json.JSONObject getFormatData(String p5, String p6)
    {
        org.json.JSONObject v1_1 = new org.json.JSONObject();
        try {
            v1_1.put("height", 0);
            v1_1.put("width", 0);
            v1_1.put("bitrate", 0);
            v1_1.put("duration", 0);
            v1_1.put("codecs", "");
        } catch (int v2_11) {
            android.util.Log.d("Capture", "Error: setting media file data object");
            return v1_1;
        }
        if (!p6.equals("image/jpeg")) {
            if (!p5.endsWith("audio/3gpp")) {
                if (!p6.equals("video/3gpp")) {
                    return v1_1;
                } else {
                    v1_1 = this.getAudioVideoData(p5, v1_1, 1);
                    return v1_1;
                }
            } else {
                v1_1 = this.getAudioVideoData(p5, v1_1, 0);
                return v1_1;
            }
        } else {
            v1_1 = this.getImageData(p5, v1_1);
            return v1_1;
        }
    }

    private org.json.JSONObject getImageData(String p4, org.json.JSONObject p5)
    {
        android.graphics.Bitmap v0 = android.graphics.BitmapFactory.decodeFile(p4);
        p5.put("height", v0.getHeight());
        p5.put("width", v0.getWidth());
        return p5;
    }

    private String getRealPathFromURI(android.net.Uri p9)
    {
        String[] v2 = new String[1];
        v2[0] = "_data";
        android.database.Cursor v7 = this.ctx.managedQuery(p9, v2, 0, 0, 0);
        int v6 = v7.getColumnIndexOrThrow("_data");
        v7.moveToFirst();
        return v7.getString(v6);
    }

    public com.phonegap.api.PluginResult execute(String p12, org.json.JSONArray p13, String p14)
    {
        this.callbackId = p14;
        this.limit = 1;
        this.duration = 0;
        this.results = new org.json.JSONArray();
        org.json.JSONObject v2 = p13.optJSONObject(0);
        if (v2 != null) {
            this.limit = v2.optLong("limit", 1);
            this.duration = v2.optDouble("duration", 0);
        }
        double v4_14;
        if (!p12.equals("getFormatData")) {
            if (!p12.equals("captureAudio")) {
                if (!p12.equals("captureImage")) {
                    if (p12.equals("captureVideo")) {
                        this.captureVideo(this.duration);
                    }
                } else {
                    this.captureImage();
                }
            } else {
                this.captureAudio();
            }
            com.phonegap.api.PluginResult v3_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.NO_RESULT);
            v3_1.setKeepCallback(1);
            v4_14 = v3_1;
        } else {
            try {
                v4_14 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, this.getFormatData(p13.getString(0), p13.getString(1)));
            } catch (double v4_18) {
                v4_14 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.ERROR);
            }
        }
        return v4_14;
    }

    public void fail(String p3)
    {
        this.error(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.ERROR, p3), this.callbackId);
        return;
    }

    public void onActivityResult(int p12, int p13, android.content.Intent p14)
    {
        if (p13 != -1) {
            if (p13 != 0) {
                if (this.results.length() <= 0) {
                    this.fail("Did not complete!");
                } else {
                    this.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, this.results, "navigator.device.capture._castMediaFile"), this.callbackId);
                }
            } else {
                if (this.results.length() <= 0) {
                    this.fail("Canceled.");
                } else {
                    this.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, this.results, "navigator.device.capture._castMediaFile"), this.callbackId);
                }
            }
        } else {
            if (p12 != 0) {
                if (p12 != 1) {
                    if (p12 == 2) {
                        this.results.put(this.createMediaFile(p14.getData()));
                        if (((long) this.results.length()) < this.limit) {
                            this.captureVideo(this.duration);
                        } else {
                            this.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, this.results, "navigator.device.capture._castMediaFile"), this.callbackId);
                        }
                    }
                } else {
                    try {
                        int v0 = android.provider.MediaStore$Images$Media.getBitmap(this.ctx.getContentResolver(), this.imageUri);
                        android.content.ContentValues v6_1 = new android.content.ContentValues();
                        v6_1.put("mime_type", "image/jpeg");
                        try {
                            android.net.Uri v5 = this.ctx.getContentResolver().insert(android.provider.MediaStore$Images$Media.EXTERNAL_CONTENT_URI, v6_1);
                        } catch (String v7_41) {
                            System.out.println("Can\'t write to external media storage.");
                            try {
                                v5 = this.ctx.getContentResolver().insert(android.provider.MediaStore$Images$Media.INTERNAL_CONTENT_URI, v6_1);
                            } catch (String v7_45) {
                                System.out.println("Can\'t write to internal media storage.");
                                this.fail("Error capturing image - no media storage found.");
                            }
                        }
                        java.io.OutputStream v4 = this.ctx.getContentResolver().openOutputStream(v5);
                        v0.compress(android.graphics.Bitmap$CompressFormat.JPEG, 100, v4);
                        v4.close();
                        v0.recycle();
                        System.gc();
                        this.results.put(this.createMediaFile(v5));
                        if (((long) this.results.length()) < this.limit) {
                            this.captureImage();
                        } else {
                            this.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, this.results, "navigator.device.capture._castMediaFile"), this.callbackId);
                        }
                    } catch (String v7_6) {
                        v7_6.printStackTrace();
                        this.fail("Error capturing image.");
                    }
                }
            } else {
                this.results.put(this.createMediaFile(p14.getData()));
                if (((long) this.results.length()) < this.limit) {
                    this.captureAudio();
                } else {
                    this.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, this.results, "navigator.device.capture._castMediaFile"), this.callbackId);
                }
            }
        }
        return;
    }
}
