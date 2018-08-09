import java.security.*;
import java.security.spec.ECGenParameterSpec;

/*
Simple class to create a wallet
 */
public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    /**
     * Creates a wallet
     */
    public Wallet() {
        try {
            createKeys();
        } catch (NoSuchProviderException |
                NoSuchAlgorithmException |
                InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a keypair using elliptic key cryptography
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public void createKeys()
            throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
        keyPairGenerator.initialize(ecSpec, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }
}
