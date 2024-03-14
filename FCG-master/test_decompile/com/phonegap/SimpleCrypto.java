package com.phonegap;
public class SimpleCrypto {
    private static final String HEX = "01234567890ABCDEF";

    public SimpleCrypto()
    {
        return;
    }

    private static void appendHex(StringBuffer p3, byte p4)
    {
        p3.append("01234567890ABCDEF".charAt(((p4 >> 4) & 15))).append("01234567890ABCDEF".charAt((p4 & 15)));
        return;
    }

    public static String decrypt(String p4, String p5)
    {
        return new String(com.phonegap.SimpleCrypto.decrypt(com.phonegap.SimpleCrypto.getRawKey(p4.getBytes()), com.phonegap.SimpleCrypto.toByte(p5)));
    }

    private static byte[] decrypt(byte[] p4, byte[] p5)
    {
        javax.crypto.spec.SecretKeySpec v2_1 = new javax.crypto.spec.SecretKeySpec(p4, "AES");
        javax.crypto.Cipher v0 = javax.crypto.Cipher.getInstance("AES");
        v0.init(2, v2_1);
        return v0.doFinal(p5);
    }

    public static String encrypt(String p3, String p4)
    {
        return com.phonegap.SimpleCrypto.toHex(com.phonegap.SimpleCrypto.encrypt(com.phonegap.SimpleCrypto.getRawKey(p3.getBytes()), p4.getBytes()));
    }

    private static byte[] encrypt(byte[] p4, byte[] p5)
    {
        javax.crypto.spec.SecretKeySpec v2_1 = new javax.crypto.spec.SecretKeySpec(p4, "AES");
        javax.crypto.Cipher v0 = javax.crypto.Cipher.getInstance("AES");
        v0.init(1, v2_1);
        return v0.doFinal(p5);
    }

    public static String fromHex(String p2)
    {
        return new String(com.phonegap.SimpleCrypto.toByte(p2));
    }

    public static byte[] getRawKey(byte[] p5)
    {
        javax.crypto.KeyGenerator v0 = javax.crypto.KeyGenerator.getInstance("AES");
        java.security.SecureRandom v3 = java.security.SecureRandom.getInstance("SHA1PRNG");
        v3.setSeed(p5);
        v0.init(128, v3);
        return v0.generateKey().getEncoded();
    }

    public static byte[] toByte(String p5)
    {
        int v1 = (p5.length() / 2);
        byte[] v2 = new byte[v1];
        int v0 = 0;
        while (v0 < v1) {
            v2[v0] = Integer.valueOf(p5.substring((v0 * 2), ((v0 * 2) + 2)), 16).byteValue();
            v0++;
        }
        return v2;
    }

    public static String toHex(String p1)
    {
        return com.phonegap.SimpleCrypto.toHex(p1.getBytes());
    }

    public static String toHex(byte[] p3)
    {
        String v2_1;
        if (p3 != null) {
            StringBuffer v1_1 = new StringBuffer((p3.length * 2));
            int v0 = 0;
            while (v0 < p3.length) {
                com.phonegap.SimpleCrypto.appendHex(v1_1, p3[v0]);
                v0++;
            }
            v2_1 = v1_1.toString();
        } else {
            v2_1 = "";
        }
        return v2_1;
    }
}
