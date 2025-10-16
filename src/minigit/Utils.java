package minigit;

import java.security.*;

public class Utils {
    // create byte sequence to hash in sha1
    // data is a string of all metadata and file content of the object
    // throw s UnsupportedEncodingException in getBytes if encoding type is not supported

    // prefix() not used yet
    public static byte[] prefix(String type, byte[] dataBytes) throws Exception{
        String header = type + " " + dataBytes.length;
        byte[] headerBytes = header.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        byte[] sequence = new byte[headerBytes.length + 1 + dataBytes.length];

        // Copy header
        System.arraycopy(headerBytes, 0, sequence, 0, headerBytes.length);
        // Add null byte separator
        sequence[headerBytes.length] = 0;
        // Copy actual file data
        System.arraycopy(dataBytes, 0, sequence, headerBytes.length + 1, dataBytes.length);

        return sequence;
    }

    // takes in byte sequence created in prefix to create unique object id
    // which acts as filepath for object in minigit/object/{hash}
    public static String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] result = md.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
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
