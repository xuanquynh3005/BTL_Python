package com.phonegap;
public class ContactAccessorSdk5 extends com.phonegap.ContactAccessor {
    private static final String EMAIL_REGEXP = ".+@.+\\.+.+";
    private static final long MAX_PHOTO_SIZE = 1048576;
    private static final java.util.Map dbMap;

    static ContactAccessorSdk5()
    {
        com.phonegap.ContactAccessorSdk5.dbMap = new java.util.HashMap();
        com.phonegap.ContactAccessorSdk5.dbMap.put("id", "_id");
        com.phonegap.ContactAccessorSdk5.dbMap.put("displayName", "display_name");
        com.phonegap.ContactAccessorSdk5.dbMap.put("name", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("name.formatted", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("name.familyName", "data3");
        com.phonegap.ContactAccessorSdk5.dbMap.put("name.givenName", "data2");
        com.phonegap.ContactAccessorSdk5.dbMap.put("name.middleName", "data5");
        com.phonegap.ContactAccessorSdk5.dbMap.put("name.honorificPrefix", "data4");
        com.phonegap.ContactAccessorSdk5.dbMap.put("name.honorificSuffix", "data6");
        com.phonegap.ContactAccessorSdk5.dbMap.put("nickname", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("phoneNumbers", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("phoneNumbers.value", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("emails", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("emails.value", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("addresses", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("addresses.formatted", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("addresses.streetAddress", "data4");
        com.phonegap.ContactAccessorSdk5.dbMap.put("addresses.locality", "data7");
        com.phonegap.ContactAccessorSdk5.dbMap.put("addresses.region", "data8");
        com.phonegap.ContactAccessorSdk5.dbMap.put("addresses.postalCode", "data9");
        com.phonegap.ContactAccessorSdk5.dbMap.put("addresses.country", "data10");
        com.phonegap.ContactAccessorSdk5.dbMap.put("ims", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("ims.value", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("organizations", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("organizations.name", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("organizations.department", "data5");
        com.phonegap.ContactAccessorSdk5.dbMap.put("organizations.title", "data4");
        com.phonegap.ContactAccessorSdk5.dbMap.put("birthday", "vnd.android.cursor.item/contact_event");
        com.phonegap.ContactAccessorSdk5.dbMap.put("note", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("photos.value", "vnd.android.cursor.item/photo");
        com.phonegap.ContactAccessorSdk5.dbMap.put("urls", "data1");
        com.phonegap.ContactAccessorSdk5.dbMap.put("urls.value", "data1");
        return;
    }

    public ContactAccessorSdk5(android.webkit.WebView p1, android.app.Activity p2)
    {
        this.mApp = p2;
        this.mView = p1;
        return;
    }

    private org.json.JSONObject addressQuery(android.database.Cursor p5)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        try {
            v0_1.put("id", p5.getString(p5.getColumnIndex("_id")));
            v0_1.put("formatted", p5.getString(p5.getColumnIndex("data1")));
            v0_1.put("streetAddress", p5.getString(p5.getColumnIndex("data4")));
            v0_1.put("locality", p5.getString(p5.getColumnIndex("data7")));
            v0_1.put("region", p5.getString(p5.getColumnIndex("data8")));
            v0_1.put("postalCode", p5.getString(p5.getColumnIndex("data9")));
            v0_1.put("country", p5.getString(p5.getColumnIndex("data10")));
        } catch (String v2_6) {
            org.json.JSONException v1 = v2_6;
            android.util.Log.e("ContactsAccessor", v1.getMessage(), v1);
        }
        return v0_1;
    }

    private com.phonegap.ContactAccessor$WhereOptions buildIdClause(java.util.Set p6, String p7)
    {
        com.phonegap.ContactAccessor$WhereOptions v2_1 = new com.phonegap.ContactAccessor$WhereOptions(this);
        if (!p7.equals("%")) {
            java.util.Iterator v1 = p6.iterator();
            StringBuffer v0_0 = new StringBuffer("(");
            while (v1.hasNext()) {
                v0_0.append(new StringBuilder().append("\'").append(((String) v1.next())).append("\'").toString());
                if (v1.hasNext()) {
                    v0_0.append(",");
                }
            }
            v0_0.append(")");
            v2_1.setWhere(new StringBuilder().append("contact_id IN ").append(v0_0.toString()).toString());
            v2_1.setWhereArgs(0);
        } else {
            v2_1.setWhere("(contact_id LIKE ? )");
            String v3_20 = new String[1];
            v3_20[0] = p7;
            v2_1.setWhereArgs(v3_20);
        }
        return v2_1;
    }

    private com.phonegap.ContactAccessor$WhereOptions buildWhereClause(org.json.JSONArray p12, String p13)
    {
        java.util.ArrayList v6_1 = new java.util.ArrayList();
        java.util.ArrayList v7_1 = new java.util.ArrayList();
        com.phonegap.ContactAccessor$WhereOptions v3_1 = new com.phonegap.ContactAccessor$WhereOptions(this);
        if (!"%".equals(p13)) {
            int v1_0 = 0;
            try {
                while (v1_0 < p12.length()) {
                    String v2 = p12.getString(v1_0);
                    if (!v2.startsWith("displayName")) {
                        if (!v2.startsWith("name")) {
                            if (!v2.startsWith("nickname")) {
                                if (!v2.startsWith("phoneNumbers")) {
                                    if (!v2.startsWith("emails")) {
                                        if (!v2.startsWith("addresses")) {
                                            if (!v2.startsWith("ims")) {
                                                if (!v2.startsWith("organizations")) {
                                                    if (!v2.startsWith("note")) {
                                                        if (v2.startsWith("urls")) {
                                                            v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                                                            v7_1.add(p13);
                                                            v7_1.add("vnd.android.cursor.item/website");
                                                        }
                                                    } else {
                                                        v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                                                        v7_1.add(p13);
                                                        v7_1.add("vnd.android.cursor.item/note");
                                                    }
                                                } else {
                                                    v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                                                    v7_1.add(p13);
                                                    v7_1.add("vnd.android.cursor.item/organization");
                                                }
                                            } else {
                                                v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                                                v7_1.add(p13);
                                                v7_1.add("vnd.android.cursor.item/im");
                                            }
                                        } else {
                                            v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                                            v7_1.add(p13);
                                            v7_1.add("vnd.android.cursor.item/postal-address_v2");
                                        }
                                    } else {
                                        v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                                        v7_1.add(p13);
                                        v7_1.add("vnd.android.cursor.item/email_v2");
                                    }
                                } else {
                                    v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                                    v7_1.add(p13);
                                    v7_1.add("vnd.android.cursor.item/phone_v2");
                                }
                            } else {
                                v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                                v7_1.add(p13);
                                v7_1.add("vnd.android.cursor.item/nickname");
                            }
                        } else {
                            v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? AND ").append("mimetype").append(" = ? )").toString());
                            v7_1.add(p13);
                            v7_1.add("vnd.android.cursor.item/name");
                        }
                    } else {
                        v6_1.add(new StringBuilder().append("(").append(((String) com.phonegap.ContactAccessorSdk5.dbMap.get(v2))).append(" LIKE ? )").toString());
                        v7_1.add(p13);
                    }
                    v1_0++;
                }
            } catch (String v8_81) {
                org.json.JSONException v0 = v8_81;
                android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
            }
            StringBuffer v4_1 = new StringBuffer();
            int v1_1 = 0;
            while (v1_1 < v6_1.size()) {
                v4_1.append(((String) v6_1.get(v1_1)));
                if (v1_1 != (v6_1.size() - 1)) {
                    v4_1.append(" OR ");
                }
                v1_1++;
            }
            v3_1.setWhere(v4_1.toString());
            String[] v5 = new String[v7_1.size()];
            int v1_2 = 0;
            while (v1_2 < v7_1.size()) {
                v5[v1_2] = ((String) v7_1.get(v1_2));
                v1_2++;
            }
            v3_1.setWhereArgs(v5);
        } else {
            v3_1.setWhere("(display_name LIKE ? )");
            String v8_93 = new String[1];
            v8_93[0] = p13;
            v3_1.setWhereArgs(v8_93);
        }
        return v3_1;
    }

    private boolean createNewContact(org.json.JSONObject p30, android.accounts.Account p31)
    {
        java.util.ArrayList v16_1 = new java.util.ArrayList();
        v16_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$RawContacts.CONTENT_URI).withValue("account_type", p31.type).withValue("account_name", p31.name).build());
        try {
            org.json.JSONObject v13 = p30.optJSONObject("name");
            String v6 = p30.getString("displayName");
        } catch (String v26_1) {
            android.util.Log.d("ContactsAccessor", "Could not get name object");
            try {
                org.json.JSONArray v20 = p30.getJSONArray("phoneNumbers");
            } catch (String v26_5) {
                android.util.Log.d("ContactsAccessor", "Could not get phone numbers");
                try {
                    org.json.JSONArray v9 = p30.getJSONArray("emails");
                } catch (String v26_9) {
                    android.util.Log.d("ContactsAccessor", "Could not get emails");
                    try {
                        org.json.JSONArray v4 = p30.getJSONArray("addresses");
                    } catch (String v26_13) {
                        android.util.Log.d("ContactsAccessor", "Could not get addresses");
                        try {
                            org.json.JSONArray v18 = p30.getJSONArray("organizations");
                        } catch (String v26_18) {
                            android.util.Log.d("ContactsAccessor", "Could not get organizations");
                            try {
                                org.json.JSONArray v12 = p30.getJSONArray("ims");
                            } catch (String v26_23) {
                                android.util.Log.d("ContactsAccessor", "Could not get emails");
                                String v15 = this.getJsonString(p30, "note");
                                if (v15 != null) {
                                    v16_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/note").withValue("data1", v15).build());
                                }
                                String v14 = this.getJsonString(p30, "nickname");
                                if (v14 != null) {
                                    v16_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/nickname").withValue("data1", v14).build());
                                }
                                try {
                                    org.json.JSONArray v25 = p30.getJSONArray("websites");
                                } catch (String v26_43) {
                                    android.util.Log.d("ContactsAccessor", "Could not get websites");
                                    String v5 = this.getJsonString(p30, "birthday");
                                    if (v5 != null) {
                                        v16_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/contact_event").withValue("data2", Integer.valueOf(3)).withValue("data1", v5).build());
                                    }
                                    try {
                                        org.json.JSONArray v22 = p30.getJSONArray("photos");
                                    } catch (String v26_56) {
                                        android.util.Log.d("ContactsAccessor", "Could not get photos");
                                        int v23 = 1;
                                        try {
                                            this.mApp.getContentResolver().applyBatch("com.android.contacts", v16_1);
                                        } catch (String v26_63) {
                                            android.content.OperationApplicationException v7_1 = v26_63;
                                            android.util.Log.e("ContactsAccessor", v7_1.getMessage(), v7_1);
                                            v23 = 0;
                                        } catch (String v26_60) {
                                            android.content.OperationApplicationException v7_0 = v26_60;
                                            android.util.Log.e("ContactsAccessor", v7_0.getMessage(), v7_0);
                                            v23 = 0;
                                        }
                                        return v23;
                                    }
                                    if (v22 == null) {
                                    } else {
                                        int v10_6 = 0;
                                        while (v10_6 < v22.length()) {
                                            this.insertPhoto(v16_1, ((org.json.JSONObject) v22.get(v10_6)));
                                            v10_6++;
                                        }
                                    }
                                }
                                if (v25 == null) {
                                } else {
                                    int v10_5 = 0;
                                    while (v10_5 < v25.length()) {
                                        this.insertWebsite(v16_1, ((org.json.JSONObject) v25.get(v10_5)));
                                        v10_5++;
                                    }
                                }
                            }
                            if (v12 == null) {
                            } else {
                                int v10_4 = 0;
                                while (v10_4 < v12.length()) {
                                    this.insertIm(v16_1, ((org.json.JSONObject) v12.get(v10_4)));
                                    v10_4++;
                                }
                            }
                        }
                        if (v18 == null) {
                        } else {
                            int v10_3 = 0;
                            while (v10_3 < v18.length()) {
                                this.insertOrganization(v16_1, ((org.json.JSONObject) v18.get(v10_3)));
                                v10_3++;
                            }
                        }
                    }
                    if (v4 == null) {
                    } else {
                        int v10_2 = 0;
                        while (v10_2 < v4.length()) {
                            this.insertAddress(v16_1, ((org.json.JSONObject) v4.get(v10_2)));
                            v10_2++;
                        }
                    }
                }
                if (v9 == null) {
                } else {
                    int v10_1 = 0;
                    while (v10_1 < v9.length()) {
                        this.insertEmail(v16_1, ((org.json.JSONObject) v9.get(v10_1)));
                        v10_1++;
                    }
                }
            }
            if (v20 == null) {
            } else {
                int v10_0 = 0;
                while (v10_0 < v20.length()) {
                    this.insertPhone(v16_1, ((org.json.JSONObject) v20.get(v10_0)));
                    v10_0++;
                }
            }
        }
        if ((v6 == null) && (v13 == null)) {
        } else {
            v16_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", v6).withValue("data3", this.getJsonString(v13, "familyName")).withValue("data5", this.getJsonString(v13, "middleName")).withValue("data2", this.getJsonString(v13, "givenName")).withValue("data4", this.getJsonString(v13, "honorificPrefix")).withValue("data6", this.getJsonString(v13, "honorificSuffix")).build());
        }
    }

    private org.json.JSONObject emailQuery(android.database.Cursor p5)
    {
        org.json.JSONObject v1_1 = new org.json.JSONObject();
        try {
            v1_1.put("id", p5.getString(p5.getColumnIndex("_id")));
            v1_1.put("pref", 0);
            v1_1.put("value", p5.getString(p5.getColumnIndex("data1")));
            v1_1.put("type", this.getContactType(p5.getInt(p5.getColumnIndex("data2"))));
        } catch (String v2_3) {
            org.json.JSONException v0 = v2_3;
            android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
        }
        return v1_1;
    }

    private int getContactType(String p4)
    {
        int v1_8;
        if (p4 == null) {
            v1_8 = 3;
        } else {
            if (!"home".equals(p4.toLowerCase())) {
                if (!"work".equals(p4.toLowerCase())) {
                    if (!"other".equals(p4.toLowerCase())) {
                        if (!"mobile".equals(p4.toLowerCase())) {
                            if (!"custom".equals(p4.toLowerCase())) {
                            } else {
                                v1_8 = 0;
                            }
                        } else {
                            v1_8 = 4;
                        }
                    } else {
                        v1_8 = 3;
                    }
                } else {
                    v1_8 = 2;
                }
            } else {
                v1_8 = 1;
            }
        }
        return v1_8;
    }

    private String getContactType(int p2)
    {
        String v0;
        switch (p2) {
            case 0:
                v0 = "custom";
                break;
            case 1:
                v0 = "home";
                break;
            case 2:
                v0 = "work";
                break;
            case 3:
            default:
                v0 = "other";
                break;
            case 4:
                v0 = "mobile";
                break;
        }
        return v0;
    }

    private java.io.InputStream getPathFromUri(String p4)
    {
        java.io.FileInputStream v2_3;
        if (!p4.startsWith("content:")) {
            if ((!p4.startsWith("http:")) && (!p4.startsWith("file:"))) {
                v2_3 = new java.io.FileInputStream(p4);
            } else {
                v2_3 = new java.net.URL(p4).openStream();
            }
        } else {
            v2_3 = this.mApp.getContentResolver().openInputStream(android.net.Uri.parse(p4));
        }
        return v2_3;
    }

    private int getPhoneType(String p5)
    {
        int v1_12;
        if (!"home".equals(p5.toLowerCase())) {
            if (!"mobile".equals(p5.toLowerCase())) {
                if (!"work".equals(p5.toLowerCase())) {
                    if (!"work fax".equals(p5.toLowerCase())) {
                        if (!"home fax".equals(p5.toLowerCase())) {
                            if (!"fax".equals(p5.toLowerCase())) {
                                if (!"pager".equals(p5.toLowerCase())) {
                                    if (!"other".equals(p5.toLowerCase())) {
                                        if (!"car".equals(p5.toLowerCase())) {
                                            if (!"company main".equals(p5.toLowerCase())) {
                                                if (!"isdn".equals(p5.toLowerCase())) {
                                                    if (!"main".equals(p5.toLowerCase())) {
                                                        if (!"other fax".equals(p5.toLowerCase())) {
                                                            if (!"radio".equals(p5.toLowerCase())) {
                                                                if (!"telex".equals(p5.toLowerCase())) {
                                                                    if (!"work mobile".equals(p5.toLowerCase())) {
                                                                        if (!"work pager".equals(p5.toLowerCase())) {
                                                                            if (!"assistant".equals(p5.toLowerCase())) {
                                                                                if (!"mms".equals(p5.toLowerCase())) {
                                                                                    if (!"callback".equals(p5.toLowerCase())) {
                                                                                        if (!"tty ttd".equals(p5.toLowerCase())) {
                                                                                            if (!"custom".equals(p5.toLowerCase())) {
                                                                                                v1_12 = 7;
                                                                                            } else {
                                                                                                v1_12 = 0;
                                                                                            }
                                                                                        } else {
                                                                                            v1_12 = 16;
                                                                                        }
                                                                                    } else {
                                                                                        v1_12 = 8;
                                                                                    }
                                                                                } else {
                                                                                    v1_12 = 20;
                                                                                }
                                                                            } else {
                                                                                v1_12 = 19;
                                                                            }
                                                                        } else {
                                                                            v1_12 = 18;
                                                                        }
                                                                    } else {
                                                                        v1_12 = 17;
                                                                    }
                                                                } else {
                                                                    v1_12 = 15;
                                                                }
                                                            } else {
                                                                v1_12 = 14;
                                                            }
                                                        } else {
                                                            v1_12 = 13;
                                                        }
                                                    } else {
                                                        v1_12 = 12;
                                                    }
                                                } else {
                                                    v1_12 = 11;
                                                }
                                            } else {
                                                v1_12 = 10;
                                            }
                                        } else {
                                            v1_12 = 9;
                                        }
                                    } else {
                                        v1_12 = 7;
                                    }
                                } else {
                                    v1_12 = 6;
                                }
                            } else {
                                v1_12 = 4;
                            }
                        } else {
                            v1_12 = 5;
                        }
                    } else {
                        v1_12 = 4;
                    }
                } else {
                    v1_12 = 3;
                }
            } else {
                v1_12 = 2;
            }
        } else {
            v1_12 = 1;
        }
        return v1_12;
    }

    private String getPhoneType(int p2)
    {
        String v0;
        switch (p2) {
            case 0:
                v0 = "custom";
                break;
            case 1:
                v0 = "home";
                break;
            case 2:
                v0 = "mobile";
                break;
            case 3:
                v0 = "work";
                break;
            case 4:
                v0 = "work fax";
                break;
            case 5:
                v0 = "home fax";
                break;
            case 6:
                v0 = "pager";
                break;
            case 7:
            case 12:
            default:
                v0 = "other";
                break;
            case 8:
                v0 = "callback";
                break;
            case 9:
                v0 = "car";
                break;
            case 10:
                v0 = "company main";
                break;
            case 11:
                v0 = "isdn";
                break;
            case 13:
                v0 = "other fax";
                break;
            case 14:
                v0 = "radio";
                break;
            case 15:
                v0 = "telex";
                break;
            case 16:
                v0 = "tty tdd";
                break;
            case 17:
                v0 = "work mobile";
                break;
            case 18:
                v0 = "work pager";
                break;
            case 19:
                v0 = "assistant";
                break;
            case 20:
                v0 = "mms";
                break;
        }
        return v0;
    }

    private byte[] getPhotoBytes(String p10)
    {
        java.io.ByteArrayOutputStream v0_1 = new java.io.ByteArrayOutputStream();
        long v5 = 0;
        try {
            byte[] v2 = new byte[8192];
            java.io.InputStream v4 = this.getPathFromUri(p10);
        } catch (long v7_7) {
            java.io.IOException v3_1 = v7_7;
            android.util.Log.e("ContactsAccessor", v3_1.getMessage(), v3_1);
            return v0_1.toByteArray();
        } catch (long v7_5) {
            java.io.IOException v3_0 = v7_5;
            android.util.Log.e("ContactsAccessor", v3_0.getMessage(), v3_0);
            return v0_1.toByteArray();
        }
        while(true) {
            int v1 = v4.read(v2, 0, v2.length);
            if ((v1 == -1) || (v5 > 1048576)) {
                break;
            }
            v0_1.write(v2, 0, v1);
            v5 += ((long) v1);
        }
        v4.close();
        v0_1.flush();
        return v0_1.toByteArray();
    }

    private org.json.JSONObject imQuery(android.database.Cursor p5)
    {
        org.json.JSONObject v1_1 = new org.json.JSONObject();
        try {
            v1_1.put("id", p5.getString(p5.getColumnIndex("_id")));
            v1_1.put("pref", 0);
            v1_1.put("value", p5.getString(p5.getColumnIndex("data1")));
            v1_1.put("type", this.getContactType(p5.getInt(p5.getColumnIndex("data2"))));
        } catch (String v2_3) {
            org.json.JSONException v0 = v2_3;
            android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
        }
        return v1_1;
    }

    private void insertAddress(java.util.ArrayList p4, org.json.JSONObject p5)
    {
        p4.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/postal-address_v2").withValue("data1", this.getJsonString(p5, "formatted")).withValue("data4", this.getJsonString(p5, "streetAddress")).withValue("data7", this.getJsonString(p5, "locality")).withValue("data8", this.getJsonString(p5, "region")).withValue("data9", this.getJsonString(p5, "postalCode")).withValue("data10", this.getJsonString(p5, "country")).build());
        return;
    }

    private void insertEmail(java.util.ArrayList p4, org.json.JSONObject p5)
    {
        p4.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", this.getJsonString(p5, "value")).withValue("data2", Integer.valueOf(this.getPhoneType(this.getJsonString(p5, "type")))).build());
        return;
    }

    private void insertIm(java.util.ArrayList p4, org.json.JSONObject p5)
    {
        p4.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/im").withValue("data1", this.getJsonString(p5, "value")).withValue("data2", Integer.valueOf(this.getContactType(this.getJsonString(p5, "type")))).build());
        return;
    }

    private void insertOrganization(java.util.ArrayList p4, org.json.JSONObject p5)
    {
        p4.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/organization").withValue("data5", this.getJsonString(p5, "department")).withValue("data1", this.getJsonString(p5, "name")).withValue("data4", this.getJsonString(p5, "title")).build());
        return;
    }

    private void insertPhone(java.util.ArrayList p4, org.json.JSONObject p5)
    {
        p4.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", this.getJsonString(p5, "value")).withValue("data2", Integer.valueOf(this.getPhoneType(this.getJsonString(p5, "type")))).build());
        return;
    }

    private void insertPhoto(java.util.ArrayList p5, org.json.JSONObject p6)
    {
        p5.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("is_super_primary", Integer.valueOf(1)).withValue("mimetype", "vnd.android.cursor.item/photo").withValue("data15", this.getPhotoBytes(this.getJsonString(p6, "value"))).build());
        return;
    }

    private void insertWebsite(java.util.ArrayList p4, org.json.JSONObject p5)
    {
        p4.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", this.getJsonString(p5, "value")).withValue("data2", Integer.valueOf(this.getContactType(this.getJsonString(p5, "type")))).build());
        return;
    }

    private boolean modifyContact(String p50, org.json.JSONObject p51, android.accounts.Account p52)
    {
        int v38 = new Integer(this.getJsonString(p51, "rawId")).intValue();
        java.util.ArrayList v28_1 = new java.util.ArrayList();
        v28_1.add(android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$RawContacts.CONTENT_URI).withValue("account_type", p52.type).withValue("account_name", p52.name).build());
        try {
            String v10 = this.getJsonString(p51, "displayName");
            org.json.JSONObject v25 = p51.getJSONObject("name");
        } catch (android.content.ContentProviderOperation v43_52) {
            android.util.Log.d("ContactsAccessor", "Could not get name");
            try {
                org.json.JSONArray v34 = p51.getJSONArray("phoneNumbers");
            } catch (android.content.ContentProviderOperation v43_74) {
                android.util.Log.d("ContactsAccessor", "Could not get phone numbers");
                try {
                    org.json.JSONArray v15 = p51.getJSONArray("emails");
                } catch (android.content.ContentProviderOperation v43_96) {
                    android.util.Log.d("ContactsAccessor", "Could not get emails");
                    try {
                        org.json.JSONArray v5 = p51.getJSONArray("addresses");
                    } catch (android.content.ContentProviderOperation v43_125) {
                        android.util.Log.d("ContactsAccessor", "Could not get addresses");
                        try {
                            org.json.JSONArray v31 = p51.getJSONArray("organizations");
                        } catch (android.content.ContentProviderOperation v43_149) {
                            android.util.Log.d("ContactsAccessor", "Could not get organizations");
                            try {
                                org.json.JSONArray v23 = p51.getJSONArray("ims");
                            } catch (android.content.ContentProviderOperation v43_171) {
                                android.util.Log.d("ContactsAccessor", "Could not get emails");
                                String v27 = this.getJsonString(p51, "note");
                                android.content.ContentProviderOperation v43_175 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                java.util.ArrayList v0_165 = new String[2];
                                Integer v45_59 = v0_165;
                                v45_59[0] = p50;
                                v45_59[1] = "vnd.android.cursor.item/note";
                                v28_1.add(v43_175.withSelection("contact_id=? AND mimetype=?", v45_59).withValue("data1", v27).build());
                                String v26 = this.getJsonString(p51, "nickname");
                                if (v26 != null) {
                                    android.content.ContentProviderOperation v43_182 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                    java.util.ArrayList v0_170 = new String[2];
                                    Integer v45_61 = v0_170;
                                    v45_61[0] = p50;
                                    v45_61[1] = "vnd.android.cursor.item/nickname";
                                    v28_1.add(v43_182.withSelection("contact_id=? AND mimetype=?", v45_61).withValue("data1", v26).build());
                                }
                                try {
                                    org.json.JSONArray v42 = p51.getJSONArray("websites");
                                } catch (android.content.ContentProviderOperation v43_9) {
                                    android.util.Log.d("ContactsAccessor", "Could not get websites");
                                    String v6 = this.getJsonString(p51, "birthday");
                                    if (v6 != null) {
                                        android.content.ContentProviderOperation v43_13 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                        java.util.ArrayList v0_12 = new String[3];
                                        Integer v45_1 = v0_12;
                                        v45_1[0] = p50;
                                        v45_1[1] = "vnd.android.cursor.item/contact_event";
                                        v45_1[2] = new String("3");
                                        v28_1.add(v43_13.withSelection("contact_id=? AND mimetype=? AND data2=?", v45_1).withValue("data2", Integer.valueOf(3)).withValue("data1", v6).build());
                                    }
                                    try {
                                        org.json.JSONArray v37 = p51.getJSONArray("photos");
                                    } catch (android.content.ContentProviderOperation v43_40) {
                                        android.util.Log.d("ContactsAccessor", "Could not get photos");
                                        int v39 = 1;
                                        try {
                                            this.mApp.getContentResolver().applyBatch("com.android.contacts", v28_1);
                                        } catch (android.content.ContentProviderOperation v43_48) {
                                            android.content.OperationApplicationException v11_1 = v43_48;
                                            android.util.Log.e("ContactsAccessor", v11_1.getMessage(), v11_1);
                                            android.util.Log.e("ContactsAccessor", android.util.Log.getStackTraceString(v11_1), v11_1);
                                            v39 = 0;
                                        } catch (android.content.ContentProviderOperation v43_45) {
                                            android.content.OperationApplicationException v11_0 = v43_45;
                                            android.util.Log.e("ContactsAccessor", v11_0.getMessage(), v11_0);
                                            android.util.Log.e("ContactsAccessor", android.util.Log.getStackTraceString(v11_0), v11_0);
                                            v39 = 0;
                                        }
                                        return v39;
                                    }
                                    if (v37 == null) {
                                    } else {
                                        int v20_1 = 0;
                                        while (v20_1 < v37.length()) {
                                            org.json.JSONObject v35_1 = ((org.json.JSONObject) v37.get(v20_1));
                                            String v36 = this.getJsonString(v35_1, "id");
                                            byte[] v8 = this.getPhotoBytes(this.getJsonString(v35_1, "value"));
                                            if (v36 != null) {
                                                android.content.ContentProviderOperation v43_25 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                                java.util.ArrayList v0_23 = new String[2];
                                                Integer v45_5 = v0_23;
                                                v45_5[0] = v36;
                                                v45_5[1] = "vnd.android.cursor.item/photo";
                                                v28_1.add(v43_25.withSelection("_id=? AND mimetype=?", v45_5).withValue("is_super_primary", Integer.valueOf(1)).withValue("data15", v8).build());
                                            } else {
                                                android.content.ContentValues v9_1 = new android.content.ContentValues();
                                                v9_1.put("raw_contact_id", Integer.valueOf(v38));
                                                v9_1.put("mimetype", "vnd.android.cursor.item/photo");
                                                v9_1.put("is_super_primary", Integer.valueOf(1));
                                                v9_1.put("data15", v8);
                                                v28_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValues(v9_1).build());
                                            }
                                            v20_1++;
                                        }
                                    }
                                    int v20_2++;
                                    while (v20_2 < v34.length()) {
                                        org.json.JSONObject v32_1 = ((org.json.JSONObject) v34.get(v20_2));
                                        String v33 = this.getJsonString(v32_1, "id");
                                        if (v33 != null) {
                                            android.content.ContentProviderOperation v43_59 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                            java.util.ArrayList v0_48 = new String[2];
                                            Integer v45_9 = v0_48;
                                            v45_9[0] = v33;
                                            v45_9[1] = "vnd.android.cursor.item/phone_v2";
                                            v28_1.add(v43_59.withSelection("_id=? AND mimetype=?", v45_9).withValue("data1", this.getJsonString(v32_1, "value")).withValue("data2", Integer.valueOf(this.getPhoneType(this.getJsonString(v32_1, "type")))).build());
                                        } else {
                                            android.content.ContentValues v9_3 = new android.content.ContentValues();
                                            v9_3.put("raw_contact_id", Integer.valueOf(v38));
                                            v9_3.put("mimetype", "vnd.android.cursor.item/phone_v2");
                                            v9_3.put("data1", this.getJsonString(v32_1, "value"));
                                            v9_3.put("data2", Integer.valueOf(this.getPhoneType(this.getJsonString(v32_1, "type"))));
                                            v28_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValues(v9_3).build());
                                        }
                                    }
                                }
                                if (v42 == null) {
                                } else {
                                    int v20_0 = 0;
                                    while (v20_0 < v42.length()) {
                                        org.json.JSONObject v40_1 = ((org.json.JSONObject) v42.get(v20_0));
                                        String v41 = this.getJsonString(v40_1, "id");
                                        if (v41 != null) {
                                            android.content.ContentProviderOperation v43_190 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                            java.util.ArrayList v0_179 = new String[2];
                                            Integer v45_63 = v0_179;
                                            v45_63[0] = v41;
                                            v45_63[1] = "vnd.android.cursor.item/website";
                                            v28_1.add(v43_190.withSelection("_id=? AND mimetype=?", v45_63).withValue("data1", this.getJsonString(v40_1, "value")).withValue("data2", Integer.valueOf(this.getContactType(this.getJsonString(v40_1, "type")))).build());
                                        } else {
                                            android.content.ContentValues v9_13 = new android.content.ContentValues();
                                            v9_13.put("raw_contact_id", Integer.valueOf(v38));
                                            v9_13.put("mimetype", "vnd.android.cursor.item/website");
                                            v9_13.put("data1", this.getJsonString(v40_1, "value"));
                                            v9_13.put("data2", Integer.valueOf(this.getContactType(this.getJsonString(v40_1, "type"))));
                                            v28_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValues(v9_13).build());
                                        }
                                        v20_0++;
                                    }
                                }
                            }
                            if (v23 == null) {
                            } else {
                                int v20_6 = 0;
                                while (v20_6 < v23.length()) {
                                    org.json.JSONObject v21_1 = ((org.json.JSONObject) v23.get(v20_6));
                                    String v22 = this.getJsonString(v21_1, "id");
                                    if (v22 != null) {
                                        android.content.ContentProviderOperation v43_156 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                        java.util.ArrayList v0_147 = new String[2];
                                        Integer v45_51 = v0_147;
                                        v45_51[0] = v22;
                                        v45_51[1] = "vnd.android.cursor.item/im";
                                        v28_1.add(v43_156.withSelection("_id=? AND mimetype=?", v45_51).withValue("data1", this.getJsonString(v21_1, "value")).withValue("data2", Integer.valueOf(this.getContactType(this.getJsonString(v21_1, "type")))).build());
                                    } else {
                                        android.content.ContentValues v9_11 = new android.content.ContentValues();
                                        v9_11.put("raw_contact_id", Integer.valueOf(v38));
                                        v9_11.put("mimetype", "vnd.android.cursor.item/im");
                                        v9_11.put("data1", this.getJsonString(v21_1, "value"));
                                        v9_11.put("data2", Integer.valueOf(this.getContactType(this.getJsonString(v21_1, "type"))));
                                        v28_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValues(v9_11).build());
                                    }
                                    v20_6++;
                                }
                            }
                        }
                        if (v31 == null) {
                        } else {
                            int v20_5 = 0;
                            while (v20_5 < v31.length()) {
                                org.json.JSONObject v29_1 = ((org.json.JSONObject) v31.get(v20_5));
                                String v30 = this.getJsonString(v29_1, "id");
                                if (v30 != null) {
                                    android.content.ContentProviderOperation v43_132 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                    java.util.ArrayList v0_125 = new String[2];
                                    Integer v45_43 = v0_125;
                                    v45_43[0] = v30;
                                    v45_43[1] = "vnd.android.cursor.item/organization";
                                    v28_1.add(v43_132.withSelection("_id=? AND mimetype=?", v45_43).withValue("data5", this.getJsonString(v29_1, "department")).withValue("data1", this.getJsonString(v29_1, "name")).withValue("data4", this.getJsonString(v29_1, "title")).build());
                                } else {
                                    android.content.ContentValues v9_9 = new android.content.ContentValues();
                                    v9_9.put("raw_contact_id", Integer.valueOf(v38));
                                    v9_9.put("mimetype", "vnd.android.cursor.item/organization");
                                    v9_9.put("data5", this.getJsonString(v29_1, "department"));
                                    v9_9.put("data1", this.getJsonString(v29_1, "name"));
                                    v9_9.put("data4", this.getJsonString(v29_1, "title"));
                                    v28_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValues(v9_9).build());
                                }
                                v20_5++;
                            }
                        }
                    }
                    if (v5 == null) {
                    } else {
                        int v20_4 = 0;
                        while (v20_4 < v5.length()) {
                            org.json.JSONObject v3_1 = ((org.json.JSONObject) v5.get(v20_4));
                            String v4 = this.getJsonString(v3_1, "id");
                            if (v4 != null) {
                                android.content.ContentProviderOperation v43_102 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                                java.util.ArrayList v0_93 = new String[2];
                                Integer v45_27 = v0_93;
                                v45_27[0] = v4;
                                v45_27[1] = "vnd.android.cursor.item/postal-address_v2";
                                v28_1.add(v43_102.withSelection("_id=? AND mimetype=?", v45_27).withValue("data1", this.getJsonString(v3_1, "formatted")).withValue("data4", this.getJsonString(v3_1, "streetAddress")).withValue("data7", this.getJsonString(v3_1, "locality")).withValue("data8", this.getJsonString(v3_1, "region")).withValue("data9", this.getJsonString(v3_1, "postalCode")).withValue("data10", this.getJsonString(v3_1, "country")).build());
                            } else {
                                android.content.ContentValues v9_7 = new android.content.ContentValues();
                                v9_7.put("raw_contact_id", Integer.valueOf(v38));
                                v9_7.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
                                v9_7.put("data1", this.getJsonString(v3_1, "formatted"));
                                v9_7.put("data4", this.getJsonString(v3_1, "streetAddress"));
                                v9_7.put("data7", this.getJsonString(v3_1, "locality"));
                                v9_7.put("data8", this.getJsonString(v3_1, "region"));
                                v9_7.put("data9", this.getJsonString(v3_1, "postalCode"));
                                v9_7.put("data10", this.getJsonString(v3_1, "country"));
                                v28_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValues(v9_7).build());
                            }
                            v20_4++;
                        }
                    }
                }
                if (v15 == null) {
                } else {
                    int v20_3 = 0;
                    while (v20_3 < v15.length()) {
                        org.json.JSONObject v13_1 = ((org.json.JSONObject) v15.get(v20_3));
                        String v14 = this.getJsonString(v13_1, "id");
                        if (v14 != null) {
                            android.content.ContentProviderOperation v43_81 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
                            java.util.ArrayList v0_72 = new String[2];
                            Integer v45_19 = v0_72;
                            v45_19[0] = v14;
                            v45_19[1] = "vnd.android.cursor.item/email_v2";
                            v28_1.add(v43_81.withSelection("_id=? AND mimetype=?", v45_19).withValue("data1", this.getJsonString(v13_1, "value")).withValue("data2", Integer.valueOf(this.getContactType(this.getJsonString(v13_1, "type")))).build());
                        } else {
                            android.content.ContentValues v9_5 = new android.content.ContentValues();
                            v9_5.put("raw_contact_id", Integer.valueOf(v38));
                            v9_5.put("mimetype", "vnd.android.cursor.item/email_v2");
                            v9_5.put("data1", this.getJsonString(v13_1, "value"));
                            v9_5.put("data2", Integer.valueOf(this.getContactType(this.getJsonString(v13_1, "type"))));
                            v28_1.add(android.content.ContentProviderOperation.newInsert(android.provider.ContactsContract$Data.CONTENT_URI).withValues(v9_5).build());
                        }
                        v20_3++;
                    }
                }
            }
            if (v34 == null) {
            } else {
                v20_2 = 0;
            }
        }
        if ((v10 == null) && (v25 == null)) {
        } else {
            android.content.ContentProviderOperation v43_108 = android.content.ContentProviderOperation.newUpdate(android.provider.ContactsContract$Data.CONTENT_URI);
            java.util.ArrayList v0_104 = new String[2];
            Integer v45_41 = v0_104;
            v45_41[0] = p50;
            v45_41[1] = "vnd.android.cursor.item/name";
            android.content.ContentProviderOperation$Builder v7 = v43_108.withSelection("contact_id=? AND mimetype=?", v45_41);
            if (v10 != null) {
                v7.withValue("data1", v10);
            }
            String v16 = this.getJsonString(v25, "familyName");
            if (v16 != null) {
                v7.withValue("data3", v16);
            }
            String v24 = this.getJsonString(v25, "middleName");
            if (v24 != null) {
                v7.withValue("data5", v24);
            }
            String v17 = this.getJsonString(v25, "givenName");
            if (v17 != null) {
                v7.withValue("data2", v17);
            }
            String v18 = this.getJsonString(v25, "honorificPrefix");
            if (v18 != null) {
                v7.withValue("data4", v18);
            }
            String v19 = this.getJsonString(v25, "honorificSuffix");
            if (v19 != null) {
                v7.withValue("data6", v19);
            }
            v28_1.add(v7.build());
        }
    }

    private org.json.JSONObject nameQuery(android.database.Cursor p11)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        try {
            String v2 = p11.getString(p11.getColumnIndex("data3"));
            String v4 = p11.getString(p11.getColumnIndex("data2"));
            String v7 = p11.getString(p11.getColumnIndex("data5"));
            String v5 = p11.getString(p11.getColumnIndex("data4"));
            String v6 = p11.getString(p11.getColumnIndex("data6"));
            StringBuffer v3_1 = new StringBuffer("");
        } catch (String v8_42) {
            org.json.JSONException v1 = v8_42;
            android.util.Log.e("ContactsAccessor", v1.getMessage(), v1);
            return v0_1;
        }
        if (v5 != null) {
            v3_1.append(new StringBuilder().append(v5).append(" ").toString());
        }
        if (v4 != null) {
            v3_1.append(new StringBuilder().append(v4).append(" ").toString());
        }
        if (v7 != null) {
            v3_1.append(new StringBuilder().append(v7).append(" ").toString());
        }
        if (v2 != null) {
            v3_1.append(new StringBuilder().append(v2).append(" ").toString());
        }
        if (v6 != null) {
            v3_1.append(new StringBuilder().append(v6).append(" ").toString());
        }
        v0_1.put("familyName", v2);
        v0_1.put("givenName", v4);
        v0_1.put("middleName", v7);
        v0_1.put("honorificPrefix", v5);
        v0_1.put("honorificSuffix", v6);
        v0_1.put("formatted", v3_1);
        return v0_1;
    }

    private org.json.JSONObject organizationQuery(android.database.Cursor p5)
    {
        org.json.JSONObject v1_1 = new org.json.JSONObject();
        try {
            v1_1.put("id", p5.getString(p5.getColumnIndex("_id")));
            v1_1.put("department", p5.getString(p5.getColumnIndex("data5")));
            v1_1.put("name", p5.getString(p5.getColumnIndex("data1")));
            v1_1.put("title", p5.getString(p5.getColumnIndex("data4")));
        } catch (String v2_3) {
            org.json.JSONException v0 = v2_3;
            android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
        }
        return v1_1;
    }

    private org.json.JSONObject phoneQuery(android.database.Cursor p6)
    {
        org.json.JSONObject v2_1 = new org.json.JSONObject();
        try {
            v2_1.put("id", p6.getString(p6.getColumnIndex("_id")));
            v2_1.put("pref", 0);
            v2_1.put("value", p6.getString(p6.getColumnIndex("data1")));
            v2_1.put("type", this.getPhoneType(p6.getInt(p6.getColumnIndex("data2"))));
        } catch (String v3_5) {
            org.json.JSONException v0 = v3_5;
            android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
        } catch (String v3_3) {
            Exception v1 = v3_3;
            android.util.Log.e("ContactsAccessor", v1.getMessage(), v1);
        }
        return v2_1;
    }

    private org.json.JSONObject photoQuery(android.database.Cursor p8, String p9)
    {
        org.json.JSONObject v2_1 = new org.json.JSONObject();
        try {
            v2_1.put("id", p8.getString(p8.getColumnIndex("_id")));
            v2_1.put("pref", 0);
            v2_1.put("type", "url");
            v2_1.put("value", android.net.Uri.withAppendedPath(android.content.ContentUris.withAppendedId(android.provider.ContactsContract$Contacts.CONTENT_URI, new Long(p9).longValue()), "photo").toString());
        } catch (String v4_5) {
            org.json.JSONException v0 = v4_5;
            android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
        }
        return v2_1;
    }

    private org.json.JSONObject populateContact(org.json.JSONObject p4, org.json.JSONArray p5, org.json.JSONArray p6, org.json.JSONArray p7, org.json.JSONArray p8, org.json.JSONArray p9, org.json.JSONArray p10, org.json.JSONArray p11)
    {
        try {
            p4.put("organizations", p5);
            p4.put("addresses", p6);
            p4.put("phoneNumbers", p7);
            p4.put("emails", p8);
            p4.put("ims", p9);
            p4.put("websites", p10);
            p4.put("photos", p11);
        } catch (String v1_3) {
            org.json.JSONException v0 = v1_3;
            android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
        }
        return p4;
    }

    private org.json.JSONObject websiteQuery(android.database.Cursor p5)
    {
        org.json.JSONObject v1_1 = new org.json.JSONObject();
        try {
            v1_1.put("id", p5.getString(p5.getColumnIndex("_id")));
            v1_1.put("pref", 0);
            v1_1.put("value", p5.getString(p5.getColumnIndex("data1")));
            v1_1.put("type", this.getContactType(p5.getInt(p5.getColumnIndex("data2"))));
        } catch (String v2_3) {
            org.json.JSONException v0 = v2_3;
            android.util.Log.e("ContactsAccessor", v0.getMessage(), v0);
        }
        return v1_1;
    }

    public boolean remove(String p8)
    {
        int v1_0;
        int v1_2 = this.mApp.getContentResolver();
        String[] v4 = new String[1];
        v4[0] = p8;
        if (v1_2.delete(android.provider.ContactsContract$Data.CONTENT_URI, "contact_id = ?", v4) <= 0) {
            v1_0 = 0;
        } else {
            v1_0 = 1;
        }
        return v1_0;
    }

    public boolean save(org.json.JSONObject p12)
    {
        android.accounts.Account[] v2 = android.accounts.AccountManager.get(this.mApp).getAccounts();
        android.accounts.Account v1 = 0;
        if (v2.length != 1) {
            if (v2.length > 1) {
                android.accounts.Account[] v3_0 = v2;
                int v4_0 = 0;
                while (v4_0 < v3_0.length) {
                    android.accounts.Account v0_0 = v3_0[v4_0];
                    if ((!v0_0.type.contains("eas")) || (!v0_0.name.matches(".+@.+\\.+.+"))) {
                        v4_0++;
                    } else {
                        v1 = v0_0;
                        break;
                    }
                }
                if (v1 == null) {
                    android.accounts.Account[] v3_1 = v2;
                    int v4_1 = 0;
                    while (v4_1 < v3_1.length) {
                        android.accounts.Account v0_1 = v3_1[v4_1];
                        if ((!v0_1.type.contains("com.google")) || (!v0_1.name.matches(".+@.+\\.+.+"))) {
                            v4_1++;
                        } else {
                            v1 = v0_1;
                            break;
                        }
                    }
                }
                if (v1 == null) {
                    android.accounts.Account[] v3_2 = v2;
                    int v4_2 = 0;
                    while (v4_2 < v3_2.length) {
                        android.accounts.Account v0_2 = v3_2[v4_2];
                        if (!v0_2.name.matches(".+@.+\\.+.+")) {
                            v4_2++;
                        } else {
                            v1 = v0_2;
                            break;
                        }
                    }
                }
            }
        } else {
            v1 = v2[0];
        }
        boolean v8_13;
        if (v1 != null) {
            String v5 = this.getJsonString(p12, "id");
            if (v5 != null) {
                v8_13 = this.modifyContact(v5, p12, v1);
            } else {
                v8_13 = this.createNewContact(p12, v1);
            }
        } else {
            v8_13 = 0;
        }
        return v8_13;
    }

    public org.json.JSONArray search(org.json.JSONArray p43, org.json.JSONObject p44)
    {
        String v33_1;
        long v36 = System.currentTimeMillis();
        int v23 = 2147483647;
        if (p44 == null) {
            v33_1 = "%";
        } else {
            String v33_0 = p44.optString("filter");
            if (v33_0.length() != 0) {
                v33_1 = new StringBuilder().append("%").append(v33_0).append("%").toString();
            } else {
                v33_1 = "%";
            }
            try {
                if (!p44.getBoolean("multiple")) {
                    v23 = 1;
                }
            } catch (org.json.JSONObject v3) {
            }
        }
        java.util.HashMap v31 = this.buildPopulationSet(p43);
        com.phonegap.ContactAccessor$WhereOptions v39 = this.buildWhereClause(p43, v33_1);
        org.json.JSONObject v3_94 = this.mApp.getContentResolver();
        org.json.JSONArray v5_3 = new String[1];
        v5_3[0] = "contact_id";
        android.database.Cursor v20 = v3_94.query(android.provider.ContactsContract$Data.CONTENT_URI, v5_3, v39.getWhere(), v39.getWhereArgs(), "contact_id ASC");
        java.util.HashSet v16_1 = new java.util.HashSet();
        while (v20.moveToNext()) {
            v16_1.add(v20.getString(v20.getColumnIndex("contact_id")));
        }
        v20.close();
        com.phonegap.ContactAccessor$WhereOptions v21 = this.buildIdClause(v16_1, v33_1);
        android.database.Cursor v13 = this.mApp.getContentResolver().query(android.provider.ContactsContract$Data.CONTENT_URI, 0, v21.getWhere(), v21.getWhereArgs(), "contact_id ASC");
        String v32 = "";
        String v27 = "";
        int v26 = 1;
        org.json.JSONArray v17_1 = new org.json.JSONArray();
        org.json.JSONObject v4_6 = new org.json.JSONObject();
        org.json.JSONArray v5_1 = new org.json.JSONArray();
        org.json.JSONObject v6_0 = new org.json.JSONArray();
        org.json.JSONArray v7_1 = new org.json.JSONArray();
        org.json.JSONArray v8_1 = new org.json.JSONArray();
        org.json.JSONArray v9_1 = new org.json.JSONArray();
        org.json.JSONArray v10_1 = new org.json.JSONArray();
        org.json.JSONArray v11_1 = new org.json.JSONArray();
        if (v13.getCount() > 0) {
            while ((v13.moveToNext()) && (v17_1.length() <= (v23 - 1))) {
                try {
                    String v15 = v13.getString(v13.getColumnIndex("contact_id"));
                    v32 = v13.getString(v13.getColumnIndex("raw_contact_id"));
                } catch (org.json.JSONObject v3_91) {
                    org.json.JSONException v18 = v3_91;
                    android.util.Log.e("ContactsAccessor", v18.getMessage(), v18);
                    v27 = v15;
                }
                if (v13.getPosition() == 0) {
                    v27 = v15;
                }
                if (!v27.equals(v15)) {
                    v17_1.put(this.populateContact(v4_6, v5_1, v6_0, v7_1, v8_1, v9_1, v10_1, v11_1));
                    org.json.JSONObject v14_1 = new org.json.JSONObject();
                    try {
                        org.json.JSONArray v28_1 = new org.json.JSONArray();
                        try {
                            String v12_3 = new org.json.JSONArray();
                            try {
                                org.json.JSONArray v29_1 = new org.json.JSONArray();
                                try {
                                    org.json.JSONArray v19_1 = new org.json.JSONArray();
                                    try {
                                        org.json.JSONArray v22_1 = new org.json.JSONArray();
                                        try {
                                            org.json.JSONArray v38_1 = new org.json.JSONArray();
                                            try {
                                                v26 = 1;
                                                v11_1 = new org.json.JSONArray();
                                                v10_1 = v38_1;
                                                v9_1 = v22_1;
                                                v8_1 = v19_1;
                                                v7_1 = v29_1;
                                                v6_0 = v12_3;
                                                v5_1 = v28_1;
                                                v4_6 = v14_1;
                                            } catch (org.json.JSONObject v3_26) {
                                                v18 = v3_26;
                                                v10_1 = v38_1;
                                                v9_1 = v22_1;
                                                v8_1 = v19_1;
                                                v7_1 = v29_1;
                                                v6_0 = v12_3;
                                                v5_1 = v28_1;
                                                v4_6 = v14_1;
                                            }
                                        } catch (org.json.JSONObject v3_24) {
                                            v18 = v3_24;
                                            v9_1 = v22_1;
                                            v8_1 = v19_1;
                                            v7_1 = v29_1;
                                            v6_0 = v12_3;
                                            v5_1 = v28_1;
                                            v4_6 = v14_1;
                                        }
                                    } catch (org.json.JSONObject v3_23) {
                                        v18 = v3_23;
                                        v8_1 = v19_1;
                                        v7_1 = v29_1;
                                        v6_0 = v12_3;
                                        v5_1 = v28_1;
                                        v4_6 = v14_1;
                                    }
                                } catch (org.json.JSONObject v3_22) {
                                    v18 = v3_22;
                                    v7_1 = v29_1;
                                    v6_0 = v12_3;
                                    v5_1 = v28_1;
                                    v4_6 = v14_1;
                                }
                            } catch (org.json.JSONObject v3_21) {
                                v18 = v3_21;
                                v6_0 = v12_3;
                                v5_1 = v28_1;
                                v4_6 = v14_1;
                            }
                        } catch (org.json.JSONObject v3_19) {
                            v18 = v3_19;
                            v5_1 = v28_1;
                            v4_6 = v14_1;
                        }
                    } catch (org.json.JSONObject v3_18) {
                        v18 = v3_18;
                        v4_6 = v14_1;
                    }
                }
                if (v26 != 0) {
                    v26 = 0;
                    v4_6.put("id", v15);
                    v4_6.put("rawId", v32);
                    v4_6.put("displayName", v13.getString(v13.getColumnIndex("data1")));
                }
                String v24 = v13.getString(v13.getColumnIndex("mimetype"));
                if ((!v24.equals("vnd.android.cursor.item/name")) || (!this.isRequired("name", v31))) {
                    if ((!v24.equals("vnd.android.cursor.item/phone_v2")) || (!this.isRequired("phoneNumbers", v31))) {
                        if ((!v24.equals("vnd.android.cursor.item/email_v2")) || (!this.isRequired("emails", v31))) {
                            if ((!v24.equals("vnd.android.cursor.item/postal-address_v2")) || (!this.isRequired("addresses", v31))) {
                                if ((!v24.equals("vnd.android.cursor.item/organization")) || (!this.isRequired("organizations", v31))) {
                                    if ((!v24.equals("vnd.android.cursor.item/im")) || (!this.isRequired("ims", v31))) {
                                        if ((!v24.equals("vnd.android.cursor.item/note")) || (!this.isRequired("note", v31))) {
                                            if ((!v24.equals("vnd.android.cursor.item/nickname")) || (!this.isRequired("nickname", v31))) {
                                                if ((!v24.equals("vnd.android.cursor.item/website")) || (!this.isRequired("urls", v31))) {
                                                    if (!v24.equals("vnd.android.cursor.item/contact_event")) {
                                                        if ((!v24.equals("vnd.android.cursor.item/photo")) || (!this.isRequired("photos", v31))) {
                                                        } else {
                                                            v11_1.put(this.photoQuery(v13, v15));
                                                        }
                                                    } else {
                                                        if ((3 != v13.getInt(v13.getColumnIndex("data2"))) || (!this.isRequired("birthday", v31))) {
                                                        } else {
                                                            v4_6.put("birthday", v13.getString(v13.getColumnIndex("data1")));
                                                        }
                                                    }
                                                } else {
                                                    v10_1.put(this.websiteQuery(v13));
                                                }
                                            } else {
                                                v4_6.put("nickname", v13.getString(v13.getColumnIndex("data1")));
                                            }
                                        } else {
                                            v4_6.put("note", v13.getString(v13.getColumnIndex("data1")));
                                        }
                                    } else {
                                        v9_1.put(this.imQuery(v13));
                                    }
                                } else {
                                    v5_1.put(this.organizationQuery(v13));
                                }
                            } else {
                                v6_0.put(this.addressQuery(v13));
                            }
                        } else {
                            v8_1.put(this.emailQuery(v13));
                        }
                    } else {
                        v7_1.put(this.phoneQuery(v13));
                    }
                } else {
                    v4_6.put("name", this.nameQuery(v13));
                }
            }
            if (v17_1.length() < v23) {
                v17_1.put(this.populateContact(v4_6, v5_1, v6_0, v7_1, v8_1, v9_1, v10_1, v11_1));
            }
        }
        v13.close();
        android.util.Log.d("ContactsAccessor", new StringBuilder().append("Total time = ").append((System.currentTimeMillis() - v36)).toString());
        return v17_1;
    }
}
