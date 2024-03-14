package com.phonegap;
public class FileUtils extends com.phonegap.api.Plugin {
    public static int ABORT_ERR = 0;
    public static int APPLICATION = 0;
    public static int ENCODING_ERR = 0;
    public static int INVALID_MODIFICATION_ERR = 0;
    public static int INVALID_STATE_ERR = 0;
    private static final String LOG_TAG = "FileUtils";
    public static int NOT_FOUND_ERR;
    public static int NOT_READABLE_ERR;
    public static int NO_MODIFICATION_ALLOWED_ERR;
    public static int PATH_EXISTS_ERR;
    public static int PERSISTENT;
    public static int QUOTA_EXCEEDED_ERR;
    public static int RESOURCE;
    public static int SECURITY_ERR;
    public static int SYNTAX_ERR;
    public static int TEMPORARY;
    public static int TYPE_MISMATCH_ERR;
    java.io.FileReader f_in;
    java.io.FileWriter f_out;

    static FileUtils()
    {
        com.phonegap.FileUtils.NOT_FOUND_ERR = 1;
        com.phonegap.FileUtils.SECURITY_ERR = 2;
        com.phonegap.FileUtils.ABORT_ERR = 3;
        com.phonegap.FileUtils.NOT_READABLE_ERR = 4;
        com.phonegap.FileUtils.ENCODING_ERR = 5;
        com.phonegap.FileUtils.NO_MODIFICATION_ALLOWED_ERR = 6;
        com.phonegap.FileUtils.INVALID_STATE_ERR = 7;
        com.phonegap.FileUtils.SYNTAX_ERR = 8;
        com.phonegap.FileUtils.INVALID_MODIFICATION_ERR = 9;
        com.phonegap.FileUtils.QUOTA_EXCEEDED_ERR = 10;
        com.phonegap.FileUtils.TYPE_MISMATCH_ERR = 11;
        com.phonegap.FileUtils.PATH_EXISTS_ERR = 12;
        com.phonegap.FileUtils.TEMPORARY = 0;
        com.phonegap.FileUtils.PERSISTENT = 1;
        com.phonegap.FileUtils.RESOURCE = 2;
        com.phonegap.FileUtils.APPLICATION = 3;
        return;
    }

    public FileUtils()
    {
        return;
    }

    private boolean atRootDirectory(String p3)
    {
        if ((!p3.equals(new StringBuilder().append(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()).append("/Android/data/").append(this.ctx.getPackageName()).append("/cache").toString())) && (!p3.equals(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()))) {
            int v0_10 = 0;
        } else {
            v0_10 = 1;
        }
        return v0_10;
    }

    private org.json.JSONObject copyDirectory(java.io.File p8, java.io.File p9)
    {
        if ((!p9.exists()) || (!p9.isFile())) {
            if (!this.isCopyOnItself(p8.getAbsolutePath(), p9.getAbsolutePath())) {
                if ((p9.exists()) || (p9.mkdir())) {
                    java.io.File[] v0 = p8.listFiles();
                    int v4 = v0.length;
                    int v3 = 0;
                    while (v3 < v4) {
                        java.io.File v2 = v0[v3];
                        if (!v2.isDirectory()) {
                            this.copyFile(v2, new java.io.File(new StringBuilder().append(p9.getAbsoluteFile()).append(java.io.File.separator).append(v2.getName()).toString()));
                        } else {
                            this.copyDirectory(v2, p9);
                        }
                        v3++;
                    }
                    return this.getEntry(p9);
                } else {
                    throw new com.phonegap.file.NoModificationAllowedException("Couldn\'t create the destination direcotry");
                }
            } else {
                throw new com.phonegap.file.InvalidModificationException("Can\'t copy itself into itself");
            }
        } else {
            throw new com.phonegap.file.InvalidModificationException("Can\'t rename a file to a directory");
        }
    }

