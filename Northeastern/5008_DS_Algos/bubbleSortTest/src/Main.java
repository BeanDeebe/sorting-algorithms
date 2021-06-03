/*
CS5008
Dean Beebe
Bubble Sort Driver

This file is the driver for the bubbleSort file. This was a test for myself to see if I could create a bubble sort
algorithm from scratch.
 */
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] tester = {85, 46, 11, 29, 31, 18, 2, 99, 64, 56};
        bubbleSort bubble = new bubbleSort(tester);
        System.out.println("Unsorted Array: " + Arrays.toString(bubble.sortingArray));
        bubble.sort();
        System.out.println("Sorted Array: " + Arrays.toString(bubble.sortingArray));
    }
}
