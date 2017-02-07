import org.junit.Test;

import java.util.Arrays;

/**
 * Created by tlyon on 1/20/17.
 */
public class AESTest {

    @org.junit.Test
    public void generateKey() throws Exception {
        String keyTest = "2b7e151628aed2a6abf7158809cf4f3c";
        String keyTest192 = "8e73b0f7da0e6452c810f32b809079e562f8ead2522c6b7b";
        String keyTest256 = "603deb1015ca71be2b73aef0857d77811f352c073b6108d72d9810a30914dff4";

        AES aes = new AES(keyTest);
        String[] expected128 = {"2b7e1516","28aed2a6","abf71588","09cf4f3c"};
        String[] result128 = {aes.keyWords[0],aes.keyWords[1],aes.keyWords[2],aes.keyWords[3]};
        assert Arrays.deepEquals(expected128,result128);

        aes = new AES(keyTest192);
        String[] expected192 = {"8e73b0f7", "da0e6452", "c810f32b","809079e5","62f8ead2","522c6b7b"};
        String[] result192 = new String[6];
        System.arraycopy(aes.keyWords, 0, result192, 0, 6);
        assert Arrays.deepEquals(expected192,result192);

        aes = new AES(keyTest256);
        String[] expected256 = {"603deb10", "15ca71be", "2b73aef0","857d7781","1f352c07","3b6108d7", "2d9810a3","0914dff4"};
        String[] result256 =  new String[8];
        System.arraycopy(aes.keyWords, 0, result256, 0, 8);
        assert Arrays.deepEquals(expected256,result256);
    }

    @org.junit.Test
    public void encrypt() throws Exception {
        //Encryption
        System.out.println("\n\n-----------ENCRYPTION-------------------\n");

        //c1
        System.out.println("----------------------");
        System.out.println("C1 Encryption:");
        String c1Key ="000102030405060708090a0b0c0d0e0f";
        String c1PlainText ="00112233445566778899aabbccddeeff";
        String c1Expected = "69c4e0d86a7b0430d8cdb78070b4c55a";
        AES aes1 = new AES(c1Key);
        String c1Result = aes1.encrypt(c1PlainText);
        assert c1Result.equals(c1Expected);
        System.out.println("Plaintext:\t" + c1PlainText);
        System.out.println("Key:\t\t" + c1Key);
        System.out.println("Encrypted:\t" + c1Result);

        //c2
        System.out.println("----------------------");
        System.out.println("C2 Encryption:");
        String c2PlainText = "00112233445566778899aabbccddeeff";
        String c2Key = "000102030405060708090a0b0c0d0e0f1011121314151617";
        String c2Expected = "dda97ca4864cdfe06eaf70a0ec0d7191";
        aes1 = new AES(c2Key);
        String c2Result = aes1.encrypt(c2PlainText);
        assert c2Result.equals(c2Expected);
        System.out.println("Plaintext:\t" + c2PlainText);
        System.out.println("Key:\t\t" + c2Key);
        System.out.println("Encrypted:\t" + c2Result);

        //c3
        System.out.println("----------------------");
        System.out.println("C3 Encryption:");
        String c3PlainText = "00112233445566778899aabbccddeeff";
        String c3Key = "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f";
        String c3Expected = "8ea2b7ca516745bfeafc49904b496089";
        aes1 = new AES(c3Key);
        String c3Result = aes1.encrypt(c3PlainText);
        assert c3Result.equals(c3Expected);
        System.out.println("Plaintext:\t" + c3PlainText);
        System.out.println("Key:\t\t" + c3Key);
        System.out.println("Encrypted:\t" + c3Result);

        //Decryption

    }

    @Test
    public void decrypt() throws Exception {
        System.out.println("\n\n-----------DECRYPTION-------------------\n");

        //c1
        String c1PlainText = "69c4e0d86a7b0430d8cdb78070b4c55a";
        String c1Key ="000102030405060708090a0b0c0d0e0f";
        String c1Expected = "00112233445566778899aabbccddeeff";
        AES aes = new AES(c1Key);
        String c1Result = aes.decrypt(c1PlainText);
        assert c1Result.equals(c1Expected);
        System.out.println("Plaintext:\t" + c1PlainText);
        System.out.println("Key:\t\t" + c1Key);
        System.out.println("Decrypted:\t" + c1Result);

        //c2
        System.out.println("----------------------");
        System.out.println("C2 Decryption:");
        String c2PlainText = "dda97ca4864cdfe06eaf70a0ec0d7191";
        String c2Key = "000102030405060708090a0b0c0d0e0f1011121314151617";
        String c2Expected = "00112233445566778899aabbccddeeff";
        aes = new AES(c2Key);
        String c2Result = aes.decrypt(c2PlainText);
        assert c2Result.equals(c2Expected);
        System.out.println("Plaintext:\t" + c2PlainText);
        System.out.println("Key:\t\t" + c2Key);
        System.out.println("Decrypted:\t" + c2Result);

        System.out.println("----------------------");
        System.out.println("C3 Decryption:");
        String c3PlainText = "8ea2b7ca516745bfeafc49904b496089";
        String c3Key = "000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f";
        String c3Expected = "00112233445566778899aabbccddeeff";
        aes = new AES(c3Key);
        String c3Result = aes.decrypt(c3PlainText);
        assert c3Result.equals(c3Expected);
        System.out.println("Plaintext:\t" + c3PlainText);
        System.out.println("Key:\t\t" + c3Key);
        System.out.println("Dectrypted:\t" + c3Result);

    }