    private org.json.JSONObject copyFile(java.io.File p7, java.io.File p8)
    {
        if ((!p8.exists()) || (!p8.isDirectory())) {
            java.nio.channels.FileChannel v0 = new java.io.FileInputStream(p7).getChannel();
            java.nio.channels.FileChannel v5 = new java.io.FileOutputStream(p8).getChannel();
            v0.transferTo(0, v0.size(), v5);
            v0.close();
            v5.close();
            return this.getEntry(p8);
        } else {
            throw new com.phonegap.file.InvalidModificationException("Can\'t rename a file to a directory");
        }
    }

    private java.io.File createDestination(String p4, java.io.File p5, java.io.File p6)
    {
        if (("null".equals(p4)) || ("".equals(p4))) {
            p4 = 0;
        }
        java.io.File v0_1;
        if (p4 == 0) {
            v0_1 = new java.io.File(new StringBuilder().append(p6.getAbsolutePath()).append(java.io.File.separator).append(p5.getName()).toString());
        } else {
            v0_1 = new java.io.File(new StringBuilder().append(p6.getAbsolutePath()).append(java.io.File.separator).append(p4).toString());
        }
        return v0_1;
    }

    private java.io.File createFileObject(String p4, String p5)
    {
        java.io.File v0_0;
        if (!p5.startsWith("/")) {
            v0_0 = new java.io.File(new StringBuilder().append(p4).append(java.io.File.separator).append(p5).toString());
        } else {
            v0_0 = new java.io.File(p5);
        }
        return v0_0;
    }

    private org.json.JSONObject getEntry(java.io.File p4)
    {
        org.json.JSONObject v0_1 = new org.json.JSONObject();
        v0_1.put("isFile", p4.isFile());
        v0_1.put("isDirectory", p4.isDirectory());
        v0_1.put("name", p4.getName());
        v0_1.put("fullPath", p4.getAbsolutePath());
        return v0_1;
    }

    private org.json.JSONObject getEntry(String p2)
    {
        return this.getEntry(new java.io.File(p2));
    }

    private org.json.JSONObject getFile(String p6, String p7, org.json.JSONObject p8, boolean p9)
    {
        boolean v0 = 0;
        boolean v1 = 0;
        if (p8 != null) {
            v0 = p8.optBoolean("create");
            if (v0) {
                v1 = p8.optBoolean("exclusive");
            }
        }
        if (!p7.contains(":")) {
            java.io.File v2 = this.createFileObject(p6, p7);
            if (!v0) {
                if (v2.exists()) {
                    if (!p9) {
                        if (v2.isDirectory()) {
                            throw new com.phonegap.file.TypeMismatchException("path doesn\'t exist or is directory");
                        }
                    } else {
                        if (v2.isFile()) {
                            throw new com.phonegap.file.TypeMismatchException("path doesn\'t exist or is file");
                        }
                    }
                } else {
                    throw new java.io.FileNotFoundException("path does not exist");
                }
            } else {
                if ((!v1) || (!v2.exists())) {
                    if (!p9) {
                        v2.createNewFile();
                    } else {
                        v2.mkdir();
                    }
                    if (!v2.exists()) {
                        throw new com.phonegap.file.FileExistsException("create fails");
                    }
                } else {
                    throw new com.phonegap.file.FileExistsException("create/exclusive fails");
                }
            }
            return this.getEntry(v2);
        } else {
            throw new com.phonegap.file.EncodingException("This file has a : in it\'s name");
        }
    }

    private org.json.JSONObject getFileMetadata(String p6)
    {
        java.io.File v0_1 = new java.io.File(p6);
        if (v0_1.exists()) {
            org.json.JSONObject v1_1 = new org.json.JSONObject();
            v1_1.put("size", v0_1.length());
            v1_1.put("type", com.phonegap.FileUtils.getMimeType(p6));
            v1_1.put("name", v0_1.getName());
            v1_1.put("fullPath", v0_1.getAbsolutePath());
            v1_1.put("lastModifiedDate", v0_1.lastModified());
            return v1_1;
        } else {
            throw new java.io.FileNotFoundException(new StringBuilder().append("File: ").append(p6).append(" does not exist.").toString());
        }
    }

