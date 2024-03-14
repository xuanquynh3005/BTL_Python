package com.phonegap;
public class CameraLauncher extends com.phonegap.api.Plugin {
    private static final int CAMERA = 1;
    private static final int DATA_URL = 0;
    private static final int FILE_URI = 1;
    private static final int PHOTOLIBRARY = 0;
    private static final int SAVEDPHOTOALBUM = 2;
    public String callbackId;
    private android.net.Uri imageUri;
    private int mQuality;

    public CameraLauncher()
    {
        return;
    }

    public com.phonegap.api.PluginResult execute(String p10, org.json.JSONArray p11, String p12)
    {
        this.callbackId = p12;
        try {
            int v6_0;
            if (!p10.equals("takePicture")) {
                v6_0 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, "");
            } else {
                int v0 = 0;
                if (p11.length() > 1) {
                    v0 = p11.getInt(1);
                }
                int v4 = 1;
                if (p11.length() > 2) {
                    v4 = p11.getInt(2);
                }
                if (v4 != 1) {
                    if ((v4 == 0) || (v4 == 2)) {
                        this.getImage(p11.getInt(0), v4, v0);
                    }
                } else {
                    this.takePicture(p11.getInt(0), v0);
                }
                com.phonegap.api.PluginResult v2_1 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.NO_RESULT);
                v2_1.setKeepCallback(1);
                v6_0 = v2_1;
            }
        } catch (int v6_11) {
            v6_11.printStackTrace();
            v6_0 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v6_0;
    }

    public void failPicture(String p3)
    {
        this.error(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.ERROR, p3), this.callbackId);
        return;
    }

    public void getImage(int p5, int p6, int p7)
    {
        this.mQuality = p5;
        android.content.Intent v0_1 = new android.content.Intent();
        v0_1.setType("image/*");
        v0_1.setAction("android.intent.action.GET_CONTENT");
        v0_1.addCategory("android.intent.category.OPENABLE");
        this.ctx.startActivityForResult(this, android.content.Intent.createChooser(v0_1, new String("Get Picture")), ((((p6 + 1) * 16) + p7) + 1));
        return;
    }

    public void onActivityResult(int p13, int p14, android.content.Intent p15)
    {
        int v6 = ((p13 / 16) - 1);
        int v1 = ((p13 % 16) - 1);
        try {
            if (v6 != 1) {
                if ((v6 == 0) || (v6 == 2)) {
                    if (p14 != -1) {
                        if (p14 != 0) {
                            this.failPicture("Selection did not complete!");
                        } else {
                            this.failPicture("Selection cancelled.");
                        }
                    } else {
                        android.net.Uri v7_0 = p15.getData();
                        android.content.ContentResolver vtmp8 = this.ctx.getContentResolver();
                        if (v1 != 0) {
                            if (v1 == 1) {
                                this.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, v7_0.toString()), this.callbackId);
                            }
                        } else {
                            try {
                                int v0_0 = android.graphics.BitmapFactory.decodeStream(vtmp8.openInputStream(v7_0));
                                this.processPicture(v0_0);
                                v0_0.recycle();
                                System.gc();
                            } catch (String v9_13) {
                                v9_13.printStackTrace();
                                this.failPicture("Error retrieving image.");
                            }
                        }
                    }
                }
            } else {
                if (p14 != -1) {
                    if (p14 != 0) {
                        this.failPicture("Did not complete!");
                    } else {
                        this.failPicture("Camera cancelled.");
                    }
                } else {
                    int v0_1 = android.provider.MediaStore$Images$Media.getBitmap(this.ctx.getContentResolver(), this.imageUri);
                    if (v1 != 0) {
                        if (v1 == 1) {
                            android.content.ContentValues v8_1 = new android.content.ContentValues();
                            v8_1.put("mime_type", "image/jpeg");
                            try {
                                android.net.Uri v7_2 = this.ctx.getContentResolver().insert(android.provider.MediaStore$Images$Media.EXTERNAL_CONTENT_URI, v8_1);
                            } catch (String v9_27) {
                                System.out.println("Can\'t write to external media storage.");
                                try {
                                    v7_2 = this.ctx.getContentResolver().insert(android.provider.MediaStore$Images$Media.INTERNAL_CONTENT_URI, v8_1);
                                } catch (String v9_31) {
                                    System.out.println("Can\'t write to internal media storage.");
                                    this.failPicture("Error capturing image - no media storage found.");
                                    return;
                                }
                            }
                            java.io.OutputStream v4 = this.ctx.getContentResolver().openOutputStream(v7_2);
                            v0_1.compress(android.graphics.Bitmap$CompressFormat.JPEG, this.mQuality, v4);
                            v4.close();
                            this.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, v7_2.toString()), this.callbackId);
                        }
                    } else {
                        this.processPicture(v0_1);
                    }
                    v0_1.recycle();
                    System.gc();
                }
            }
        } catch (String v9_4) {
            v9_4.printStackTrace();
            this.failPicture("Error capturing image.");
        }
        return;
    }

    public void processPicture(android.graphics.Bitmap p8)
    {
        int v2_1 = new java.io.ByteArrayOutputStream();
        try {
            if (p8.compress(android.graphics.Bitmap$CompressFormat.JPEG, this.mQuality, v2_1)) {
                this.success(new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, new String(org.apache.commons.codec.binary.Base64.encodeBase64(v2_1.toByteArray()))), this.callbackId);
                int v4 = 0;
            }
        } catch (com.phonegap.api.PluginResult v5_4) {
            this.failPicture("Error compressing image.");
        }
        return;
    }

    public void takePicture(int p5, int p6)
    {
        this.mQuality = p5;
        android.content.Intent v0_1 = new android.content.Intent("android.media.action.IMAGE_CAPTURE");
        java.io.File v1_1 = new java.io.File(android.os.Environment.getExternalStorageDirectory(), "Pic.jpg");
        v0_1.putExtra("output", android.net.Uri.fromFile(v1_1));
        this.imageUri = android.net.Uri.fromFile(v1_1);
        this.ctx.startActivityForResult(this, v0_1, ((p6 + 32) + 1));
        return;
    }
}
