package com.phonegap;
public class DirectoryManager {
    private static final String LOG_TAG = "DirectoryManager";

    public DirectoryManager()
    {
        return;
    }

    private static java.io.File constructFilePaths(String p3, String p4)
    {
        java.io.File v0_0;
        if (!p4.startsWith(p3)) {
            v0_0 = new java.io.File(new StringBuilder().append(p3).append("/").append(p4).toString());
        } else {
            v0_0 = new java.io.File(p4);
        }
        return v0_0;
    }

    protected static long getFreeDiskSpace()
    {
        long v10_5;
        if (!android.os.Environment.getExternalStorageState().equals("mounted")) {
            v10_5 = -1;
        } else {
            try {
                android.os.StatFs v8_1 = new android.os.StatFs(android.os.Environment.getExternalStorageDirectory().getPath());
                long v5 = ((((long) v8_1.getAvailableBlocks()) * ((long) v8_1.getBlockSize())) / 1024);
            } catch (long v10_4) {
                v10_4.printStackTrace();
            }
            v10_5 = v5;
        }
        return v10_5;
    }

    protected static boolean testFileExists(String p4)
    {
        if ((!com.phonegap.DirectoryManager.testSaveLocationExists()) || (p4.equals(""))) {
            boolean v2 = 0;
        } else {
            v2 = com.phonegap.DirectoryManager.constructFilePaths(android.os.Environment.getExternalStorageDirectory().toString(), p4).exists();
        }
        return v2;
    }

    protected static boolean testSaveLocationExists()
    {
        int v1;
        if (!android.os.Environment.getExternalStorageState().equals("mounted")) {
            v1 = 0;
        } else {
            v1 = 1;
        }
        return v1;
    }
}
