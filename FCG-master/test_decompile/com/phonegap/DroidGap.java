package com.phonegap;
public class DroidGap extends com.phonegap.api.PhonegapActivity {
    protected com.phonegap.api.Plugin activityResultCallback;
    protected boolean activityResultKeepRunning;
    protected android.webkit.WebView appView;
    private String baseUrl;
    public boolean bound;
    public com.phonegap.CallbackServer callbackServer;
    protected boolean cancelLoadUrl;
    protected boolean clearHistory;
    protected boolean hideLoadingDialogOnPageLoad;
    protected boolean keepRunning;
    protected boolean loadInWebView;
    private int loadUrlTimeout;
    protected int loadUrlTimeoutValue;
    protected com.phonegap.api.PluginManager pluginManager;
    protected android.widget.LinearLayout root;
    protected int splashscreen;
    private String url;
    private String urlFile;
    protected android.webkit.WebViewClient webViewClient;

    public DroidGap()
    {
        this.bound = 0;
        this.cancelLoadUrl = 0;
        this.clearHistory = 0;
        this.activityResultCallback = 0;
        this.loadUrlTimeout = 0;
        this.hideLoadingDialogOnPageLoad = 0;
        this.loadInWebView = 0;
        this.splashscreen = 0;
        this.loadUrlTimeoutValue = 20000;
        this.keepRunning = 1;
        return;
    }

    static synthetic void access$000(com.phonegap.DroidGap p0)
    {
        p0.handleActivityParameters();
        return;
    }

    static synthetic int access$100(com.phonegap.DroidGap p1)
    {
        return p1.loadUrlTimeout;
    }

    static synthetic int access$108(com.phonegap.DroidGap p2)
    {
        int v0 = p2.loadUrlTimeout;
        p2.loadUrlTimeout = (v0 + 1);
        return v0;
    }

    static synthetic String access$200(com.phonegap.DroidGap p1, String p2)
    {
        return p1.getUrlFile(p2);
    }

    static synthetic String access$300(com.phonegap.DroidGap p1)
    {
        return p1.urlFile;
    }

    static synthetic String access$400(com.phonegap.DroidGap p1)
    {
        return p1.baseUrl;
    }

    private void bindBrowser(android.webkit.WebView p3)
    {
        this.callbackServer = new com.phonegap.CallbackServer();
        this.pluginManager = new com.phonegap.api.PluginManager(p3, this);
        this.addService("App", "com.phonegap.App");
        this.addService("Geolocation", "com.phonegap.GeoBroker");
        this.addService("Device", "com.phonegap.Device");
        this.addService("Accelerometer", "com.phonegap.AccelListener");
        this.addService("Compass", "com.phonegap.CompassListener");
        this.addService("Media", "com.phonegap.AudioHandler");
        this.addService("Camera", "com.phonegap.CameraLauncher");
        this.addService("Contacts", "com.phonegap.ContactManager");
        this.addService("Crypto", "com.phonegap.CryptoHandler");
        this.addService("File", "com.phonegap.FileUtils");
        this.addService("Location", "com.phonegap.GeoBroker");
        this.addService("Network Status", "com.phonegap.NetworkManager");
        this.addService("Notification", "com.phonegap.Notification");
        this.addService("Storage", "com.phonegap.Storage");
        this.addService("Temperature", "com.phonegap.TempListener");
        this.addService("FileTransfer", "com.phonegap.FileTransfer");
        this.addService("Capture", "com.phonegap.Capture");
        return;
    }

    private String getUrlFile(String p5)
    {
        int v0 = p5.indexOf("#");
        int v1 = p5.indexOf("?");
        if (v0 < 0) {
            v0 = p5.length();
        }
        if (v1 < 0) {
            v1 = p5.length();
        }
        int v2;
        if (v0 >= v1) {
            v2 = v1;
        } else {
            v2 = v0;
        }
        return p5.substring(0, v2);
    }