    private org.json.JSONObject getMetadata(String p6)
    {
        java.io.File v0_1 = new java.io.File(p6);
        if (v0_1.exists()) {
            org.json.JSONObject v1_1 = new org.json.JSONObject();
            v1_1.put("modificationTime", v0_1.lastModified());
            return v1_1;
        } else {
            throw new java.io.FileNotFoundException("Failed to find file in getMetadata");
        }
    }

    public static String getMimeType(String p2)
    {
        return android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(android.webkit.MimeTypeMap.getFileExtensionFromUrl(p2));
    }

    private org.json.JSONObject getParent(String p2)
    {
        org.json.JSONObject v0_1;
        if (!this.atRootDirectory(p2)) {
            v0_1 = this.getEntry(new java.io.File(p2).getParent());
        } else {
            v0_1 = this.getEntry(p2);
        }
        return v0_1;
    }

    private java.io.InputStream getPathFromUri(String p3)
    {
        java.io.FileInputStream v1_2;
        if (!p3.startsWith("content")) {
            v1_2 = new java.io.FileInputStream(p3);
        } else {
            v1_2 = this.ctx.getContentResolver().openInputStream(android.net.Uri.parse(p3));
        }
        return v1_2;
    }

    private boolean isCopyOnItself(String p4, String p5)
    {
        if ((!p5.startsWith(p4)) || (p5.indexOf(java.io.File.separator, (p4.length() - 1)) == -1)) {
            int v0_0 = 0;
        } else {
            v0_0 = 1;
        }
        return v0_0;
    }

    private org.json.JSONObject moveDirectory(java.io.File p3, java.io.File p4)
    {
        if ((!p4.exists()) || (!p4.isFile())) {
            if (!this.isCopyOnItself(p3.getAbsolutePath(), p4.getAbsolutePath())) {
                if ((!p4.exists()) || (p4.list().length <= 0)) {
                    // Both branches of the condition point to the same code.
                    // if (p3.renameTo(p4)) {
                        return this.getEntry(p4);
                    // }
                } else {
                    throw new com.phonegap.file.InvalidModificationException("directory is not empty");
                }
            } else {
                throw new com.phonegap.file.InvalidModificationException("Can\'t move itself into itself");
            }
        } else {
            throw new com.phonegap.file.InvalidModificationException("Can\'t rename a file to a directory");
        }
    }

    private org.json.JSONObject moveFile(java.io.File p3, java.io.File p4)
    {
        if ((!p4.exists()) || (!p4.isDirectory())) {
            // Both branches of the condition point to the same code.
            // if (p3.renameTo(p4)) {
                return this.getEntry(p4);
            // }
        } else {
            throw new com.phonegap.file.InvalidModificationException("Can\'t rename a file to a directory");
        }
    }

    private org.json.JSONArray readEntries(String p6)
    {
        java.io.File v2_1 = new java.io.File(p6);
        if (v2_1.exists()) {
            org.json.JSONArray v0_1 = new org.json.JSONArray();
            if (v2_1.isDirectory()) {
                java.io.File[] v1 = v2_1.listFiles();
                int v3 = 0;
                while (v3 < v1.length) {
                    v0_1.put(this.getEntry(v1[v3]));
                    v3++;
                }
            }
            return v0_1;
        } else {
            throw new java.io.FileNotFoundException();
        }
    }

    private boolean remove(String p4)
    {
        java.io.File v0_1 = new java.io.File(p4);
        if (!this.atRootDirectory(p4)) {
            if ((!v0_1.isDirectory()) || (v0_1.list().length <= 0)) {
                return v0_1.delete();
            } else {
                throw new com.phonegap.file.InvalidModificationException("You can\'t delete a directory that is not empty.");
            }
        } else {
            throw new com.phonegap.file.NoModificationAllowedException("You can\'t delete the root directory");
        }
    }

