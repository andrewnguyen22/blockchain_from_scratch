import java.security.MessageDigest;

/*
A class that holds simple tooling functions needed
 */
public class Utility {
    /**
     * Turns a string into a SHA256 Hash
     *
     * @param string
     * @return
     */
    public static String SHA256(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input,
            byte[] hash = digest.digest(string.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