    private void handleActivityParameters()
    {
        if (this.appView == null) {
            this.init();
        }
        this.splashscreen = this.getIntegerProperty("splashscreen", 0);
        if (this.splashscreen != 0) {
            this.root.setBackgroundResource(this.splashscreen);
        }
        this.hideLoadingDialogOnPageLoad = this.getBooleanProperty("hideLoadingDialogOnPageLoad", 0);
        this.loadInWebView = this.getBooleanProperty("loadInWebView", 0);
        int v0 = this.getIntegerProperty("loadUrlTimeoutValue", 0);
        if (v0 > 0) {
            this.loadUrlTimeoutValue = v0;
        }
        this.keepRunning = this.getBooleanProperty("keepRunning", 1);
        return;
    }

    public void addService(String p2, String p3)
    {
        this.pluginManager.addService(p2, p3);
        return;
    }

    public void cancelLoadUrl()
    {
        this.cancelLoadUrl = 1;
        return;
    }

    public void clearCache()
    {
        if (this.appView == null) {
            this.init();
        }
        this.appView.clearCache(1);
        return;
    }

    public void clearHistory()
    {
        this.clearHistory = 1;
        if (this.appView != null) {
            this.appView.clearHistory();
        }
        return;
    }

    public void displayError(String p8, String p9, String p10, boolean p11)
    {
        this.runOnUiThread(new com.phonegap.DroidGap$5(this, this, p9, p8, p10, p11));
        return;
    }

    public boolean getBooleanProperty(String p4, boolean p5)
    {
        boolean v2_1;
        android.os.Bundle v0 = this.getIntent().getExtras();
        if (v0 != null) {
            Boolean v1_1 = ((Boolean) v0.get(p4));
            if (v1_1 != null) {
                v2_1 = v1_1.booleanValue();
            } else {
                v2_1 = p5;
            }
        } else {
            v2_1 = p5;
        }
        return v2_1;
    }

    public double getDoubleProperty(String p5, double p6)
    {
        double v2_1;
        android.os.Bundle v0 = this.getIntent().getExtras();
        if (v0 != null) {
            Double v1_1 = ((Double) v0.get(p5));
            if (v1_1 != null) {
                v2_1 = v1_1.doubleValue();
            } else {
                v2_1 = p6;
            }
        } else {
            v2_1 = p6;
        }
        return v2_1;
    }

    public int getIntegerProperty(String p4, int p5)
    {
        int v2_1;
        android.os.Bundle v0 = this.getIntent().getExtras();
        if (v0 != null) {
            Integer v1_1 = ((Integer) v0.get(p4));
            if (v1_1 != null) {
                v2_1 = v1_1.intValue();
            } else {
                v2_1 = p5;
            }
        } else {
            v2_1 = p5;
        }
        return v2_1;
    }

    public String getStringProperty(String p4, String p5)
    {
        String v2_1;
        android.os.Bundle v0 = this.getIntent().getExtras();
        if (v0 != null) {
            String v1 = v0.getString(p4);
            if (v1 != null) {
                v2_1 = v1;
            } else {
                v2_1 = p5;
            }
        } else {
            v2_1 = p5;
        }
        return v2_1;
    }

