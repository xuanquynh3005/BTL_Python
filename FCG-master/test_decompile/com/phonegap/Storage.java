package com.phonegap;
public class Storage extends com.phonegap.api.Plugin {
    private static final String ALTER = "alter";
    private static final String CREATE = "create";
    private static final String DROP = "drop";
    private static final String TRUNCATE = "truncate";
    String dbName;
    android.database.sqlite.SQLiteDatabase myDb;
    String path;

    public Storage()
    {
        this.myDb = 0;
        this.path = 0;
        this.dbName = 0;
        return;
    }

    private boolean isDDL(String p3)
    {
        int v1_4;
        String v0 = p3.toLowerCase();
        if ((!v0.startsWith("drop")) && ((!v0.startsWith("create")) && ((!v0.startsWith("alter")) && (!v0.startsWith("truncate"))))) {
            v1_4 = 0;
        } else {
            v1_4 = 1;
        }
        return v1_4;
    }

    public com.phonegap.api.PluginResult execute(String p14, org.json.JSONArray p15, String p16)
    {
        try {
            if (!p14.equals("setStorage")) {
                if (!p14.equals("openDatabase")) {
                    if (!p14.equals("executeSql")) {
                        String v0_19 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.OK, "");
                    } else {
                        String[] v11;
                        if (!p15.isNull(1)) {
                            org.json.JSONArray v6 = p15.getJSONArray(1);
                            int v9 = v6.length();
                            v11 = new String[v9];
                            int v8 = 0;
                            while (v8 < v9) {
                                v11[v8] = v6.getString(v8);
                                v8++;
                            }
                        } else {
                            v11 = new String[0];
                        }
                        this.executeSql(p15.getString(0), v11, p15.getString(2));
                    }
                } else {
                    this.openDatabase(p15.getString(0), p15.getString(1), p15.getString(2), p15.getLong(3));
                }
            } else {
                this.setStorage(p15.getString(0));
            }
        } catch (String v0_21) {
            v0_19 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v0_19;
    }

    public void executeSql(String p6, String[] p7, String p8)
    {
        try {
            if (!this.isDDL(p6)) {
                android.database.Cursor v1 = this.myDb.rawQuery(p6, p7);
                this.processResults(v1, p8);
                v1.close();
            } else {
                this.myDb.execSQL(p6);
                this.sendJavascript(new StringBuilder().append("droiddb.completeQuery(\'").append(p8).append("\', \'\');").toString());
            }
        } catch (android.database.sqlite.SQLiteDatabase v2_7) {
            android.database.sqlite.SQLiteException v0 = v2_7;
            v0.printStackTrace();
            System.out.println(new StringBuilder().append("Storage.executeSql(): Error=").append(v0.getMessage()).toString());
            this.sendJavascript(new StringBuilder().append("droiddb.fail(\'").append(v0.getMessage()).append("\',\'").append(p8).append("\');").toString());
        }
        return;
    }

    public boolean isSynch(String p2)
    {
        return 1;
    }

    public void onDestroy()
    {
        if (this.myDb != null) {
            this.myDb.close();
            this.myDb = 0;
        }
        return;
    }

    public void openDatabase(String p5, String p6, String p7, long p8)
    {
        if (this.myDb != null) {
            this.myDb.close();
        }
        if (this.path == null) {
            this.setStorage(this.ctx.getClass().getPackage().getName());
        }
        this.dbName = new StringBuilder().append(this.path).append(p5).append(".db").toString();
        this.myDb = android.database.sqlite.SQLiteDatabase.openOrCreateDatabase(this.dbName, 0);
        return;
    }

    public void processResults(android.database.Cursor p11, String p12)
    {
        String v5 = "[]";
        if (p11.moveToFirst()) {
            org.json.JSONArray v2_1 = new org.json.JSONArray();
            String v7 = "";
            int v0 = p11.getColumnCount();
            while(true) {
                org.json.JSONObject v6_1 = new org.json.JSONObject();
                int v3 = 0;
                if (v3 >= v0) {
                    v2_1.put(v6_1);
                } else {
                    v6_1.put(p11.getColumnName(v3), p11.getString(v3));
                    v3++;
                    try {
                        while (v3 < v0) {
                        }
                    } catch (boolean v8_1) {
                        v8_1.printStackTrace();
                    }
                }
                if (!p11.moveToNext()) {
                    v5 = v2_1.toString();
                }
            }
        }
        this.sendJavascript(new StringBuilder().append("droiddb.completeQuery(\'").append(p12).append("\', ").append(v5).append(");").toString());
        return;
    }

    public void setStorage(String p3)
    {
        this.path = new StringBuilder().append("/data/data/").append(p3).append("/databases/").toString();
        return;
    }
}