    @org.junit.Test
    public void byteToString() throws Exception {
        String byteS = "57";
        int b =  aes.stringToByte(byteS);
        String result = aes.byteToString(b);
        assert result.equals(byteS);

    }

    @org.junit.Test
    public void stringToByte() throws Exception {
        String byteS = "57";
        int result = aes.stringToByte(byteS);
        int expected = 87;
        assert result == expected;
    }

    AES aes = new AES("2b7e151628aed2a6abf7158809cf4f3c");
    @org.junit.Test
    public void rotWord() throws Exception {
        String word = "12345678";
        String result = aes.rotWord(word);
        assert result.equals("34567812");
    }

    @org.junit.Test
    public void subBytes() throws Exception {
        String[][] state = {
                {"19","a0","9a","e9"},
                {"3d","f4","c6","f8"},
                {"e3","e2","8d","48"},
                {"be","2b","2a","08"}
        };

        String[][] result = aes.subBytes(state);
        String[][] expected = {
                {"d4","e0","b8","1e"},
                {"27","bf","b4","41"},
                {"11","98","5d","52"},
                {"ae","f1","e5","30"}
        };

        assert Arrays.deepEquals(result,expected);
    }

    @org.junit.Test
    public void shiftRows() throws Exception {
        String[][] state = {
                {"d4","e0","b8","le"},
                {"27","bf","b4","41"},
                {"11","98","5d","52"},
                {"ae","f1","e5","30"}
        };
        String[][] result = aes.shiftRows(state);
        String[][] expected = {
                {"d4","e0","b8","le"},
                {"bf","b4","41","27"},
                {"5d","52", "11","98"},
                {"30","ae","f1","e5",}
        };

        assert Arrays.deepEquals(result,expected);
    }

    @org.junit.Test
    public void swap() throws Exception {
        String[] word = {"12","34","56","78"};
        String[] result = aes.swap(word, 1, 2);
        String[] expected = {"12","56","34","78"};
        assert Arrays.deepEquals(result,expected);
    }

    @org.junit.Test
    public void mixCols() throws Exception {
        String[][] state = {
                {"d4","e0","b8","1e"},
                {"bf","b4","41","27"},
                {"5d","52", "11","98"},
                {"30","ae","f1","e5",}
        };
        String[][]result = aes.mixCols(state);
        String[][]expected = {
                {"04","e0","48","28"},
                {"66","cb","f8","06"},
                {"81","19","d3","26"},
                {"e5","9a","7a","4c"}
        };

        assert Arrays.deepEquals(result,expected);
    }

    @org.junit.Test
    public void setCol() throws Exception {
        String[][] state = {
                {"d4","e0","b8","le"},
                {"bf","b4","41","27"},
                {"5d","52", "11","98"},
                {"30","ae","f1","e5",}
        };
        String[] col = {"00","00","00","00"};
        String[][] result = aes.setCol(state,0,col);
        String[][] expected = {
                {"00","e0","b8","le"},
                {"00","b4","41","27"},
                {"00","52", "11","98"},
                {"00","ae","f1","e5",}
        };
        assert Arrays.deepEquals(result,expected);
    }

    @org.junit.Test
    public void getCol() throws Exception {
        String[][] state = {
                {"d4","e0","b8","le"},
                {"bf","b4","41","27"},
                {"5d","52", "11","98"},
                {"30","ae","f1","e5",}
        };
        String[] result = aes.getCol(state,0);
        String[] expected = {"d4","bf","5d","30"};
        assert Arrays.deepEquals(result,expected);
    }

    @org.junit.Test
    public void finiteFieldMultiply() throws Exception {
        String a = "57";
        String b = "83";
        String result = aes.ffMultiply(a,b);
        assert result.equals("c1");
    }

    @org.junit.Test
    public void xtime() throws Exception {
        String byteS = "57";
        int b = aes.stringToByte(byteS);
        int result = aes.xtime(b);
        int result2 = aes.xtime(result);
        int result3 = aes.xtime(result2);
        int result4 = aes.xtime(result3);

        String resultS = aes.byteToString(result);
        String resultS2 = aes.byteToString(result2);
        String resultS3 = aes.byteToString(result3);
        String resultS4 = aes.byteToString(result4);

        assert resultS.equals("ae");
        assert resultS2.equals("47");
        assert resultS3.equals("8e");
        assert resultS4.equals("07");

    }

    @org.junit.Test
    public void xorWord() throws Exception {

    }

    @org.junit.Test
    public void xorByte() throws Exception {

    }

}