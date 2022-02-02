package types;

import java.util.Arrays;
import java.util.Random;

public class Code {

    public static void main(String[] args) {

        int[] numbers = {1, 3, -2, 9};

        System.out.println(sum(numbers)); // 11
        System.out.println(average(new int[]{1, 2})); // 1.5
        System.out.println(minimumElement(new int[]{1, 2})); // tagastab 1
        System.out.println(minimumElement(new int[]{})); // tagastab null-i
        System.out.println(asString(new int[]{1, 9, 3, -2, 9, 9})); // tagastab "1, 9, 3, -2, 9, 9"
        System.out.println(mode("abcb")); // b
        System.out.println(mode("abcacbaca")); // a
        System.out.println(mode("ab")); // a/b
        System.out.println(mode("")); // ""
        System.out.println(squareDigits("2"));
        System.out.println(squareDigits("a2b"));
        System.out.println(squareDigits("a22b"));
        System.out.println(squareDigits("a9b2"));
    }

    public static int sum(int[] numbers) {
        int sum = 0;
        for (int number :
                numbers) {
            sum += number;
        }
        return sum;
    }

    public static double average(int[] numbers) {
        return Double.valueOf(sum(numbers)) / numbers.length;
    }

    public static Integer minimumElement(int[] integers) {
        if (integers.length == 0) {
            return null;
        }
        int ret = integers[0];
        for (int num : integers) {
            if (ret > num) {
                ret = num;
            }
        }
        return ret;
    }

    public static String asString(int[] elements) {
        String ret = "";
        int count = 0;
        for (int elem :
                elements) {
            count++;
            ret += elem;
            if (elements.length != count) {
                ret += ", ";
            }
        }
        return ret;
    }

    public static Character mode(String input) {
        if (input.length() == 0) {
            return null;
        }
        Integer[] mod = {0, 0, 0};
        String[] letters = input.split("");
        for (String letter : letters) {
            if ("a".equals(letter)) {
                mod[0] += 1;
            } else if ("b".equals(letter)) {
                mod[1] += 1;
            } else if ("c".equals(letter)) {
                mod[2] += 1;
            }
        }
        if (mod[0] >= mod[1] && mod[0] >= mod[2]) {
            return 'a';
        } else if (mod[1] >= mod[0] && mod[1] >= mod[2]) {
            return 'b';
        } else {
            return 'c';
        }
    }

    public static String squareDigits(String s) {
        char[] sChar = s.toCharArray();
        String ret = "";
        for (char c : sChar) {
            if (Character.isDigit(c)) {
                int temp = Integer.parseInt(String.valueOf(c));
                ret += temp * temp;
            } else {
                ret += c;
            }
        }
        return ret;
    }

    public static int isolatedSquareCount() {
        boolean[][] matrix = getSampleMatrix();

        printMatrix(matrix);

        int isolatedCount = 0;

        // count isolates squares here
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i > 0 && i < 9 && j > 0 && j < 9) {
                    if (!matrix[i - 1][j - 1] && !matrix[i - 1][j] && !matrix[i - 1][j + 1]
                            && !matrix[i][j - 1] && !matrix[i][j + 1]
                            && !matrix[i + 1][j - 1] && !matrix[i + 1][j] && !matrix[i + 1][j + 1]) {
                        isolatedCount++;
                    }
                } else if (i == 0 && j > 0 && j < 9) {
                    if (!matrix[i][j - 1] && !matrix[i][j + 1] && !matrix[i + 1][j - 1] &&
                            !matrix[i + 1][j] && !matrix[i + 1][j + 1]) {
                        isolatedCount++;
                    }

                } else if (i == 9 && j > 0 && j < 9) {
                    if (!matrix[i - 1][j - 1] && !matrix[i - 1][j] && !matrix[i - 1][j + 1]
                            && !matrix[i][j - 1] && !matrix[i][j + 1]) {
                        isolatedCount++;
                    }
                } else if (i > 0 && i < 9 && j == 0) {
                    if (!matrix[i - 1][j] && !matrix[i - 1][j + 1] && !matrix[i][j + 1] &&
                            !matrix[i + 1][j] && !matrix[i + 1][j + 1]) {
                        isolatedCount++;
                    }
                } else if (i > 0 && i < 9) {
                    if (!matrix[i - 1][j - 1] && !matrix[i - 1][j] && !matrix[i][j - 1]
                            && !matrix[i + 1][j - 1] && !matrix[i + 1][j]) {
                        isolatedCount++;
                    }
                } else if (i == 0 && j == 0) {
                    if (!matrix[i][j + 1] && !matrix[i + 1][j] && !matrix[i + 1][j + 1]) {
                        isolatedCount++;
                    }
                } else if (i == 9 && j == 0) {
                    if (!matrix[i - 1][j] && !matrix[i - 1][j + 1] && !matrix[i][j + 1]) {
                        isolatedCount++;
                    }
                } else if (i == 0) {
                    if (!matrix[i][j - 1] && !matrix[i + 1][j - 1] && !matrix[i + 1][j]) {
                        isolatedCount++;
                    }
                } else {
                    if (!matrix[i - 1][j - 1] && !matrix[i - 1][j] && !matrix[i][j - 1]
                    ) {
                        isolatedCount++;
                    }
                }
            }
        }
        return isolatedCount;
    }

//            [false    false   false   false   true    true    false   true    false   true]
//            [true     false   false   true    false   true    false   true    false   false]
//            [false    true    false   true    true    false   true    false   true    true]
//            [false    false   false   false   false   true    true    false   false   false]
//            [false    true    true    false   true    false   false   false   false   true]
//            [true     true    false   false   false   false   false   true    false   true]
//            [false    true    false   true    false   true    false   false   false   false]
//            [false    true    false   true    true    false   false   true    false   false]
//            [true     false   false   false   true    true    false   true    true    false]
//            [false    true    true    true    true    true    false   false   false   true]

    private static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[10][10];

        Random r = new Random(5);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = r.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}
