package com.phonegap.api;
public final enum class PluginResult$Status extends java.lang.Enum {
    private static final synthetic com.phonegap.api.PluginResult$Status[] $VALUES;
    public static final enum com.phonegap.api.PluginResult$Status CLASS_NOT_FOUND_EXCEPTION;
    public static final enum com.phonegap.api.PluginResult$Status ERROR;
    public static final enum com.phonegap.api.PluginResult$Status ILLEGAL_ACCESS_EXCEPTION;
    public static final enum com.phonegap.api.PluginResult$Status INSTANTIATION_EXCEPTION;
    public static final enum com.phonegap.api.PluginResult$Status INVALID_ACTION;
    public static final enum com.phonegap.api.PluginResult$Status IO_EXCEPTION;
    public static final enum com.phonegap.api.PluginResult$Status JSON_EXCEPTION;
    public static final enum com.phonegap.api.PluginResult$Status MALFORMED_URL_EXCEPTION;
    public static final enum com.phonegap.api.PluginResult$Status NO_RESULT;
    public static final enum com.phonegap.api.PluginResult$Status OK;

    static PluginResult$Status()
    {
        com.phonegap.api.PluginResult$Status.NO_RESULT = new com.phonegap.api.PluginResult$Status("NO_RESULT", 0);
        com.phonegap.api.PluginResult$Status.OK = new com.phonegap.api.PluginResult$Status("OK", 1);
        com.phonegap.api.PluginResult$Status.CLASS_NOT_FOUND_EXCEPTION = new com.phonegap.api.PluginResult$Status("CLASS_NOT_FOUND_EXCEPTION", 2);
        com.phonegap.api.PluginResult$Status.ILLEGAL_ACCESS_EXCEPTION = new com.phonegap.api.PluginResult$Status("ILLEGAL_ACCESS_EXCEPTION", 3);
        com.phonegap.api.PluginResult$Status.INSTANTIATION_EXCEPTION = new com.phonegap.api.PluginResult$Status("INSTANTIATION_EXCEPTION", 4);
        com.phonegap.api.PluginResult$Status.MALFORMED_URL_EXCEPTION = new com.phonegap.api.PluginResult$Status("MALFORMED_URL_EXCEPTION", 5);
        com.phonegap.api.PluginResult$Status.IO_EXCEPTION = new com.phonegap.api.PluginResult$Status("IO_EXCEPTION", 6);
        com.phonegap.api.PluginResult$Status.INVALID_ACTION = new com.phonegap.api.PluginResult$Status("INVALID_ACTION", 7);
        com.phonegap.api.PluginResult$Status.JSON_EXCEPTION = new com.phonegap.api.PluginResult$Status("JSON_EXCEPTION", 8);
        com.phonegap.api.PluginResult$Status.ERROR = new com.phonegap.api.PluginResult$Status("ERROR", 9);
        com.phonegap.api.PluginResult$Status[] v0_19 = new com.phonegap.api.PluginResult$Status[10];
        v0_19[0] = com.phonegap.api.PluginResult$Status.NO_RESULT;
        v0_19[1] = com.phonegap.api.PluginResult$Status.OK;
        v0_19[2] = com.phonegap.api.PluginResult$Status.CLASS_NOT_FOUND_EXCEPTION;
        v0_19[3] = com.phonegap.api.PluginResult$Status.ILLEGAL_ACCESS_EXCEPTION;
        v0_19[4] = com.phonegap.api.PluginResult$Status.INSTANTIATION_EXCEPTION;
        v0_19[5] = com.phonegap.api.PluginResult$Status.MALFORMED_URL_EXCEPTION;
        v0_19[6] = com.phonegap.api.PluginResult$Status.IO_EXCEPTION;
        v0_19[7] = com.phonegap.api.PluginResult$Status.INVALID_ACTION;
        v0_19[8] = com.phonegap.api.PluginResult$Status.JSON_EXCEPTION;
        v0_19[9] = com.phonegap.api.PluginResult$Status.ERROR;
        com.phonegap.api.PluginResult$Status.$VALUES = v0_19;
        return;
    }

    private PluginResult$Status(String p1, int p2)
    {
        super(p1, p2);
        return;
    }

    public static com.phonegap.api.PluginResult$Status valueOf(String p1)
    {
        return ((com.phonegap.api.PluginResult$Status) Enum.valueOf(com.phonegap.api.PluginResult$Status, p1));
    }

    public static com.phonegap.api.PluginResult$Status[] values()
    {
        return ((com.phonegap.api.PluginResult$Status[]) com.phonegap.api.PluginResult$Status.$VALUES.clone());
    }
}
