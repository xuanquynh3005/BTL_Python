package com.phonegap;
public class ContactAccessorSdk3_4 extends com.phonegap.ContactAccessor {
    private static final String PEOPLE_ID_EQUALS = "people._id = ?";
    private static final java.util.Map dbMap;

    static ContactAccessorSdk3_4()
    {
        com.phonegap.ContactAccessorSdk3_4.dbMap = new java.util.HashMap();
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("id", "_id");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("displayName", "display_name");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("phoneNumbers", "number");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("phoneNumbers.value", "number");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("emails", "data");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("emails.value", "data");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("addresses", "data");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("addresses.formatted", "data");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("ims", "data");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("ims.value", "data");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("organizations", "company");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("organizations.name", "company");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("organizations.title", "title");
        com.phonegap.ContactAccessorSdk3_4.dbMap.put("note", "notes");
        return;
    }

    public ContactAccessorSdk3_4(android.webkit.WebView p1, android.app.Activity p2)
    {
        this.mApp = p2;
        this.mView = p1;
        return;
    }

    private org.json.JSONArray addressQuery(android.content.ContentResolver p11, String p12)
    {
        String[] v4 = new String[2];
        v4[0] = p12;
        v4[1] = "vnd.android.cursor.item/postal-address";
        android.database.Cursor v8 = p11.query(android.provider.Contacts$ContactMethods.CONTENT_URI, 0, "person = ? AND kind = ?", v4, 0);
        org.json.JSONArray v7_1 = new org.json.JSONArray();
        while (v8.moveToNext()) {
            org.json.JSONObject v6_1 = new org.json.JSONObject();
            try {
                v6_1.put("id", v8.getString(v8.getColumnIndex("_id")));
                v6_1.put("formatted", v8.getString(v8.getColumnIndex("data")));
                v7_1.put(v6_1);
            } catch (String v0_5) {
                org.json.JSONException v9 = v0_5;
                android.util.Log.e("ContactsAccessor", v9.getMessage(), v9);
            }
        }
        return v7_1;
    }

