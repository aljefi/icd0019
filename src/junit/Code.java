package junit;

import java.util.ArrayList;
import java.util.Collections;

public class Code {

    public static boolean isSpecial(int candidate) {
        return candidate % 11 <= 3;
    }

    public static int longestStreak(String inputString) {
        if (inputString.length() == 0) {
            return 0;
        }
        ArrayList<Integer> streaks = new ArrayList<>();
        int count = 0;
        char current = inputString.charAt(0);
        for (Character c : inputString.toCharArray()) {
            if (current == c) {
                count++;
            } else {
                current = c;
                streaks.add(count);
                count = 1;
            }
        }
        streaks.add(count);
        if (streaks.get(0) != 0) {
            return Collections.max(streaks);
        } else {
            return inputString.length();
        }
    }

    public static Character mode(String inputString) {
        Character ret = null;
        int count = 0;
        if (inputString != null) {
            for (Character c : inputString.toCharArray()) {
                int temp = getCharacterCount(inputString, c);
                if (count < temp) {
                    ret = c;
                }
                count = temp;
            }
        }
        return ret;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        int count = 0;
        for (Character c :
                allCharacters.toCharArray()) {
            if (c == targetCharacter) {
                count++;
            }
        }
        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        int[] temp = new int[integers.length];
        int count = 0;
        int j = 0;
        boolean hasZero = false;
        for (int integer : integers) {
            if (!isContains(temp, integer)) {
                temp[j] = integer;
                count++;
                j++;
            } else if (integer == 0 && !hasZero) {
                temp[j] = integer;
                count++;
                j++;
                hasZero = true;
            }
        }
        int[] ret = new int[count];
        for (int i = 0; i < count; i++) {
            ret[i] = temp[i];
        }
        return ret;
    }

    public static boolean isContains(int[] temp, int num) {
        for (int i : temp) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int[] temp = removeDuplicates(integers);
        int sum = 0;
        for (int num :
                temp) {
            sum += num;
        }
        return sum;
    }

}
