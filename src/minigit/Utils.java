package minigit;

import java.security.*;

public class Utils {
    // create byte sequence to hash in sha1
    // data is a string of all metadata and file content of the object
    public static byte[] prefix(String type, String data) throws Exception{
        byte[] dataBytes = data.getBytes("UTF-8");

        int size = dataBytes.length;
        String header = type + " " + size;
        byte[] headerBytes = header.getBytes("UTF-8");

        byte[] sequence = new byte[headerBytes.length + 1 + dataBytes.length];

        sequence[headerBytes.length] = 0;
        System.arraycopy(headerBytes, 0, sequence, headerBytes.length+1, dataBytes.length);
        return sequence;
    }

    // takes in byte sequence created in prefix to create unique object id
    // which acts as filepath for object in minigit/object/{hash}
    public static String sha1(String input) {
        // SHA-1 hash (40 char hexadecimal string) gives
        // each object a unique identifier like in git
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] result = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