    private java.util.Set buildSetOfContactIds(org.json.JSONArray p11, String p12)
    {
        java.util.HashSet v2_1 = new java.util.HashSet();
        int v8 = 0;
        try {
            while (v8 < p11.length()) {
                String v9 = p11.getString(v8);
                if (!v9.startsWith("displayName")) {
                    if (!v9.startsWith("phoneNumbers")) {
                        if (!v9.startsWith("emails")) {
                            if (!v9.startsWith("addresses")) {
                                if (!v9.startsWith("ims")) {
                                    if (!v9.startsWith("organizations")) {
                                        if (v9.startsWith("note")) {
                                            String v5_5 = new StringBuilder().append(((String) com.phonegap.ContactAccessorSdk3_4.dbMap.get(v9))).append(" LIKE ?").toString();
                                            String[] v6_5 = new String[1];
                                            v6_5[0] = p12;
                                            this.doQuery(p12, v2_1, android.provider.Contacts$People.CONTENT_URI, "_id", v5_5, v6_5);
                                        }
                                    } else {
                                        String v5_6 = new StringBuilder().append(((String) com.phonegap.ContactAccessorSdk3_4.dbMap.get(v9))).append(" LIKE ?").toString();
                                        String[] v6_6 = new String[1];
                                        v6_6[0] = p12;
                                        this.doQuery(p12, v2_1, android.provider.Contacts$Organizations.CONTENT_URI, "person", v5_6, v6_6);
                                    }
                                } else {
                                    String v5_0 = new StringBuilder().append(((String) com.phonegap.ContactAccessorSdk3_4.dbMap.get(v9))).append(" LIKE ? AND ").append("kind").append(" = ?").toString();
                                    String[] v6_0 = new String[2];
                                    v6_0[0] = p12;
                                    v6_0[1] = "vnd.android.cursor.item/jabber-im";
                                    this.doQuery(p12, v2_1, android.provider.Contacts$ContactMethods.CONTENT_URI, "person", v5_0, v6_0);
                                }
                            } else {
                                String v5_1 = new StringBuilder().append(((String) com.phonegap.ContactAccessorSdk3_4.dbMap.get(v9))).append(" LIKE ? AND ").append("kind").append(" = ?").toString();
                                String[] v6_1 = new String[2];
                                v6_1[0] = p12;
                                v6_1[1] = "vnd.android.cursor.item/postal-address";
                                this.doQuery(p12, v2_1, android.provider.Contacts$ContactMethods.CONTENT_URI, "person", v5_1, v6_1);
                            }
                        } else {
                            String v5_2 = new StringBuilder().append(((String) com.phonegap.ContactAccessorSdk3_4.dbMap.get(v9))).append(" LIKE ? AND ").append("kind").append(" = ?").toString();
                            String[] v6_2 = new String[2];
                            v6_2[0] = p12;
                            v6_2[1] = "vnd.android.cursor.item/email";
                            this.doQuery(p12, v2_1, android.provider.Contacts$ContactMethods.CONTENT_EMAIL_URI, "person", v5_2, v6_2);
                        }
                    } else {
                        String v5_3 = new StringBuilder().append(((String) com.phonegap.ContactAccessorSdk3_4.dbMap.get(v9))).append(" LIKE ?").toString();
                        String[] v6_3 = new String[1];
                        v6_3[0] = p12;
                        this.doQuery(p12, v2_1, android.provider.Contacts$Phones.CONTENT_URI, "person", v5_3, v6_3);
                    }
                } else {
                    String v5_4 = new StringBuilder().append(((String) com.phonegap.ContactAccessorSdk3_4.dbMap.get(v9))).append(" LIKE ?").toString();
                    String[] v6_4 = new String[1];
                    v6_4[0] = p12;
                    this.doQuery(p12, v2_1, android.provider.Contacts$People.CONTENT_URI, "_id", v5_4, v6_4);
                }
                v8++;
            }
        } catch (void v0_48) {
            org.json.JSONException v7 = v0_48;
            android.util.Log.e("ContactsAccessor", v7.getMessage(), v7);
        }
        return v2_1;
    }

    private String createAddressString(org.json.JSONObject p3)
    {
        StringBuffer v0_1 = new StringBuffer("");
        if (this.getJsonString(p3, "locality") != null) {
            v0_1.append(this.getJsonString(p3, "locality"));
        }
        if (this.getJsonString(p3, "region") != null) {
            if (v0_1.length() > 0) {
                v0_1.append(", ");
            }
            v0_1.append(this.getJsonString(p3, "region"));
        }
        if (this.getJsonString(p3, "postalCode") != null) {
            if (v0_1.length() > 0) {
                v0_1.append(", ");
            }
            v0_1.append(this.getJsonString(p3, "postalCode"));
        }
        if (this.getJsonString(p3, "country") != null) {
            if (v0_1.length() > 0) {
                v0_1.append(", ");
            }
            v0_1.append(this.getJsonString(p3, "country"));
        }
        return v0_1.toString();
    }

    private void doQuery(String p8, java.util.Set p9, android.net.Uri p10, String p11, String p12, String[] p13)
    {
        android.database.Cursor v6 = this.mApp.getContentResolver().query(p10, 0, p12, p13, 0);
        while (v6.moveToNext()) {
            p9.add(v6.getString(v6.getColumnIndex(p11)));
        }
        v6.close();
        return;
    }

