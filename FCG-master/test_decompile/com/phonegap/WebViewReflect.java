package com.phonegap;
public class WebViewReflect {
    private static reflect.Method mWebSettings_setDatabaseEnabled;
    private static reflect.Method mWebSettings_setDatabasePath;
    private static reflect.Method mWebSettings_setDomStorageEnabled;
    private static reflect.Method mWebSettings_setGeolocationEnabled;

    static WebViewReflect()
    {
        com.phonegap.WebViewReflect.checkCompatibility();
        return;
    }

    public WebViewReflect()
    {
        return;
    }

    public static void checkCompatibility()
    {
        try {
            Class[] v2_5 = new Class[1];
            v2_5[0] = Boolean.TYPE;
            com.phonegap.WebViewReflect.mWebSettings_setDatabaseEnabled = android.webkit.WebSettings.getMethod("setDatabaseEnabled", v2_5);
            Class[] v2_1 = new Class[1];
            v2_1[0] = String;
            com.phonegap.WebViewReflect.mWebSettings_setDatabasePath = android.webkit.WebSettings.getMethod("setDatabasePath", v2_1);
            Class[] v2_4 = new Class[1];
            v2_4[0] = Boolean.TYPE;
            com.phonegap.WebViewReflect.mWebSettings_setDomStorageEnabled = android.webkit.WebSettings.getMethod("setDomStorageEnabled", v2_4);
            Class[] v2_7 = new Class[1];
            v2_7[0] = Boolean.TYPE;
            com.phonegap.WebViewReflect.mWebSettings_setGeolocationEnabled = android.webkit.WebSettings.getMethod("setGeolocationEnabled", v2_7);
        } catch (NoSuchMethodException v0) {
        }
        return;
    }

    private static void setDatabaseEnabled(boolean p6)
    {
        try {
            String v5_2 = new Object[0];
            com.phonegap.WebViewReflect.mWebSettings_setDatabaseEnabled.invoke(Boolean.valueOf(p6), v5_2);
        } catch (RuntimeException v3_7) {
            System.err.println(new StringBuilder().append("unexpected ").append(v3_7).toString());
        } catch (RuntimeException v3_1) {
            reflect.InvocationTargetException v2 = v3_1;
            Error v0_0 = v2.getCause();
            if (!(v0_0 instanceof java.io.IOException)) {
                if (!(v0_0 instanceof RuntimeException)) {
                    if (!(v0_0 instanceof Error)) {
                        throw new RuntimeException(v2);
                    } else {
                        throw ((Error) v0_0);
                    }
                } else {
                    throw ((RuntimeException) v0_0);
                }
            } else {
                throw ((java.io.IOException) v0_0);
            }
        }
        return;
    }

    public static void setDomStorage(android.webkit.WebSettings p5)
    {
        if (com.phonegap.WebViewReflect.mWebSettings_setDomStorageEnabled != null) {
            try {
                Object[] v2_1 = new Object[1];
                v2_1[0] = Boolean.valueOf(1);
                com.phonegap.WebViewReflect.mWebSettings_setDomStorageEnabled.invoke(p5, v2_1);
            } catch (reflect.InvocationTargetException v1_3) {
                v1_3.printStackTrace();
            } catch (reflect.InvocationTargetException v1_2) {
                v1_2.printStackTrace();
            } catch (reflect.InvocationTargetException v1_1) {
                v1_1.printStackTrace();
            }
        }
        return;
    }

    public static void setGeolocationEnabled(android.webkit.WebSettings p5, boolean p6)
    {
        if (com.phonegap.WebViewReflect.mWebSettings_setGeolocationEnabled == null) {
            System.out.println("Native Geolocation not supported - we\'re ok");
        } else {
            try {
                String v2_2 = new Object[1];
                v2_2[0] = Boolean.valueOf(p6);
                com.phonegap.WebViewReflect.mWebSettings_setGeolocationEnabled.invoke(p5, v2_2);
            } catch (reflect.InvocationTargetException v1_3) {
                v1_3.printStackTrace();
            } catch (reflect.InvocationTargetException v1_2) {
                v1_2.printStackTrace();
            } catch (reflect.InvocationTargetException v1_1) {
                v1_1.printStackTrace();
            }
        }
        return;
    }

    public static void setStorage(android.webkit.WebSettings p5, boolean p6, String p7)
    {
        if (com.phonegap.WebViewReflect.mWebSettings_setDatabaseEnabled != null) {
            try {
                Object[] v2_3 = new Object[1];
                v2_3[0] = Boolean.valueOf(p6);
                com.phonegap.WebViewReflect.mWebSettings_setDatabaseEnabled.invoke(p5, v2_3);
                Object[] v2_1 = new Object[1];
                v2_1[0] = p7;
                com.phonegap.WebViewReflect.mWebSettings_setDatabasePath.invoke(p5, v2_1);
            } catch (reflect.InvocationTargetException v1_5) {
                v1_5.printStackTrace();
            } catch (reflect.InvocationTargetException v1_3) {
                v1_3.printStackTrace();
            } catch (reflect.InvocationTargetException v1_2) {
                v1_2.printStackTrace();
            }
        }
        return;
    }
}
