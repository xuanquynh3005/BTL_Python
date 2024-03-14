package com.phonegap;
public class AudioPlayer implements android.media.MediaPlayer$OnCompletionListener, android.media.MediaPlayer$OnPreparedListener, android.media.MediaPlayer$OnErrorListener {
    private static int MEDIA_DURATION;
    private static int MEDIA_ERROR;
    private static int MEDIA_ERROR_ALREADY_RECORDING;
    private static int MEDIA_ERROR_PAUSE_STATE;
    private static int MEDIA_ERROR_PLAY_MODE_SET;
    private static int MEDIA_ERROR_RECORD_MODE_SET;
    private static int MEDIA_ERROR_RESUME_STATE;
    private static int MEDIA_ERROR_STARTING_PLAYBACK;
    private static int MEDIA_ERROR_STARTING_RECORDING;
    private static int MEDIA_ERROR_STOP_STATE;
    private static int MEDIA_NONE;
    private static int MEDIA_PAUSED;
    private static int MEDIA_RUNNING;
    private static int MEDIA_STARTING;
    private static int MEDIA_STATE;
    private static int MEDIA_STOPPED;
    private String audioFile;
    private float duration;
    private com.phonegap.AudioHandler handler;
    private String id;
    private android.media.MediaPlayer mPlayer;
    private boolean prepareOnly;
    private android.media.MediaRecorder recorder;
    private int state;
    private String tempFile;

    static AudioPlayer()
    {
        com.phonegap.AudioPlayer.MEDIA_NONE = 0;
        com.phonegap.AudioPlayer.MEDIA_STARTING = 1;
        com.phonegap.AudioPlayer.MEDIA_RUNNING = 2;
        com.phonegap.AudioPlayer.MEDIA_PAUSED = 3;
        com.phonegap.AudioPlayer.MEDIA_STOPPED = 4;
        com.phonegap.AudioPlayer.MEDIA_STATE = 1;
        com.phonegap.AudioPlayer.MEDIA_DURATION = 2;
        com.phonegap.AudioPlayer.MEDIA_ERROR = 9;
        com.phonegap.AudioPlayer.MEDIA_ERROR_PLAY_MODE_SET = 1;
        com.phonegap.AudioPlayer.MEDIA_ERROR_ALREADY_RECORDING = 2;
        com.phonegap.AudioPlayer.MEDIA_ERROR_STARTING_RECORDING = 3;
        com.phonegap.AudioPlayer.MEDIA_ERROR_RECORD_MODE_SET = 4;
        com.phonegap.AudioPlayer.MEDIA_ERROR_STARTING_PLAYBACK = 5;
        com.phonegap.AudioPlayer.MEDIA_ERROR_RESUME_STATE = 6;
        com.phonegap.AudioPlayer.MEDIA_ERROR_PAUSE_STATE = 7;
        com.phonegap.AudioPlayer.MEDIA_ERROR_STOP_STATE = 8;
        return;
    }

    public AudioPlayer(com.phonegap.AudioHandler p3, String p4)
    {
        this.state = com.phonegap.AudioPlayer.MEDIA_NONE;
        this.audioFile = 0;
        this.duration = -1082130432;
        this.recorder = 0;
        this.tempFile = 0;
        this.mPlayer = 0;
        this.prepareOnly = 0;
        this.handler = p3;
        this.id = p4;
        this.tempFile = new StringBuilder().append(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()).append("/tmprecording.mp3").toString();
        return;
    }

    private float getDurationInSeconds()
    {
        return (((float) this.mPlayer.getDuration()) / 1148846080);
    }