    private org.json.JSONArray emailQuery(android.content.ContentResolver p11, String p12)
    {
        String[] v4 = new String[1];
        v4[0] = p12;
        android.database.Cursor v6 = p11.query(android.provider.Contacts$ContactMethods.CONTENT_EMAIL_URI, 0, "person = ?", v4, 0);
        org.json.JSONArray v9_1 = new org.json.JSONArray();
        while (v6.moveToNext()) {
            org.json.JSONObject v8_1 = new org.json.JSONObject();
            try {
                v8_1.put("id", v6.getString(v6.getColumnIndex("_id")));
                v8_1.put("perf", 0);
                v8_1.put("value", v6.getString(v6.getColumnIndex("data")));
                v9_1.put(v8_1);
            } catch (String v0_4) {
                org.json.JSONException v7 = v0_4;
                android.util.Log.e("ContactsAccessor", v7.getMessage(), v7);
            }
        }
        return v9_1;
    }

    private int getContactType(String p4)
    {
        int v1_6;
        if (p4 == null) {
            v1_6 = 3;
        } else {
            if (!"home".equals(p4.toLowerCase())) {
                if (!"work".equals(p4.toLowerCase())) {
                    if (!"other".equals(p4.toLowerCase())) {
                        if (!"custom".equals(p4.toLowerCase())) {
                        } else {
                            v1_6 = 0;
                        }
                    } else {
                        v1_6 = 3;
                    }
                } else {
                    v1_6 = 2;
                }
            } else {
                v1_6 = 1;
            }
        }
        return v1_6;
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
            default:
                v0 = "other";
        }
        return v0;
    }

    private int getPhoneType(String p5)
    {
        int v1_16;
        if (!"home".equals(p5.toLowerCase())) {
            if (!"mobile".equals(p5.toLowerCase())) {
                if (!"work".equals(p5.toLowerCase())) {
                    if (!"work fax".equals(p5.toLowerCase())) {
                        if (!"home fax".equals(p5.toLowerCase())) {
                            if (!"fax".equals(p5.toLowerCase())) {
                                if (!"pager".equals(p5.toLowerCase())) {
                                    if (!"other".equals(p5.toLowerCase())) {
                                        if (!"custom".equals(p5.toLowerCase())) {
                                            v1_16 = 7;
                                        } else {
                                            v1_16 = 0;
                                        }
                                    } else {
                                        v1_16 = 7;
                                    }
                                } else {
                                    v1_16 = 6;
                                }
                            } else {
                                v1_16 = 4;
                            }
                        } else {
                            v1_16 = 5;
                        }
                    } else {
                        v1_16 = 4;
                    }
                } else {
                    v1_16 = 3;
                }
            } else {
                v1_16 = 2;
            }
        } else {
            v1_16 = 1;
        }
        return v1_16;
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
            default:
                v0 = "custom";
        }
        return v0;
    }

    private org.json.JSONArray imQuery(android.content.ContentResolver p11, String p12)
    {
        String[] v4 = new String[2];
        v4[0] = p12;
        v4[1] = "vnd.android.cursor.item/jabber-im";
        android.database.Cursor v6 = p11.query(android.provider.Contacts$ContactMethods.CONTENT_URI, 0, "person = ? AND kind = ?", v4, 0);
        org.json.JSONArray v9_1 = new org.json.JSONArray();
        while (v6.moveToNext()) {
            org.json.JSONObject v8_1 = new org.json.JSONObject();
            try {
                v8_1.put("id", v6.getString(v6.getColumnIndex("_id")));
                v8_1.put("perf", 0);
                v8_1.put("value", v6.getString(v6.getColumnIndex("data")));
                v8_1.put("type", this.getContactType(v6.getInt(v6.getColumnIndex("type"))));
                v9_1.put(v8_1);
            } catch (String v0_7) {
                org.json.JSONException v7 = v0_7;
                android.util.Log.e("ContactsAccessor", v7.getMessage(), v7);
            }
        }
        v6.close();
        return 0;
    }

    private org.json.JSONArray organizationQuery(android.content.ContentResolver p11, String p12)
    {
        String[] v4 = new String[1];
        v4[0] = p12;
        android.database.Cursor v6 = p11.query(android.provider.Contacts$Organizations.CONTENT_URI, 0, "person = ?", v4, 0);
        org.json.JSONArray v9_1 = new org.json.JSONArray();
        while (v6.moveToNext()) {
            org.json.JSONObject v8_1 = new org.json.JSONObject();
            try {
                v8_1.put("id", v6.getString(v6.getColumnIndex("_id")));
                v8_1.put("name", v6.getString(v6.getColumnIndex("company")));
                v8_1.put("title", v6.getString(v6.getColumnIndex("title")));
                v9_1.put(v8_1);
            } catch (String v0_6) {
                org.json.JSONException v7 = v0_6;
                android.util.Log.e("ContactsAccessor", v7.getMessage(), v7);
            }
        }
        return v9_1;
    }

    private org.json.JSONArray phoneQuery(android.content.ContentResolver p11, String p12)
    {
        String[] v4 = new String[1];
        v4[0] = p12;
        android.database.Cursor v6 = p11.query(android.provider.Contacts$Phones.CONTENT_URI, 0, "person = ?", v4, 0);
        org.json.JSONArray v9_1 = new org.json.JSONArray();
        while (v6.moveToNext()) {
            org.json.JSONObject v8_1 = new org.json.JSONObject();
            try {
                v8_1.put("id", v6.getString(v6.getColumnIndex("_id")));
                v8_1.put("perf", 0);
                v8_1.put("value", v6.getString(v6.getColumnIndex("number")));
                v8_1.put("type", this.getPhoneType(v6.getInt(v6.getColumnIndex("type"))));
                v9_1.put(v8_1);
            } catch (String v0_6) {
                org.json.JSONException v7 = v0_6;
                android.util.Log.e("ContactsAccessor", v7.getMessage(), v7);
            }
        }
        return v9_1;
    }

    private void saveAddresses(org.json.JSONObject p13, android.net.Uri p14)
    {
        android.content.ContentValues v8_1 = new android.content.ContentValues();
        android.net.Uri v6 = android.net.Uri.withAppendedPath(p14, "contact_methods");
        try {
            org.json.JSONArray v2 = p13.getJSONArray("addresses");
        } catch (android.content.ContentResolver v9_12) {
            android.util.Log.d("ContactsAccessor", new StringBuilder().append("Could not save address = ").append(v9_12.getMessage()).toString());
            return;
        }
        if ((v2 == null) || (v2.length() <= 0)) {
            return;
        } else {
            v8_1.put("kind", Integer.valueOf(2));
            int v4 = 0;
            while (v4 < v2.length()) {
                org.json.JSONObject v3 = v2.getJSONObject(v4);
                String v5 = this.getJsonString(v3, "id");
                String v0 = this.getJsonString(v3, "formatted");
                if (v0 == null) {
                    v8_1.put("data", this.createAddressString(v3));
                } else {
                    v8_1.put("data", v0);
                }
                if (v5 != null) {
                    this.mApp.getContentResolver().update(android.net.Uri.withAppendedPath(v6, v5), v8_1, 0, 0);
                } else {
                    this.mApp.getContentResolver().insert(v6, v8_1);
                }
                v4++;
            }
            return;
        }
    }

    private void saveEntries(org.json.JSONObject p12, android.net.Uri p13, String p14, int p15)
    {
        android.content.ContentValues v7_1 = new android.content.ContentValues();
        android.net.Uri v5 = android.net.Uri.withAppendedPath(p13, "contact_methods");
        try {
            org.json.JSONArray v1 = p12.getJSONArray(p14);
        } catch (android.content.ContentResolver v8_11) {
            android.util.Log.d("ContactsAccessor", new StringBuilder().append("Could not save ").append(p14).append(" = ").append(v8_11.getMessage()).toString());
            return;
        }
        if ((v1 == null) || (v1.length() <= 0)) {
            return;
        } else {
            v7_1.put("kind", Integer.valueOf(p15));
            int v3 = 0;
            while (v3 < v1.length()) {
                org.json.JSONObject v2 = v1.getJSONObject(v3);
                String v4 = this.getJsonString(v2, "id");
                v7_1.put("data", this.getJsonString(v2, "value"));
                v7_1.put("type", Integer.valueOf(this.getContactType(this.getJsonString(v2, "type"))));
                if (v4 != null) {
                    this.mApp.getContentResolver().update(android.net.Uri.withAppendedPath(v5, v4), v7_1, 0, 0);
                } else {
                    this.mApp.getContentResolver().insert(v5, v7_1);
                }
                v3++;
            }
            return;
        }
    }

    private void saveOrganizations(org.json.JSONObject p12, android.net.Uri p13)
    {
        android.content.ContentValues v7_1 = new android.content.ContentValues();
        android.net.Uri v4 = android.net.Uri.withAppendedPath(p13, "organizations");
        try {
            org.json.JSONArray v5 = p12.getJSONArray("organizations");
        } catch (android.content.ContentResolver v8_10) {
            android.util.Log.d("ContactsAccessor", new StringBuilder().append("Could not save organizations = ").append(v8_10.getMessage()).toString());
            return;
        }
        if ((v5 == null) || (v5.length() <= 0)) {
            return;
        } else {
            int v1 = 0;
            while (v1 < v5.length()) {
                org.json.JSONObject v3 = v5.getJSONObject(v1);
                String v2 = this.getJsonString(v3, "id");
                v7_1.put("company", this.getJsonString(v3, "name"));
                v7_1.put("title", this.getJsonString(v3, "title"));
                if (v2 != null) {
                    this.mApp.getContentResolver().update(android.net.Uri.withAppendedPath(v4, v2), v7_1, 0, 0);
                } else {
                    this.mApp.getContentResolver().insert(v4, v7_1);
                }
                v1++;
            }
            return;
        }
    }

    private void savePhoneNumbers(org.json.JSONObject p12, android.net.Uri p13)
    {
        android.content.ContentValues v7_1 = new android.content.ContentValues();
        android.net.Uri v6 = android.net.Uri.withAppendedPath(p13, "phones");
        try {
            org.json.JSONArray v5 = p12.getJSONArray("phoneNumbers");
        } catch (android.content.ContentResolver v8_10) {
            android.util.Log.d("ContactsAccessor", new StringBuilder().append("Could not save phones = ").append(v8_10.getMessage()).toString());
            return;
        }
        if ((v5 == null) || (v5.length() <= 0)) {
            return;
        } else {
            int v1 = 0;
            while (v1 < v5.length()) {
                org.json.JSONObject v4 = v5.getJSONObject(v1);
                String v2 = this.getJsonString(v4, "id");
                v7_1.put("number", this.getJsonString(v4, "value"));
                v7_1.put("type", Integer.valueOf(this.getPhoneType(this.getJsonString(v4, "type"))));
                if (v2 != null) {
                    this.mApp.getContentResolver().update(android.net.Uri.withAppendedPath(v6, v2), v7_1, 0, 0);
                } else {
                    this.mApp.getContentResolver().insert(v6, v7_1);
                }
                v1++;
            }
            return;
        }
    }

    public boolean remove(String p8)
    {
        int v1_0;
        int v1_2 = this.mApp.getContentResolver();
        String[] v4 = new String[1];
        v4[0] = p8;
        if (v1_2.delete(android.provider.Contacts$People.CONTENT_URI, "people._id = ?", v4) <= 0) {
            v1_0 = 0;
        } else {
            v1_0 = 1;
        }
        return v1_0;
    }

    public boolean save(org.json.JSONObject p11)
    {
        android.content.ContentValues v4_1 = new android.content.ContentValues();
        String v0 = this.getJsonString(p11, "id");
        String v1 = this.getJsonString(p11, "displayName");
        if (v1 != null) {
            v4_1.put("name", v1);
        }
        String v3 = this.getJsonString(p11, "note");
        if (v3 != null) {
            v4_1.put("notes", v3);
        }
        android.net.Uri v2;
        v4_1.put("starred", Integer.valueOf(0));
        if (v0 != null) {
            v2 = android.net.Uri.withAppendedPath(android.provider.Contacts$People.CONTENT_URI, v0);
            int v5_6 = this.mApp.getContentResolver();
            String[] v7 = new String[1];
            v7[0] = v0;
            v5_6.update(v2, v4_1, "people._id = ?", v7);
        } else {
            v2 = android.provider.Contacts$People.createPersonInMyContactsGroup(this.mApp.getContentResolver(), v4_1);
        }
        int v5_10;
        if (v2 == null) {
            v5_10 = 0;
        } else {
            this.savePhoneNumbers(p11, v2);
            this.saveEntries(p11, v2, "emails", 1);
            this.saveAddresses(p11, v2);
            this.saveOrganizations(p11, v2);
            this.saveEntries(p11, v2, "ims", 3);
            v5_10 = 1;
        }
        return v5_10;
    }

    public org.json.JSONArray search(org.json.JSONArray p22, org.json.JSONObject p23)
    {
        String v20_0;
        int v16 = 2147483647;
        if (p23 == null) {
            v20_0 = "%";
        } else {
            String v20_1 = p23.optString("filter");
            if (v20_1.length() != 0) {
                v20_0 = new StringBuilder().append("%").append(v20_1).append("%").toString();
            } else {
                v20_0 = "%";
            }
            try {
                if (!p23.getBoolean("multiple")) {
                    v16 = 1;
                }
            } catch (String v4) {
            }
        }
        android.content.ContentResolver v3 = this.mApp.getContentResolver();
        java.util.Set v11 = this.buildSetOfContactIds(p22, v20_0);
        java.util.HashMap v18 = this.buildPopulationSet(p22);
        java.util.Iterator v15 = v11.iterator();
        org.json.JSONArray v12_1 = new org.json.JSONArray();
        int v19 = 0;
        while ((v15.hasNext()) && (v19 < v16)) {
            org.json.JSONObject v9_1 = new org.json.JSONObject();
            try {
                String v10_1 = ((String) v15.next());
                v9_1.put("id", v10_1);
                String v5_12 = new String[2];
                v5_12[0] = "display_name";
                v5_12[1] = "notes";
                String[] v7_3 = new String[1];
                v7_3[0] = v10_1;
                android.database.Cursor v13 = v3.query(android.provider.Contacts$People.CONTENT_URI, v5_12, "people._id = ?", v7_3, 0);
                v13.moveToFirst();
            } catch (String v4_21) {
                org.json.JSONException v14 = v4_21;
                android.util.Log.e("ContactsAccessor", v14.getMessage(), v14);
                v12_1.put(v9_1);
            }
            if (this.isRequired("displayName", v18)) {
                v9_1.put("displayName", v13.getString(v13.getColumnIndex("display_name")));
            }
            if (this.isRequired("phoneNumbers", v18)) {
                v9_1.put("phoneNumbers", this.phoneQuery(v3, v10_1));
            }
            if (this.isRequired("emails", v18)) {
                v9_1.put("emails", this.emailQuery(v3, v10_1));
            }
            if (this.isRequired("addresses", v18)) {
                v9_1.put("addresses", this.addressQuery(v3, v10_1));
            }
            if (this.isRequired("organizations", v18)) {
                v9_1.put("organizations", this.organizationQuery(v3, v10_1));
            }
            if (this.isRequired("ims", v18)) {
                v9_1.put("ims", this.imQuery(v3, v10_1));
            }
            if (this.isRequired("note", v18)) {
                v9_1.put("note", v13.getString(v13.getColumnIndex("notes")));
            }
            v19++;
            v13.close();
        }
        return v12_1;
    }
}