    private boolean removeDirRecursively(java.io.File p8)
    {
        if (p8.isDirectory()) {
            java.io.File[] v0 = p8.listFiles();
            int v3 = v0.length;
            int v2 = 0;
            while (v2 < v3) {
                this.removeDirRecursively(v0[v2]);
                v2++;
            }
        }
        if (p8.delete()) {
            return 1;
        } else {
            throw new com.phonegap.file.FileExistsException(new StringBuilder().append("could not delete: ").append(p8.getName()).toString());
        }
    }

    private boolean removeRecursively(String p3)
    {
        boolean v1_1;
        java.io.File v0_1 = new java.io.File(p3);
        if (!this.atRootDirectory(p3)) {
            v1_1 = this.removeDirRecursively(v0_1);
        } else {
            v1_1 = 0;
        }
        return v1_1;
    }

    private org.json.JSONObject requestFileSystem(int p6)
    {
        org.json.JSONObject v1_1 = new org.json.JSONObject();
        if (p6 != com.phonegap.FileUtils.TEMPORARY) {
            if (p6 != com.phonegap.FileUtils.PERSISTENT) {
                if (p6 != com.phonegap.FileUtils.RESOURCE) {
                    if (p6 != com.phonegap.FileUtils.APPLICATION) {
                        throw new java.io.IOException("No filesystem of type requested");
                    } else {
                        v1_1.put("name", "application");
                    }
                } else {
                    v1_1.put("name", "resource");
                }
            } else {
                if (!android.os.Environment.getExternalStorageState().equals("mounted")) {
                    throw new java.io.IOException("SD Card not mounted");
                } else {
                    v1_1.put("name", "persistent");
                    v1_1.put("root", this.getEntry(android.os.Environment.getExternalStorageDirectory()));
                }
            }
        } else {
            if (!android.os.Environment.getExternalStorageState().equals("mounted")) {
                throw new java.io.IOException("SD Card not mounted");
            } else {
                v1_1.put("name", "temporary");
                v1_1.put("root", this.getEntry(new StringBuilder().append(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()).append("/Android/data/").append(this.ctx.getPackageName()).append("/cache/").toString()));
                new java.io.File(new StringBuilder().append(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()).append("/Android/data/").append(this.ctx.getPackageName()).append("/cache/").toString()).mkdirs();
            }
        }
        return v1_1;
    }

    private org.json.JSONObject resolveLocalFileSystemURI(String p6)
    {
        java.io.File v1_1;
        String v0 = java.net.URLDecoder.decode(p6, "UTF-8");
        new java.net.URL(v0);
        if (!v0.startsWith("file://")) {
            v1_1 = new java.io.File(v0);
        } else {
            v1_1 = new java.io.File(v0.substring(7, v0.length()));
        }
        if (v1_1.exists()) {
            if (v1_1.canRead()) {
                return this.getEntry(v1_1);
            } else {
                throw new java.io.IOException();
            }
        } else {
            throw new java.io.FileNotFoundException();
        }
    }

    private org.json.JSONObject transferTo(String p6, org.json.JSONObject p7, String p8, boolean p9)
    {
        if ((p8 == null) || (!p8.contains(":"))) {
            java.io.File v2_1 = new java.io.File(p6);
            if (v2_1.exists()) {
                java.io.File v1_1 = new java.io.File(p7.getString("fullPath"));
                if (v1_1.exists()) {
                    java.io.File v0 = this.createDestination(p8, v2_1, v1_1);
                    if (!v2_1.getAbsolutePath().equals(v0.getAbsolutePath())) {
                        org.json.JSONObject v3_8;
                        if (!v2_1.isDirectory()) {
                            if (!p9) {
                                v3_8 = this.copyFile(v2_1, v0);
                            } else {
                                v3_8 = this.moveFile(v2_1, v0);
                            }
                        } else {
                            if (!p9) {
                                v3_8 = this.copyDirectory(v2_1, v0);
                            } else {
                                v3_8 = this.moveDirectory(v2_1, v0);
                            }
                        }
                        return v3_8;
                    } else {
                        throw new com.phonegap.file.InvalidModificationException("Can\'t copy a file onto itself");
                    }
                } else {
                    throw new java.io.FileNotFoundException("The source does not exist");
                }
            } else {
                throw new java.io.FileNotFoundException("The source does not exist");
            }
        } else {
            throw new com.phonegap.file.EncodingException("Bad file name");
        }
    }

