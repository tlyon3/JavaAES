import javax.net.ssl.SSLContext;

/**
 * Created by tlyon on 1/19/17.
 */
public class main {
    public static void main(String[] args){
        String key1 ="000102030405060708090a0b0c0d0e0f";
        String input1 ="00112233445566778899aabbccddeeff";

        System.out.println();
        System.out.println("Using key: " + key1);
        AES aes = new AES(key1);
        System.out.println("Encrypting: " + input1);
        System.out.println("Encrypted: " + aes.encrypt(input1));
    }
}
