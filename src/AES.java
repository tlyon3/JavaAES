/**
 * Created by tlyon on 1/19/17.
 *
 */
class AES {
    private int Nb = 4;
    private int Nk;
    private int Nr;
    String[] keyWords;

    private String[][] sBox = {
            {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
            {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
            {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
            {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
            {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
            {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
            {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
            {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
            {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
            {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
            {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
            {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
            {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
            {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
            {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
            {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}};

    private String[][] inverseSBox = {
            {"52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb"},
            {"7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4", "de", "e9", "cb"},
            {"54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3", "4e"},
            {"08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2", "49", "6d", "8b", "d1", "25"},
            {"72", "f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92"},
            {"6c", "70", "48", "50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84"},
            {"90", "d8", "ab", "00", "8c", "bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06"},
            {"d0", "2c", "1e", "8f", "ca", "3f", "0f", "02", "c1", "af", "bd", "03", "01", "13", "8a", "6b"},
            {"3a", "91", "11", "41", "4f", "67", "dc", "ea", "97", "f2", "cf", "ce", "f0", "b4", "e6", "73"},
            {"96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37", "e8", "1c", "75", "df", "6e"},
            {"47", "f1", "1a", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa", "18", "be", "1b"},
            {"fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a", "f4"},
            {"1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f"},
            {"60", "51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef"},
            {"a0", "e0", "3b", "4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61"},
            {"17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d"}};

    private String[] rcon = {
            "00000000",
            "01000000", "02000000", "04000000", "08000000",
            "10000000", "20000000", "40000000", "80000000",
            "1B000000", "36000000", "6C000000", "D8000000",
            "AB000000", "4D000000", "9A000000", "2F000000",
            "5E000000", "BC000000", "63000000", "C6000000",
            "97000000", "35000000", "6A000000", "D4000000",
            "B3000000", "7D000000", "FA000000", "EF000000",
            "C5000000", "91000000", "39000000", "72000000",
            "E4000000", "D3000000", "BD000000", "61000000",
            "C2000000", "9F000000", "25000000", "4A000000",
            "94000000", "33000000", "66000000", "CC000000",
            "83000000", "1D000000", "3A000000", "74000000",
            "E8000000", "CB000000", "8D000000"};

    AES(String key) {
        int keylength = key.length() / 8;
        this.Nk = keylength;
        switch (keylength) {
            case 4:
                this.Nr = 10;
                break;
            case 6:
                this.Nr = 12;
                break;
            case 8:
                this.Nr = 14;
                break;
        }
        generateKey(key);
    }

    private String[] generateKey(String key) {
        this.keyWords = stringToWords(key);
        String temp;
        for (int i = Nk; i < (Nb * (Nr + 1)); i++) {
            temp = keyWords[i - 1];
            if (i % Nk == 0) {
                temp = xorWord(subWord(rotWord(temp)), rcon[i / Nk]);
            }
            //if is multiple of 4
            else if (Nk > 6 && i % Nk == 4) {
                temp = subWord(temp);
            }
            keyWords[i] = xorWord(keyWords[i - Nk], temp);
        }
        return keyWords;
    }

    String rotWord(String s) {
//        System.out.println("Rot word before: " + s);
        //append last three bytes
        //append first byte to end
        //        System.out.println("Rot word after: " + sb.toString());
        return s.substring(2) + s.substring(0, 2);
    }

    String encrypt(String s) {
        String[][] state = stringToState(s);

        state = addRoundKey(state, getKeyForRound(0, 3));
        System.out.println("ARK: " + stateToString(state));
        for (int i = 1; i < Nr; i++) {
            System.out.println("-[" + i + "]-");
            state = subBytes(state);
            System.out.println("SB: " + stateToString(state));
            state = shiftRows(state);
            System.out.println("SR: " + stateToString(state));
            state = mixCols(state);
            System.out.println("MC: " + stateToString(state));
            state = addRoundKey(state, getKeyForRound(i * Nb, (i + 1) * Nb - 1));
            System.out.println("ARK: " + stateToString(state));
        }
        System.out.println("-[Final]-");
        state = subBytes(state);
        System.out.println("SB: " + stateToString(state));
        state = shiftRows(state);
        System.out.println("SR: " + stateToString(state));
        state = addRoundKey(state, getKeyForRound(Nr * Nb, (Nr + 1) * Nb - 1));
        System.out.println("ARK: " + stateToString(state));
        return stateToString(state);
    }

    String decrypt(String s) {
        String[][] state = stringToState(s);

        state = addRoundKey(state, getKeyForRound(Nr * Nb, (Nr + 1) * Nb - 1));
        System.out.println("ARK: " + stateToString(state));

        for (int i = Nr - 1; i > 0; i--) {
            System.out.println("-[" + i + "]-");
            state = inverseShiftRows(state);
            System.out.println("ISR: " + stateToString(state));
            state = inverseSubBytes(state);
            System.out.println("ISB: " + stateToString(state));
            state = addRoundKey(state, getKeyForRound(i * Nb, (i + 1) * Nb - 1));
            System.out.println("ARK: " + stateToString(state));
            state = inverseMixCols(state);
            System.out.println("IMC: " + stateToString(state));
        }
        System.out.println("-[Final]-");
        state = inverseShiftRows(state);
        System.out.println("ISR: " + stateToString(state));
        state = inverseSubBytes(state);
        System.out.println("ISB: " + stateToString(state));
        state = addRoundKey(state, getKeyForRound(0, Nb - 1));
        System.out.println("ARK: " + stateToString(state));

        return stateToString(state);
    }

    String[][] subBytes(String[][] state) {
        String[][] result = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String stateByte = state[i][j];
                int[] indexes = getIndexesFromByte(stateByte);
                result[i][j] = sBox[indexes[0]][indexes[1]];
            }
        }
        return result;
    }

    private String[][] inverseSubBytes(String[][] state) {
        String[][] result = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String stateByte = state[i][j];
                int[] indexes = getIndexesFromByte(stateByte);
                result[i][j] = inverseSBox[indexes[0]][indexes[1]];
            }
        }
        return result;
    }

     String[][] shiftRows(String[][] state) {
        String[][] result = new String[4][4];
        String[] row1 = state[0];
        String[] row2 = state[1];
        String[] row3 = state[2];
        String[] row4 = state[3];

        result[0] = row1;
        //swap row2
        row2 = swap(row2, 0, 1);
        row2 = swap(row2, 1, 2);
        row2 = swap(row2, 2, 3);

        result[1] = row2;

        //swap row3
        row3 = swap(row3, 1, 2);
        row3 = swap(row3, 2, 3);
        row3 = swap(row3, 0, 1);
        row3 = swap(row3, 1, 2);

        result[2] = row3;

        //swap row4
        row4 = swap(row4, 2, 3);
        row4 = swap(row4, 1, 2);
        row4 = swap(row4, 0, 1);

        result[3] = row4;

        return result;
    }

     String[] swap(String[] array, int i, int j) {
         String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

    private String[][] inverseShiftRows(String[][] state) {
        String[][] result = new String[4][4];
        String[] row1 = state[0];
        String[] row2 = state[1];
        String[] row3 = state[2];
        String[] row4 = state[3];
        result[0] = row1;
        row2 = swap(row2, 2, 3);
        row2 = swap(row2, 1, 2);
        row2 = swap(row2, 0, 1);

        result[1] = row2;

        row3 = swap(row3, 1, 2);
        row3 = swap(row3, 0, 1);
        row3 = swap(row3, 2, 3);
        row3 = swap(row3, 1, 2);

        result[2] = row3;

        row4 = swap(row4, 0, 1);
        row4 = swap(row4, 1, 2);
        row4 = swap(row4, 2, 3);

        result[3] = row4;

        return result;
    }

     String[][] mixCols(String[][] state) {
        String[][] result = new String[4][4];
        for (int i = 0; i < 4; i++) {
            String s0 = state[0][i];
            String s1 = state[1][i];
            String s2 = state[2][i];
            String s3 = state[3][i];

            result[0][i] = xorByte(xorByte(xorByte(ffMultiply(s0, "02"), ffMultiply(s1, "03")), s2), s3);
            result[1][i] = xorByte(xorByte(xorByte(ffMultiply(s1, "02"), ffMultiply(s2, "03")), s0), s3);
            result[2][i] = xorByte(xorByte(xorByte(ffMultiply(s2, "02"), ffMultiply(s3, "03")), s0), s1);
            result[3][i] = xorByte(xorByte(xorByte(ffMultiply(s3, "02"), ffMultiply(s0, "03")), s1), s2);
        }
        return result;
    }

    private String[][] inverseMixCols(String[][] state) {
        String[][] result = new String[4][4];
        for (int i = 0; i < 4; i++) {
            String s0 = state[0][i];
            String s1 = state[1][i];
            String s2 = state[2][i];
            String s3 = state[3][i];

            result[0][i] = xorByte(xorByte(xorByte(ffMultiply(s0,"0e"),ffMultiply(s1,"0b")),ffMultiply(s2,"0d")),ffMultiply(s3,"09"));
            result[1][i] = xorByte(xorByte(xorByte(ffMultiply(s0,"09"),ffMultiply(s1,"0e")),ffMultiply(s2,"0b")),ffMultiply(s3,"0d"));
            result[2][i] = xorByte(xorByte(xorByte(ffMultiply(s0,"0d"),ffMultiply(s1,"09")),ffMultiply(s2,"0e")),ffMultiply(s3,"0b"));
            result[3][i] = xorByte(xorByte(xorByte(ffMultiply(s0,"0b"),ffMultiply(s1,"0d")),ffMultiply(s2,"09")),ffMultiply(s3,"0e"));
        }
        return result;
    }

     String[][] setCol(String[][] state, int colNumber, String[] col) {
         state[0][colNumber] = col[0];
        state[1][colNumber] = col[1];
        state[2][colNumber] = col[2];
        state[3][colNumber] = col[3];
        return state;

    }

     String[] getCol(String[][] state, int colNumber) {
        String[] result = new String[4];
        result[0] = state[0][colNumber];
        result[1] = state[1][colNumber];
        result[2] = state[2][colNumber];
        result[3] = state[3][colNumber];
        return result;
    }

     String ffMultiply(String a, String b) {
        int x = stringToByte(a);
        int y = stringToByte(b);
        int sum = 0;
        int temp = x;
        while (y > 0) {
            if ((y & 0x01) == 1) {
                sum = sum ^ temp;
            }
            temp = xtime(temp);
            y = y >> 1;
        }
        sum = sum & 0xff;
        return padTrimHex(Integer.toHexString(sum));
    }

    private String padTrimHex(String s) {
        while (s.length() > 2) {
            s = s.substring(1);
        }
        if (s.length() < 2) {
            s = "0" + s;
        }
        return s;
    }

     int xtime(int x) {
        if ((x & 0x80) == 0x80) {
            x = x << 1;
            x = x ^ 0x1b;
        } else {
            x = x << 1;
        }
        //mask first two bytes and return
        return x & 0xff;
    }


    private String xorWord(String a, String b) {
        StringBuilder sb = new StringBuilder();
        while (a.length() > 0) {
            //get bytes
//            System.out.println("a: " + a);
//            System.out.println("b: " + b);
            String subA = a.substring(0, 2);
            String subB = b.substring(0, 2);

            sb.append(xorByte(subA, subB));
            //move to next byte
            a = a.substring(2);
            b = b.substring(2);
        }
        return sb.toString();
    }

    private String xorByte(String a, String b) {
        int aByte = stringToByte(a);
        int bByte = stringToByte(b);
        int result = aByte ^ bByte;
        return byteToString(result);
    }

    private String subWord(String w) {
        StringBuilder sb = new StringBuilder();
        String[] bytes = splitIntoBytes(w);
        for (int i = 0; i < 4; i++) {
            int[] indexes = getIndexesFromByte(bytes[i]);
            String sub = sBox[indexes[0]][indexes[1]];
            sb.append(sub);
            //get next byte
            w = w.substring(2);
        }
        return sb.toString();
    }

    private String[] splitIntoBytes(String w) {
        String[] result = new String[4];
        result[0] = w.substring(0, 2);
        result[1] = w.substring(2, 4);
        result[2] = w.substring(4, 6);
        result[3] = w.substring(6, 8);
        return result;
    }

    private String[] stringToWords(String s) {
        String[] result = new String[Nb * (Nr + 1)];
        for (int i = 0; i < Nk; i++) {
            result[i] = s.substring(0, 8);
            //get next word
            s = s.substring(8);
        }
        return result;
    }

     int stringToByte(String s) {
        try {
            String first = s.substring(0, 1);
            String second = s.substring(1, 2);
            int valueFirst = toIndex(first);
            int valueSecond = toIndex(second);
            return (valueFirst << 4) | valueSecond;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

     String byteToString(int b) {
        return padTrimHex(Integer.toHexString(b));
    }

    private int[] getIndexesFromByte(String b) {
        String first = b.substring(0, 1);
        String second = b.substring(1, 2);
        return new int[]{toIndex(first), toIndex(second)};
    }

    private int toIndex(String s) {
        try {
            switch (s) {
                case "a":
                    return 10;
                case "A":
                    return 10;
                case "b":
                    return 11;
                case "B":
                    return 11;
                case "c":
                    return 12;
                case "C":
                    return 12;
                case "d":
                    return 13;
                case "D":
                    return 13;
                case "e":
                    return 14;
                case "E":
                    return 14;
                case "f":
                    return 15;
                case "F":
                    return 15;
                default:
                    return Integer.parseInt(s);

            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    private String[][] stringToState(String s) {
        String[][] state = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //col X row
                state[j][i] = s.substring(0, 2);
                //move to next byte
                s = s.substring(2);
            }
        }
        return state;
    }

    private String stateToString(String[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(state[j][i]);
            }
        }
        return sb.toString();
    }

    private String[][] addRoundKey(String[][] state, String[][] key) {
        String[][] result = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = xorByte(state[i][j], key[i][j]);
            }
        }
        return result;
    }

    private String[][] getKeyForRound(int start, int end) {
        String[][] result = new String[4][4];
        int j = 0;
        for (int i = start; i <= end; i++) {
            result[0][j] = keyWords[i].substring(0, 2);
            result[1][j] = keyWords[i].substring(2, 4);
            result[2][j] = keyWords[i].substring(4, 6);
            result[3][j] = keyWords[i].substring(6, 8);
            j++;
        }
        return result;
    }
}
