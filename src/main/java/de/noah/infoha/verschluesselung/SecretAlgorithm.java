package de.noah.infoha.verschluesselung;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecretAlgorithm extends Verschluesselung {

    protected final String algorithm;
    protected final SecretKey key;
    public SecretAlgorithm(String algorithm, int keyN) throws NoSuchAlgorithmException {
        this.algorithm = algorithm;
        final KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keyN);
        key = keyGenerator.generateKey();
    }

    @Override
    public String codiere(String klartext) {
        try {
            return encrypt(algorithm, klartext, key);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }

    private String encrypt(String algorithm, String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        final byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    @Override
    public String decodiere(String geheimtext) {
        try {
            return decrypt(algorithm, geheimtext, key);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }

    private String decrypt(String algorithm, String cipherText, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        final byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

}