    private void setState(int p4)
    {
        if (this.state != p4) {
            this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_STATE).append(", ").append(p4).append(");").toString());
        }
        this.state = p4;
        return;
    }

    public void destroy()
    {
        if (this.mPlayer != null) {
            this.stopPlaying();
            this.mPlayer.release();
            this.mPlayer = 0;
        }
        if (this.recorder != null) {
            this.stopRecording();
            this.recorder.release();
            this.recorder = 0;
        }
        return;
    }

    public long getCurrentPosition()
    {
        if ((this.state != com.phonegap.AudioPlayer.MEDIA_RUNNING) && (this.state != com.phonegap.AudioPlayer.MEDIA_PAUSED)) {
            long v0_1 = -1;
        } else {
            v0_1 = ((long) this.mPlayer.getCurrentPosition());
        }
        return v0_1;
    }

    public float getDuration(String p2)
    {
        float v0_3;
        if (this.recorder == null) {
            if (this.mPlayer == null) {
                this.prepareOnly = 1;
                this.startPlaying(p2);
                v0_3 = this.duration;
            } else {
                v0_3 = this.duration;
            }
        } else {
            v0_3 = -1073741824;
        }
        return v0_3;
    }

    public boolean isStreaming(String p2)
    {
        int v0_2;
        if (!p2.contains("http://")) {
            v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        return v0_2;
    }

    public void moveFile(String p5)
    {
        new java.io.File(this.tempFile).renameTo(new java.io.File(new StringBuilder().append("/sdcard/").append(p5).toString()));
        return;
    }

    public void onCompletion(android.media.MediaPlayer p2)
    {
        this.setState(com.phonegap.AudioPlayer.MEDIA_STOPPED);
        return;
    }

    public boolean onError(android.media.MediaPlayer p4, int p5, int p6)
    {
        System.out.println(new StringBuilder().append("AudioPlayer.onError(").append(p5).append(", ").append(p6).append(")").toString());
        this.mPlayer.stop();
        this.mPlayer.release();
        this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(p5).append(");").toString());
        return 0;
    }

    public void onPrepared(android.media.MediaPlayer p4)
    {
        this.mPlayer.setOnCompletionListener(this);
        if (!this.prepareOnly) {
            this.mPlayer.start();
            this.setState(com.phonegap.AudioPlayer.MEDIA_RUNNING);
        }
        this.duration = this.getDurationInSeconds();
        this.prepareOnly = 0;
        this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_DURATION).append(",").append(this.duration).append(");").toString());
        return;
    }

    public void pausePlaying()
    {
        if (this.state != com.phonegap.AudioPlayer.MEDIA_RUNNING) {
            System.out.println(new StringBuilder().append("AudioPlayer Error: pausePlaying() called during invalid state: ").append(this.state).toString());
            this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(com.phonegap.AudioPlayer.MEDIA_ERROR_PAUSE_STATE).append(");").toString());
        } else {
            this.mPlayer.pause();
            this.setState(com.phonegap.AudioPlayer.MEDIA_PAUSED);
        }
        return;
    }

    public void seekToPlaying(int p2)
    {
        if (this.mPlayer != null) {
            this.mPlayer.seekTo(p2);
        }
        return;
    }

    public void startPlaying(String p10)
    {
        if (this.recorder == null) {
            if ((this.mPlayer != null) && (this.state != com.phonegap.AudioPlayer.MEDIA_STOPPED)) {
                if ((this.state != com.phonegap.AudioPlayer.MEDIA_PAUSED) && (this.state != com.phonegap.AudioPlayer.MEDIA_STARTING)) {
                    System.out.println(new StringBuilder().append("AudioPlayer Error: startPlaying() called during invalid state: ").append(this.state).toString());
                    this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(com.phonegap.AudioPlayer.MEDIA_ERROR_RESUME_STATE).append(");").toString());
                } else {
                    this.mPlayer.start();
                    this.setState(com.phonegap.AudioPlayer.MEDIA_RUNNING);
                }
            } else {
                try {
                    if (this.mPlayer == null) {
                        this.mPlayer = new android.media.MediaPlayer();
                    } else {
                        this.mPlayer.reset();
                    }
                } catch (float v0_6) {
                    v0_6.printStackTrace();
                    this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(com.phonegap.AudioPlayer.MEDIA_ERROR_STARTING_PLAYBACK).append(");").toString());
                }
                this.audioFile = p10;
                if (!this.isStreaming(p10)) {
                    if (!p10.startsWith("/android_asset/")) {
                        this.mPlayer.setDataSource(new StringBuilder().append("/sdcard/").append(p10).toString());
                    } else {
                        android.content.res.AssetFileDescriptor v8 = this.handler.ctx.getBaseContext().getAssets().openFd(p10.substring(15));
                        this.mPlayer.setDataSource(v8.getFileDescriptor(), v8.getStartOffset(), v8.getLength());
                    }
                    this.setState(com.phonegap.AudioPlayer.MEDIA_STARTING);
                    this.mPlayer.setOnPreparedListener(this);
                    this.mPlayer.prepare();
                    this.duration = this.getDurationInSeconds();
                } else {
                    this.mPlayer.setDataSource(p10);
                    this.mPlayer.setAudioStreamType(3);
                    this.setState(com.phonegap.AudioPlayer.MEDIA_STARTING);
                    this.mPlayer.setOnPreparedListener(this);
                    this.mPlayer.prepareAsync();
                }
            }
        } else {
            System.out.println("AudioPlayer Error: Can\'t play in record mode.");
            this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(com.phonegap.AudioPlayer.MEDIA_ERROR_RECORD_MODE_SET).append(");").toString());
        }
        return;
    }

    public void startRecording(String p5)
    {
        if (this.mPlayer == null) {
            if (this.recorder != null) {
                System.out.println("AudioPlayer Error: Already recording.");
                this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(com.phonegap.AudioPlayer.MEDIA_ERROR_ALREADY_RECORDING).append(");").toString());
            } else {
                this.audioFile = p5;
                this.recorder = new android.media.MediaRecorder();
                this.recorder.setAudioSource(1);
                this.recorder.setOutputFormat(0);
                this.recorder.setAudioEncoder(0);
                this.recorder.setOutputFile(this.tempFile);
                try {
                    this.recorder.prepare();
                    this.recorder.start();
                    this.setState(com.phonegap.AudioPlayer.MEDIA_RUNNING);
                } catch (com.phonegap.AudioHandler v1_13) {
                    v1_13.printStackTrace();
                    this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(com.phonegap.AudioPlayer.MEDIA_ERROR_STARTING_RECORDING).append(");").toString());
                } catch (com.phonegap.AudioHandler v1_12) {
                    v1_12.printStackTrace();
                }
            }
        } else {
            System.out.println("AudioPlayer Error: Can\'t record in play mode.");
            this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(com.phonegap.AudioPlayer.MEDIA_ERROR_PLAY_MODE_SET).append(");").toString());
        }
        return;
    }

    public void stopPlaying()
    {
        if ((this.state != com.phonegap.AudioPlayer.MEDIA_RUNNING) && (this.state != com.phonegap.AudioPlayer.MEDIA_PAUSED)) {
            System.out.println(new StringBuilder().append("AudioPlayer Error: stopPlaying() called during invalid state: ").append(this.state).toString());
            this.handler.sendJavascript(new StringBuilder().append("PhoneGap.Media.onStatus(\'").append(this.id).append("\', ").append(com.phonegap.AudioPlayer.MEDIA_ERROR).append(", ").append(com.phonegap.AudioPlayer.MEDIA_ERROR_STOP_STATE).append(");").toString());
        } else {
            this.mPlayer.stop();
            this.setState(com.phonegap.AudioPlayer.MEDIA_STOPPED);
        }
        return;
    }

    public void stopRecording()
    {
        if (this.recorder != null) {
            try {
                if (this.state == com.phonegap.AudioPlayer.MEDIA_RUNNING) {
                    this.recorder.stop();
                    this.setState(com.phonegap.AudioPlayer.MEDIA_STOPPED);
                }
            } catch (String v1_1) {
                v1_1.printStackTrace();
            }
            this.moveFile(this.audioFile);
        }
        return;
    }
}
