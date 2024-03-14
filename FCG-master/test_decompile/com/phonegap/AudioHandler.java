package com.phonegap;
public class AudioHandler extends com.phonegap.api.Plugin {
    java.util.HashMap players;

    public AudioHandler()
    {
        this.players = new java.util.HashMap();
        return;
    }

    private boolean release(String p3)
    {
        int v1_1;
        if (this.players.containsKey(p3)) {
            com.phonegap.AudioPlayer v0_1 = ((com.phonegap.AudioPlayer) this.players.get(p3));
            this.players.remove(p3);
            v0_1.destroy();
            v1_1 = 1;
        } else {
            v1_1 = 0;
        }
        return v1_1;
    }

    public com.phonegap.api.PluginResult execute(String p8, org.json.JSONArray p9, String p10)
    {
        com.phonegap.api.PluginResult$Status v4 = com.phonegap.api.PluginResult$Status.OK;
        try {
            com.phonegap.api.PluginResult v5_2;
            if (!p8.equals("startRecordingAudio")) {
                if (!p8.equals("stopRecordingAudio")) {
                    if (!p8.equals("startPlayingAudio")) {
                        if (!p8.equals("seekToAudio")) {
                            if (!p8.equals("pausePlayingAudio")) {
                                if (!p8.equals("stopPlayingAudio")) {
                                    if (!p8.equals("getCurrentPositionAudio")) {
                                        if (!p8.equals("getDurationAudio")) {
                                            if (!p8.equals("release")) {
                                                v5_2 = new com.phonegap.api.PluginResult(v4, "");
                                            } else {
                                                v5_2 = new com.phonegap.api.PluginResult(v4, this.release(p9.getString(0)));
                                            }
                                        } else {
                                            v5_2 = new com.phonegap.api.PluginResult(v4, this.getDurationAudio(p9.getString(0), p9.getString(1)));
                                        }
                                    } else {
                                        v5_2 = new com.phonegap.api.PluginResult(v4, this.getCurrentPositionAudio(p9.getString(0)));
                                    }
                                } else {
                                    this.stopPlayingAudio(p9.getString(0));
                                }
                            } else {
                                this.pausePlayingAudio(p9.getString(0));
                            }
                        } else {
                            this.seekToAudio(p9.getString(0), p9.getInt(1));
                        }
                    } else {
                        this.startPlayingAudio(p9.getString(0), p9.getString(1));
                    }
                } else {
                    this.stopRecordingAudio(p9.getString(0));
                }
            } else {
                this.startRecordingAudio(p9.getString(0), p9.getString(1));
            }
        } catch (com.phonegap.api.PluginResult v5_42) {
            v5_42.printStackTrace();
            v5_2 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v5_2;
    }

    public int getAudioOutputDevice()
    {
        int v1_1;
        android.media.AudioManager v0_1 = ((android.media.AudioManager) this.ctx.getSystemService("audio"));
        if (v0_1.getRouting(0) != 1) {
            if (v0_1.getRouting(0) != 2) {
                v1_1 = -1;
            } else {
                v1_1 = 2;
            }
        } else {
            v1_1 = 1;
        }
        return v1_1;
    }

    public float getCurrentPositionAudio(String p4)
    {
        int v1_1;
        com.phonegap.AudioPlayer v0_1 = ((com.phonegap.AudioPlayer) this.players.get(p4));
        if (v0_1 == null) {
            v1_1 = -1082130432;
        } else {
            v1_1 = (((float) v0_1.getCurrentPosition()) / 1148846080);
        }
        return v1_1;
    }

    public float getDurationAudio(String p3, String p4)
    {
        float v1_1;
        com.phonegap.AudioPlayer v0_1 = ((com.phonegap.AudioPlayer) this.players.get(p3));
        if (v0_1 == null) {
            com.phonegap.AudioPlayer v0_3 = new com.phonegap.AudioPlayer(this, p3);
            this.players.put(p3, v0_3);
            v1_1 = v0_3.getDuration(p4);
        } else {
            v1_1 = v0_1.getDuration(p4);
        }
        return v1_1;
    }

    public boolean isSynch(String p3)
    {
        int v0_1;
        if (!p3.equals("getCurrentPositionAudio")) {
            if (!p3.equals("getDurationAudio")) {
                v0_1 = 0;
            } else {
                v0_1 = 1;
            }
        } else {
            v0_1 = 1;
        }
        return v0_1;
    }

    public void onDestroy()
    {
        java.util.Iterator v2 = this.players.entrySet().iterator();
        while (v2.hasNext()) {
            ((com.phonegap.AudioPlayer) ((java.util.Map$Entry) v2.next()).getValue()).destroy();
        }
        this.players.clear();
        return;
    }

    public void pausePlayingAudio(String p3)
    {
        com.phonegap.AudioPlayer v0_1 = ((com.phonegap.AudioPlayer) this.players.get(p3));
        if (v0_1 != null) {
            v0_1.pausePlaying();
        }
        return;
    }

    public void seekToAudio(String p3, int p4)
    {
        com.phonegap.AudioPlayer v0_1 = ((com.phonegap.AudioPlayer) this.players.get(p3));
        if (v0_1 != null) {
            v0_1.seekToPlaying(p4);
        }
        return;
    }

    public void setAudioOutputDevice(int p8)
    {
        android.media.AudioManager v0_1 = ((android.media.AudioManager) this.ctx.getSystemService("audio"));
        if (p8 != 2) {
            if (p8 != 1) {
                System.out.println("AudioHandler.setAudioOutputDevice() Error: Unknown output device.");
            } else {
                v0_1.setRouting(0, 1, -1);
            }
        } else {
            v0_1.setRouting(0, 2, -1);
        }
        return;
    }

    public void startPlayingAudio(String p3, String p4)
    {
        com.phonegap.AudioPlayer v0_1 = ((com.phonegap.AudioPlayer) this.players.get(p3));
        if (v0_1 == null) {
            v0_1 = new com.phonegap.AudioPlayer(this, p3);
            this.players.put(p3, v0_1);
        }
        v0_1.startPlaying(p4);
        return;
    }

    public void startRecordingAudio(String p3, String p4)
    {
        if (!this.players.containsKey(p3)) {
            com.phonegap.AudioPlayer v0_1 = new com.phonegap.AudioPlayer(this, p3);
            this.players.put(p3, v0_1);
            v0_1.startRecording(p4);
        }
        return;
    }

    public void stopPlayingAudio(String p3)
    {
        com.phonegap.AudioPlayer v0_1 = ((com.phonegap.AudioPlayer) this.players.get(p3));
        if (v0_1 != null) {
            v0_1.stopPlaying();
        }
        return;
    }

    public void stopRecordingAudio(String p3)
    {
        com.phonegap.AudioPlayer v0_1 = ((com.phonegap.AudioPlayer) this.players.get(p3));
        if (v0_1 != null) {
            v0_1.stopRecording();
            this.players.remove(p3);
        }
        return;
    }
}
