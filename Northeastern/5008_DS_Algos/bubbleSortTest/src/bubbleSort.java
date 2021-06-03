/*
CS5008
Dean Beebe

From Module 4 -- my own attempt at creating a bubbleSort algorithm.
 */

public class bubbleSort {
    int[] sortingArray;

    // initializing the bubble sort class.
    public bubbleSort(int[] sortingArray) {
        this.sortingArray = sortingArray;
    }

    /*
    Algorithm steps through an array and compares neighboring indices. If R index > L index, then it swaps the two
    indices.

    Once two indices are swapped, the algorithm checks the new L index against its L neighbor and swaps if needed.

    Sort is completed once the array is sorted least -> greatest.
     */
    public void sort() {
        for (int i = 0; i < sortingArray.length; i++) {
            if (i + 1 >= sortingArray.length || i < 0) {
                return;
            } else if (sortingArray[i] > sortingArray[i + 1]) {
                int temp = sortingArray[i + 1];
                sortingArray[i + 1] = sortingArray[i];
                sortingArray[i] = temp;
                int count = 1;
                if (i == 0) {
                    continue;
                } else {
                    while (sortingArray[i - count] > sortingArray[i]) {
                        int temp2 = sortingArray[i];
                        sortingArray[i] = sortingArray[i - count];
                        sortingArray[i - count] = temp2;
                        i--;
                        if (i - 1 < 0) {
                            break;
                        }
                    }
                }
            }

        }
    }

}