    private long truncateFile(String p5, long p6)
    {
        long v2_1;
        java.io.RandomAccessFile v1_1 = new java.io.RandomAccessFile(p5, "rw");
        if (v1_1.length() < p6) {
            v2_1 = v1_1.length();
        } else {
            v1_1.getChannel().truncate(p6);
            v2_1 = p6;
        }
        return v2_1;
    }

    public com.phonegap.api.PluginResult execute(String p26, org.json.JSONArray p27, String p28)
    {
        com.phonegap.api.PluginResult$Status v19 = com.phonegap.api.PluginResult$Status.OK;
        try {
            com.phonegap.api.PluginResult v21_12;
            if (!p26.equals("testSaveLocationExists")) {
                if (!p26.equals("getFreeDiskSpace")) {
                    if (!p26.equals("testFileExists")) {
                        if (!p26.equals("testDirectoryExists")) {
                            if (!p26.equals("readAsText")) {
                                if (!p26.equals("readAsDataURL")) {
                                    if (!p26.equals("writeAsText")) {
                                        if (!p26.equals("write")) {
                                            if (!p26.equals("truncate")) {
                                                if (!p26.equals("requestFileSystem")) {
                                                    if (!p26.equals("resolveLocalFileSystemURI")) {
                                                        if (!p26.equals("getMetadata")) {
                                                            if (!p26.equals("getFileMetadata")) {
                                                                if (!p26.equals("getParent")) {
                                                                    if (!p26.equals("getDirectory")) {
                                                                        if (!p26.equals("getFile")) {
                                                                            if (!p26.equals("remove")) {
                                                                                if (!p26.equals("removeRecursively")) {
                                                                                    if (!p26.equals("moveTo")) {
                                                                                        if (!p26.equals("copyTo")) {
                                                                                            if (p26.equals("readEntries")) {
                                                                                                v21_12 = new com.phonegap.api.PluginResult;
                                                                                                v21_12(v19, this.readEntries(p27.getString(0)), "window.localFileSystem._castEntries");
                                                                                            }
                                                                                        } else {
                                                                                            v21_12 = new com.phonegap.api.PluginResult;
                                                                                            v21_12(v19, this.transferTo(p27.getString(0), p27.getJSONObject(1), p27.optString(2), 0), "window.localFileSystem._castEntry");
                                                                                        }
                                                                                    } else {
                                                                                        v21_12 = new com.phonegap.api.PluginResult;
                                                                                        v21_12(v19, this.transferTo(p27.getString(0), p27.getJSONObject(1), p27.optString(2), 1), "window.localFileSystem._castEntry");
                                                                                    }
                                                                                } else {
                                                                                    if (!this.removeRecursively(p27.getString(0))) {
                                                                                        v21_12 = new com.phonegap.api.PluginResult;
                                                                                        v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.NO_MODIFICATION_ALLOWED_ERR));
                                                                                    } else {
                                                                                        v21_12 = new com.phonegap.api.PluginResult;
                                                                                        v21_12(v19);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (!this.remove(p27.getString(0))) {
                                                                                    v21_12 = new com.phonegap.api.PluginResult;
                                                                                    v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.NO_MODIFICATION_ALLOWED_ERR));
                                                                                } else {
                                                                                    v21_12 = new com.phonegap.api.PluginResult;
                                                                                    v21_12(v19);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            v21_12 = new com.phonegap.api.PluginResult;
                                                                            v21_12(v19, this.getFile(p27.getString(0), p27.getString(1), p27.optJSONObject(2), 0), "window.localFileSystem._castEntry");
                                                                        }
                                                                    } else {
                                                                        v21_12 = new com.phonegap.api.PluginResult;
                                                                        v21_12(v19, this.getFile(p27.getString(0), p27.getString(1), p27.optJSONObject(2), 1), "window.localFileSystem._castEntry");
                                                                    }
                                                                } else {
                                                                    v21_12 = new com.phonegap.api.PluginResult;
                                                                    v21_12(v19, this.getParent(p27.getString(0)), "window.localFileSystem._castEntry");
                                                                }
                                                            } else {
                                                                v21_12 = new com.phonegap.api.PluginResult;
                                                                v21_12(v19, this.getFileMetadata(p27.getString(0)), "window.localFileSystem._castDate");
                                                            }
                                                        } else {
                                                            v21_12 = new com.phonegap.api.PluginResult;
                                                            v21_12(v19, this.getMetadata(p27.getString(0)), "window.localFileSystem._castDate");
                                                        }
                                                    } else {
                                                        v21_12 = new com.phonegap.api.PluginResult;
                                                        v21_12(v19, this.resolveLocalFileSystemURI(p27.getString(0)), "window.localFileSystem._castEntry");
                                                    }
                                                } else {
                                                    long v17 = p27.optLong(1);
                                                    if ((v17 == 0) || (v17 <= com.phonegap.DirectoryManager.getFreeDiskSpace())) {
                                                        v21_12 = new com.phonegap.api.PluginResult;
                                                        v21_12(v19, this.requestFileSystem(p27.getInt(0)), "window.localFileSystem._castFS");
                                                    } else {
                                                        v21_12 = new com.phonegap.api.PluginResult;
                                                        v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.QUOTA_EXCEEDED_ERR));
                                                    }
                                                }
                                            } else {
                                                v21_12 = new com.phonegap.api.PluginResult;
                                                v21_12(v19, ((float) this.truncateFile(p27.getString(0), p27.getLong(1))));
                                            }
                                        } else {
                                            v21_12 = new com.phonegap.api.PluginResult;
                                            v21_12(v19, ((float) this.write(p27.getString(0), p27.getString(1), p27.getInt(2))));
                                        }
                                    } else {
                                        this.writeAsText(p27.getString(0), p27.getString(1), p27.getBoolean(2));
                                    }
                                    v21_12 = new com.phonegap.api.PluginResult;
                                    v21_12(v19, "");
                                } else {
                                    v21_12 = new com.phonegap.api.PluginResult;
                                    v21_12(v19, this.readAsDataURL(p27.getString(0)));
                                }
                            } else {
                                v21_12 = new com.phonegap.api.PluginResult;
                                v21_12(v19, this.readAsText(p27.getString(0), p27.getString(1)));
                            }
                        } else {
                            v21_12 = new com.phonegap.api.PluginResult;
                            v21_12(v19, com.phonegap.DirectoryManager.testFileExists(p27.getString(0)));
                        }
                    } else {
                        v21_12 = new com.phonegap.api.PluginResult;
                        v21_12(v19, com.phonegap.DirectoryManager.testFileExists(p27.getString(0)));
                    }
                } else {
                    v21_12 = new com.phonegap.api.PluginResult;
                    v21_12(v19, ((float) com.phonegap.DirectoryManager.getFreeDiskSpace()));
                }
            } else {
                v21_12 = new com.phonegap.api.PluginResult;
                v21_12(v19, com.phonegap.DirectoryManager.testSaveLocationExists());
            }
        } catch (com.phonegap.api.PluginResult v21_105) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.NOT_FOUND_ERR));
        } catch (com.phonegap.api.PluginResult v21_101) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.PATH_EXISTS_ERR));
        } catch (com.phonegap.api.PluginResult v21_97) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.NO_MODIFICATION_ALLOWED_ERR));
        } catch (com.phonegap.api.PluginResult v21_94) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.NO_MODIFICATION_ALLOWED_ERR));
        } catch (com.phonegap.api.PluginResult v21_91) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.INVALID_MODIFICATION_ERR));
        } catch (com.phonegap.api.PluginResult v21_87) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.ENCODING_ERR));
        } catch (com.phonegap.api.PluginResult v21_84) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.INVALID_MODIFICATION_ERR));
        } catch (com.phonegap.api.PluginResult v21_79) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.ENCODING_ERR));
        } catch (com.phonegap.api.PluginResult v21_76) {
            v21_12 = new com.phonegap.api.PluginResult;
            v21_12(com.phonegap.api.PluginResult$Status.ERROR, new org.json.JSONObject().put("code", com.phonegap.FileUtils.TYPE_MISMATCH_ERR));
        } catch (com.phonegap.api.PluginResult v21_108) {
            v21_108.printStackTrace();
            v21_12 = new com.phonegap.api.PluginResult(com.phonegap.api.PluginResult$Status.JSON_EXCEPTION);
        }
        return v21_12;
    }

    public boolean isSynch(String p3)
    {
        int v0_4;
        if (!p3.equals("testSaveLocationExists")) {
            if (!p3.equals("getFreeDiskSpace")) {
                if (!p3.equals("testFileExists")) {
                    if (!p3.equals("testDirectoryExists")) {
                        v0_4 = 0;
                    } else {
                        v0_4 = 1;
                    }
                } else {
                    v0_4 = 1;
                }
            } else {
                v0_4 = 1;
            }
        } else {
            v0_4 = 1;
        }
        return v0_4;
    }

    public String readAsDataURL(String p13)
    {
        byte[] v3 = new byte[1000];
        java.io.BufferedInputStream v1_1 = new java.io.BufferedInputStream(this.getPathFromUri(p13), 1024);
        java.io.ByteArrayOutputStream v2_1 = new java.io.ByteArrayOutputStream();
        while(true) {
            int v7 = v1_1.read(v3, 0, 1000);
            if (v7 < 0) {
                break;
            }
            v2_1.write(v3, 0, v7);
        }
        String v4;
        if (!p13.startsWith("content:")) {
            v4 = com.phonegap.FileUtils.getMimeType(p13);
        } else {
            v4 = this.ctx.getContentResolver().getType(android.net.Uri.parse(p13));
        }
        return new StringBuilder().append("data:").append(v4).append(";base64,").append(new String(org.apache.commons.codec.binary.Base64.encodeBase64(v2_1.toByteArray()))).toString();
    }

    public String readAsText(String p9, String p10)
    {
        byte[] v2 = new byte[1000];
        java.io.BufferedInputStream v0_1 = new java.io.BufferedInputStream(this.getPathFromUri(p9), 1024);
        java.io.ByteArrayOutputStream v1_1 = new java.io.ByteArrayOutputStream();
        while(true) {
            int v3 = v0_1.read(v2, 0, 1000);
            if (v3 < 0) {
                break;
            }
            v1_1.write(v2, 0, v3);
        }
        return new String(v1_1.toByteArray(), p10);
    }

    public long write(String p9, String p10, int p11)
    {
        int v0 = 0;
        if (p11 > 0) {
            this.truncateFile(p9, ((long) p11));
            v0 = 1;
        }
        byte[] v4 = p10.getBytes();
        java.io.ByteArrayInputStream v2_1 = new java.io.ByteArrayInputStream(v4);
        java.io.FileOutputStream v3_1 = new java.io.FileOutputStream(p9, v0);
        byte[] v1 = new byte[v4.length];
        v2_1.read(v1, 0, v1.length);
        v3_1.write(v1, 0, v4.length);
        v3_1.flush();
        v3_1.close();
        return ((long) p10.length());
    }

    public void writeAsText(String p8, String p9, boolean p10)
    {
        byte[] v4 = p9.getBytes();
        java.io.ByteArrayInputStream v2_1 = new java.io.ByteArrayInputStream(v4);
        java.io.FileOutputStream v3_1 = new java.io.FileOutputStream(p8, p10);
        byte[] v1 = new byte[v4.length];
        v2_1.read(v1, 0, v1.length);
        v3_1.write(v1, 0, v4.length);
        v3_1.flush();
        v3_1.close();
        return;
    }
}
