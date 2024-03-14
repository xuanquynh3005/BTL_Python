package com.phonegap;
public class CallbackServer implements java.lang.Runnable {
    private static final String LOG_TAG = "CallbackServer";
    static final String digits = "0123456789ABCDEF";
    private boolean active;
    private boolean empty;
    private java.util.LinkedList javascript;
    private int port;
    private Thread serverThread;
    private String token;
    private boolean usePolling;

    public CallbackServer()
    {
        this.usePolling = 1;
        this.active = 0;
        this.empty = 1;
        this.port = 0;
        this.javascript = new java.util.LinkedList();
        return;
    }

    private static void convert(String p4, StringBuilder p5, String p6)
    {
        byte[] v0 = p4.getBytes(p6);
        int v1 = 0;
        while (v1 < v0.length) {
            p5.append(37);
            p5.append("0123456789ABCDEF".charAt(((v0[v1] & 240) >> 4)));
            p5.append("0123456789ABCDEF".charAt((v0[v1] & 15)));
            v1++;
        }
        return;
    }

    public static String encode(String p7, String p8)
    {
        if ((p7 != null) && (p8 != null)) {
            "".getBytes(p8);
            StringBuilder v0_1 = new StringBuilder((p7.length() + 16));
            int v3 = -1;
            int v2 = 0;
            while (v2 < p7.length()) {
                char v1 = p7.charAt(v2);
                if (((v1 < 97) || (v1 > 122)) && (((v1 < 65) || (v1 > 90)) && (((v1 < 48) || (v1 > 57)) && (" .-*_\'(),<>=?@[]{}:~\"\\/;!".indexOf(v1) <= -1)))) {
                    if (v3 < 0) {
                        v3 = v2;
                    }
                } else {
                    if (v3 >= 0) {
                        com.phonegap.CallbackServer.convert(p7.substring(v3, v2), v0_1, p8);
                        v3 = -1;
                    }
                    if (v1 == 32) {
                        v0_1.append(32);
                    } else {
                        v0_1.append(v1);
                    }
                }
                v2++;
            }
            if (v3 >= 0) {
                com.phonegap.CallbackServer.convert(p7.substring(v3, p7.length()), v0_1, p8);
            }
            return v0_1.toString();
        } else {
            throw new NullPointerException();
        }
    }

    public void destroy()
    {
        this.stopServer();
        return;
    }

    public String getJavascript()
    {
        Throwable v1_3;
        if (this.javascript.size() != 0) {
            String v0_1 = ((String) this.javascript.remove(0));
            try {
                if (this.javascript.size() == 0) {
                    this.empty = 1;
                }
            } catch (Throwable v1_4) {
                throw v1_4;
            }
            v1_3 = v0_1;
        } else {
            v1_3 = 0;
        }
        return v1_3;
    }

    public int getPort()
    {
        return this.port;
    }

    public int getSize()
    {
        return this.javascript.size();
    }

    public String getToken()
    {
        return this.token;
    }

    public void init(String p3)
    {
        if ((p3 == null) || (p3.startsWith("file://"))) {
            if (android.net.Proxy.getDefaultHost() == null) {
                this.usePolling = 0;
                this.startServer();
            } else {
                this.usePolling = 1;
                this.stopServer();
            }
        } else {
            this.usePolling = 1;
            this.stopServer();
        }
        return;
    }

    public void restartServer()
    {
        this.stopServer();
        this.startServer();
        return;
    }

    public void run()
    {
        try {
            this.active = 1;
            java.net.ServerSocket v7_1 = new java.net.ServerSocket(0);
            this.port = v7_1.getLocalPort();
            this.token = java.util.UUID.randomUUID().toString();
        } catch (StringBuilder v9_27) {
            v9_27.printStackTrace();
            this.active = 0;
            return;
        }
        while (this.active) {
            java.net.Socket v0 = v7_1.accept();
            java.io.BufferedReader v8_1 = new java.io.BufferedReader(new java.io.InputStreamReader(v0.getInputStream()), 40);
            java.io.DataOutputStream v3_1 = new java.io.DataOutputStream(v0.getOutputStream());
            String v4 = v8_1.readLine();
            if ((this.active) && (v4 != null)) {
                String v6;
                if (!v4.contains("GET")) {
                    v6 = "HTTP/1.1 400 Bad Request\r\n\r\n ";
                } else {
                    String[] v5 = v4.split(" ");
                    if ((v5.length != 3) || (!v5[1].substring(1).equals(this.token))) {
                        v6 = "HTTP/1.1 403 Forbidden\r\n\r\n ";
                    } else {
                        while (this.empty) {
                            try {
                                this.wait(10000);
                                break;
                            } catch (StringBuilder v9) {
                            }
                        }
                        if (!this.active) {
                            v6 = "HTTP/1.1 503 Service Unavailable\r\n\r\n ";
                        } else {
                            if (!this.empty) {
                                v6 = "HTTP/1.1 200 OK\r\n\r\n";
                                String v2 = this.getJavascript();
                                if (v2 != null) {
                                    v6 = new StringBuilder().append("HTTP/1.1 200 OK\r\n\r\n").append(com.phonegap.CallbackServer.encode(v2, "UTF-8")).toString();
                                }
                            } else {
                                v6 = "HTTP/1.1 404 NO DATA\r\n\r\n ";
                            }
                        }
                    }
                }
                v3_1.writeBytes(v6);
                v3_1.flush();
            }
            v3_1.close();
            v8_1.close();
        }
        this.active = 0;
        return;
    }

    public void sendJavascript(String p2)
    {
        this.javascript.add(p2);
        try {
            this.empty = 0;
            this.notify();
            return;
        } catch (Throwable v0_2) {
            throw v0_2;
        }
    }

    public void startServer()
    {
        this.active = 0;
        this.serverThread = new Thread(this);
        this.serverThread.start();
        return;
    }

    public void stopServer()
    {
        try {
            if (this.active) {
                this.active = 0;
                this.notify();
            }
        } catch (Throwable v0_2) {
            throw v0_2;
        }
        return;
    }

    public boolean usePolling()
    {
        return this.usePolling;
    }
}
