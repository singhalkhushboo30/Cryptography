import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class AES {
    private SecretKey key;
    private final int keySize = 128;
    private final int dataLength = 128;
    private Cipher cipher;

    public void init() throws Exception {
    	//KeyGenerator- used to generate symmetric keys
    	//initializing the key generator instance using init() method
    	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keySize);
        key = keyGenerator.generateKey();
    }
    
    public String encrypt(String data) throws Exception {
    	//getting the byte array from message
        byte[] dataByte = data.getBytes();
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //returns the byte of encrypted data 
        byte[] encryptedBytes = cipher.doFinal(dataByte);
        //encode - to convert data into string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public String decrypt(String encryptedData) throws Exception {
    	//data is converted to byte array again
    	//decode takes byte array and returns to base 64
        byte[] dataByte = decode(encryptedData);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        //returning the string(message) from byte array
        byte[] decryptedBytes = cipher.doFinal(dataByte);
        return new String(decryptedBytes);
   }
    

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
    public static void main(String[] args) {
        try {
            AES aes = new AES();
            aes.init();
            String encryptedData = aes.encrypt("Hello");
            String decryptedData = aes.decrypt(encryptedData);

            System.out.println("Encrypted Data : " + encryptedData);
            System.out.println("Decrypted Data : " + decryptedData);
        } catch (Exception ignored) {
        }
    }
}
