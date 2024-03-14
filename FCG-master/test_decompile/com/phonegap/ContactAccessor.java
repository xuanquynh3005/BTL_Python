package com.phonegap;
public abstract class ContactAccessor {
    private static com.phonegap.ContactAccessor sInstance;
    protected final String LOG_TAG;
    protected android.app.Activity mApp;
    protected android.webkit.WebView mView;

    public ContactAccessor()
    {
        this.LOG_TAG = "ContactsAccessor";
        return;
    }

    public static com.phonegap.ContactAccessor getInstance(android.webkit.WebView p7, android.app.Activity p8)
    {
        if (com.phonegap.ContactAccessor.sInstance == null) {
            String v1;
            if (!android.os.Build$VERSION.RELEASE.startsWith("1.")) {
                v1 = "com.phonegap.ContactAccessorSdk5";
            } else {
                v1 = "com.phonegap.ContactAccessorSdk3_4";
            }
            try {
                Class v2 = Class.forName(v1).asSubclass(com.phonegap.ContactAccessor);
                IllegalStateException v4_3 = new Class[2];
                v4_3[0] = Class.forName("android.webkit.WebView");
                v4_3[1] = Class.forName("android.app.Activity");
                reflect.Constructor v0 = v2.getConstructor(v4_3);
                IllegalStateException v4_6 = new Object[2];
                v4_6[0] = p7;
                v4_6[1] = p8;
                com.phonegap.ContactAccessor.sInstance = ((com.phonegap.ContactAccessor) v0.newInstance(v4_6));
            } catch (IllegalStateException v4_7) {
                throw new IllegalStateException(v4_7);
            }
        }
        return com.phonegap.ContactAccessor.sInstance;
    }

    protected java.util.HashMap buildPopulationSet(org.json.JSONArray p7)
    {
        java.util.HashMap v3_1 = new java.util.HashMap();
        int v1 = 0;
        try {
            while (v1 < p7.length()) {
                String v2 = p7.getString(v1);
                if (!v2.startsWith("displayName")) {
                    if (!v2.startsWith("name")) {
                        if (!v2.startsWith("nickname")) {
                            if (!v2.startsWith("phoneNumbers")) {
                                if (!v2.startsWith("emails")) {
                                    if (!v2.startsWith("addresses")) {
                                        if (!v2.startsWith("ims")) {
                                            if (!v2.startsWith("organizations")) {
                                                if (!v2.startsWith("birthday")) {
                                                    if (!v2.startsWith("anniversary")) {
                                                        if (!v2.startsWith("note")) {
                                                            if (!v2.startsWith("relationships")) {
                                                                if (!v2.startsWith("urls")) {
                                                                    if (v2.startsWith("photos")) {
                                                                        v3_1.put("photos", Boolean.valueOf(1));
                                                                    }
                                                                } else {
                                                                    v3_1.put("urls", Boolean.valueOf(1));
                                                                }
                                                            } else {
                                                                v3_1.put("relationships", Boolean.valueOf(1));
                                                            }
                                                        } else {
                                                            v3_1.put("note", Boolean.valueOf(1));
                                                        }
                                                    } else {
                                                        v3_1.put("anniversary", Boolean.valueOf(1));
                                                    }
                                                } else {
                                                    v3_1.put("birthday", Boolean.valueOf(1));
                                                }
                                            } else {
                                                v3_1.put("organizations", Boolean.valueOf(1));
                                            }
                                        } else {
                                            v3_1.put("ims", Boolean.valueOf(1));
                                        }
                                    } else {
                                        v3_1.put("addresses", Boolean.valueOf(1));
                                    }
                                } else {
                                    v3_1.put("emails", Boolean.valueOf(1));
                                }
                            } else {
                                v3_1.put("phoneNumbers", Boolean.valueOf(1));
                            }
                        } else {
                            v3_1.put("nickname", Boolean.valueOf(1));
                        }
                    } else {
                        v3_1.put("name", Boolean.valueOf(1));
                    }
                } else {
                    v3_1.put("displayName", Boolean.valueOf(1));
                }
                v1++;
            }
        } catch (String v4_8) {
            org.json.JSONException v0 = v4_8;
            android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
        }
        return v3_1;
    }

    protected String getJsonString(org.json.JSONObject p6, String p7)
    {
        int v1 = 0;
        if (p6 != null) {
            try {
                v1 = p6.getString(p7);
            } catch (String v2_4) {
                android.util.Log.d("ContactsAccessor", new StringBuilder().append("Could not get = ").append(v2_4.getMessage()).toString());
            }
            if (v1.equals("null")) {
                android.util.Log.d("ContactsAccessor", new StringBuilder().append(p7).append(" is string called \'null\'").toString());
                v1 = 0;
            }
        }
        return v1;
    }

    protected boolean isRequired(String p3, java.util.HashMap p4)
    {
        boolean v1;
        Boolean v0_1 = ((Boolean) p4.get(p3));
        if (v0_1 != null) {
            v1 = v0_1.booleanValue();
        } else {
            v1 = 0;
        }
        return v1;
    }

    public abstract boolean remove();

    public abstract boolean save();

    public abstract org.json.JSONArray search();
}
