import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Launcher {

    public int[] bubbleSort(int[] numbers) {

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] < numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        return numbers;
    }

    public int[] integerToArray(int number) {
        String numberStr = String.valueOf(number);


        int[] digits = new int[numberStr.length()];

        for (int i = 0; i < numberStr.length(); i++) {
            digits[i] = Character.getNumericValue(numberStr.charAt(i));
        }

        return digits;
    }


    public void show(int[] numbers) {
        for (Integer n : numbers) {
            System.out.println(n);
        }
    }

    public int getNumber(int number) {
        String numString = "";
        int sorted[] = bubbleSort(integerToArray(number));
        for (Integer n : sorted) {
            numString += n + "";
        }
        return Integer.parseInt(numString);
    }

    /*

    public int oppositeNumber(int n) {
        String number = String.valueOf(n);
        char[] digits = number.toCharArray();
        Arrays.sort(digits);
        StringBuilder newNumber = new StringBuilder(new String(digits));
        newNumber.reverse();

        return Integer.parseInt(newNumber.toString());
    }

     */

    public static void main(String[] args) throws IOException {
        Launcher l = new Launcher();
        //System.out.println(l.oppositeNumber(239));
        int[] numbers = {2, 3, 9, 8};
        System.out.println((l.getNumber(1234)));
    }
}