    public void init()
    {
        this.appView = new android.webkit.WebView(this);
        this.appView.setId(100);
        this.appView.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-1, -1, 1065353216));
        com.phonegap.WebViewReflect.checkCompatibility();
        if (!android.os.Build$VERSION.RELEASE.startsWith("1.")) {
            this.appView.setWebChromeClient(new com.phonegap.DroidGap$EclairClient(this, this));
        } else {
            this.appView.setWebChromeClient(new com.phonegap.DroidGap$GapClient(this, this));
        }
        this.setWebViewClient(this.appView, new com.phonegap.DroidGap$GapViewClient(this, this));
        this.appView.setInitialScale(100);
        this.appView.setVerticalScrollBarEnabled(0);
        this.appView.requestFocusFromTouch();
        android.webkit.WebSettings v2 = this.appView.getSettings();
        v2.setJavaScriptEnabled(1);
        v2.setJavaScriptCanOpenWindowsAutomatically(1);
        v2.setLayoutAlgorithm(android.webkit.WebSettings$LayoutAlgorithm.NORMAL);
        com.phonegap.WebViewReflect.setStorage(v2, 1, new StringBuilder().append("/data/data/").append(this.getClass().getPackage().getName()).append("/app_database/").toString());
        com.phonegap.WebViewReflect.setDomStorage(v2);
        com.phonegap.WebViewReflect.setGeolocationEnabled(v2, 1);
        this.bindBrowser(this.appView);
        this.appView.setVisibility(4);
        this.root.addView(this.appView);
        this.setContentView(this.root);
        this.cancelLoadUrl = 0;
        String v3 = this.getStringProperty("url", 0);
        if (v3 != null) {
            System.out.println(new StringBuilder().append("Loading initial URL=").append(v3).toString());
            this.loadUrl(v3);
        }
        return;
    }

    public void loadUrl(String p6)
    {
        System.out.println(new StringBuilder().append("loadUrl(").append(p6).append(")").toString());
        this.urlFile = this.getUrlFile(p6);
        this.url = p6;
        int v0 = p6.lastIndexOf(47);
        if (v0 <= 0) {
            this.baseUrl = this.url;
        } else {
            this.baseUrl = p6.substring(0, v0);
        }
        System.out.println(new StringBuilder().append("url=").append(p6).append(" baseUrl=").append(this.baseUrl).toString());
        this.runOnUiThread(new com.phonegap.DroidGap$1(this, this, p6));
        return;
    }

    public void loadUrl(String p7, int p8)
    {
        System.out.println(new StringBuilder().append("loadUrl(").append(p7).append(",").append(p8).append(")").toString());
        this.runOnUiThread(new com.phonegap.DroidGap$2(this, this));
        new Thread(new com.phonegap.DroidGap$3(this, p8, this, p7)).start();
        return;
    }

    protected void onActivityResult(int p2, int p3, android.content.Intent p4)
    {
        super.onActivityResult(p2, p3, p4);
        com.phonegap.api.Plugin v0 = this.activityResultCallback;
        if (v0 != null) {
            v0.onActivityResult(p2, p3, p4);
        }
        return;
    }

    public void onConfigurationChanged(android.content.res.Configuration p1)
    {
        super.onConfigurationChanged(p1);
        return;
    }

    public void onCreate(android.os.Bundle p10)
    {
        super.onCreate(p10);
        this.getWindow().requestFeature(1);
        this.getWindow().setFlags(2048, 2048);
        android.view.Display v1 = this.getWindowManager().getDefaultDisplay();
        this.root = new com.phonegap.DroidGap$LinearLayoutSoftKeyboardDetect(this, this, v1.getWidth(), v1.getHeight());
        this.root.setOrientation(1);
        this.root.setBackgroundColor(-16777216);
        this.root.setLayoutParams(new android.widget.LinearLayout$LayoutParams(-1, -1, 0));
        android.os.Bundle v0 = this.getIntent().getExtras();
        if ((v0 != null) && (v0.getString("url") != null)) {
            this.init();
        }
        this.setVolumeControlStream(3);
        return;
    }

    public void onDestroy()
    {
        super.onDestroy();
        if (this.appView != null) {
            this.appView.loadUrl("javascript:try{PhoneGap.onPause.fire();}catch(e){};");
            this.appView.loadUrl("javascript:try{PhoneGap.onDestroy.fire();}catch(e){};");
            this.appView.loadUrl("about:blank");
            this.pluginManager.onDestroy();
        }
        return;
    }

    public boolean onKeyDown(int p4, android.view.KeyEvent p5)
    {
        int v0_2;
        if (this.appView != null) {
            if (p4 != 4) {
                if (p4 != 82) {
                    if (p4 != 84) {
                        v0_2 = 0;
                    } else {
                        this.appView.loadUrl("javascript:PhoneGap.fireEvent(\'searchbutton\');");
                        v0_2 = 1;
                    }
                } else {
                    this.appView.loadUrl("javascript:PhoneGap.fireEvent(\'menubutton\');");
                    v0_2 = 1;
                }
            } else {
                if (!this.bound) {
                    if (!this.appView.canGoBack()) {
                        v0_2 = super.onKeyDown(p4, p5);
                    } else {
                        this.appView.goBack();
                        v0_2 = 1;
                    }
                } else {
                    this.appView.loadUrl("javascript:PhoneGap.fireEvent(\'backbutton\');");
                    v0_2 = 1;
                }
            }
        } else {
            v0_2 = super.onKeyDown(p4, p5);
        }
        return v0_2;
    }

    protected void onNewIntent(android.content.Intent p2)
    {
        super.onNewIntent(p2);
        this.pluginManager.onNewIntent(p2);
        return;
    }

    protected void onPause()
    {
        super.onPause();
        if (this.appView != null) {
            this.appView.loadUrl("javascript:try{PhoneGap.onPause.fire();}catch(e){};");
            this.pluginManager.onPause(this.keepRunning);
            if (!this.keepRunning) {
                this.appView.pauseTimers();
            }
        }
        return;
    }

    public void onReceivedError(int p7, String p8, String p9)
    {
        String v0 = this.getStringProperty("errorUrl", 0);
        if ((v0 == null) || ((!v0.startsWith("file://")) || (p9.equals(v0)))) {
            this.appView.loadUrl("about:blank");
            this.displayError("Application Error", new StringBuilder().append(p8).append(" (").append(p9).append(")").toString(), "OK", 1);
        } else {
            this.runOnUiThread(new com.phonegap.DroidGap$4(this, this, v0));
        }
        return;
    }

    protected void onResume()
    {
        super.onResume();
        if (this.appView != null) {
            int v1_1;
            this.appView.loadUrl("javascript:try{PhoneGap.onResume.fire();}catch(e){};");
            if ((!this.keepRunning) && (!this.activityResultKeepRunning)) {
                v1_1 = 0;
            } else {
                v1_1 = 1;
            }
            this.pluginManager.onResume(v1_1);
            if ((!this.keepRunning) || (this.activityResultKeepRunning)) {
                if (this.activityResultKeepRunning) {
                    this.keepRunning = this.activityResultKeepRunning;
                    this.activityResultKeepRunning = 0;
                }
                this.appView.resumeTimers();
            }
        }
        return;
    }

    public void sendJavascript(String p2)
    {
        this.callbackServer.sendJavascript(p2);
        return;
    }

    public void setBooleanProperty(String p2, boolean p3)
    {
        this.getIntent().putExtra(p2, p3);
        return;
    }

    public void setDoubleProperty(String p2, double p3)
    {
        this.getIntent().putExtra(p2, p3);
        return;
    }

    public void setIntegerProperty(String p2, int p3)
    {
        this.getIntent().putExtra(p2, p3);
        return;
    }

    public void setStringProperty(String p2, String p3)
    {
        this.getIntent().putExtra(p2, p3);
        return;
    }

    protected void setWebViewClient(android.webkit.WebView p1, android.webkit.WebViewClient p2)
    {
        this.webViewClient = p2;
        p1.setWebViewClient(p2);
        return;
    }

    public void startActivityForResult(android.content.Intent p4, int p5)
    {
        System.out.println(new StringBuilder().append("startActivityForResult(intent,").append(p5).append(")").toString());
        if (p5 != -1) {
            throw new RuntimeException("PhoneGap Exception: Call startActivityForResult(Command, Intent) instead.");
        } else {
            super.startActivityForResult(p4, p5);
            return;
        }
    }

    public void startActivityForResult(com.phonegap.api.Plugin p2, android.content.Intent p3, int p4)
    {
        this.activityResultCallback = p2;
        this.activityResultKeepRunning = this.keepRunning;
        if (p2 != null) {
            this.keepRunning = 0;
        }
        super.startActivityForResult(p3, p4);
        return;
    }
}
