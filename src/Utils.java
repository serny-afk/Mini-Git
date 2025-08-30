import java.security.*;

public class Utils {
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
