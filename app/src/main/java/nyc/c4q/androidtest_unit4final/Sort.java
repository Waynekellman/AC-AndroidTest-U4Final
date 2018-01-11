package nyc.c4q.androidtest_unit4final;

import java.util.List;

/**
 * Created by justiceo on 1/7/18.
 */

public class Sort {

    /**
     * Sorts a list using the selection sort algorithm.
     * See lecture on sorting: https://github.com/C4Q/AC-Android/tree/v2/DSA/sorting
     *
     * When `isAscending` is true, the list is sorted in ascending alphabetical order from a to z,
     * otherwise it is sorted in descending order from z to a.
     * @param list
     * @param isAscending
     */
    public static void selectionSort(List<String> list, boolean isAscending) {
        // TODO: Implement selection sort.
        // You may not use Collections.sort or its equivalent
        // You may not implement another sorting algorithm that is not "selection sort"
        // Tip: Try a version without ordering first.
        if(isAscending) {
            for (int i = list.size(); i > 0; i--) {
                int lastAscendingItem = 0;
                for (int j = 0; j < i; j++) {
                    if (list.get(j).compareTo(list.get(lastAscendingItem)) > 0) {
                        lastAscendingItem = j;
                    }
                }
                String temp = list.get(lastAscendingItem);
                list.set(lastAscendingItem, list.get(i - 1));
                list.set(i - 1, temp);
            }
        } else {
            for (int i = list.size(); i > 0; i--) {
                int lastAscendingItem = 0;
                for (int j = 0; j < i; j++) {
                    if (list.get(j).compareTo(list.get(lastAscendingItem)) < 0) {
                        lastAscendingItem = j;
                    }
                }
                String temp = list.get(lastAscendingItem);
                list.set(lastAscendingItem, list.get(i - 1));
                list.set(i - 1, temp);
            }
        }
        /**
         * public void selectionSort(int[] arr) {
         for (int n = arr.length; n > 0; n--) {      // O(n) - loop
         // step 1: find largest element
         int largest = 0;                        // O(1)
         for(int i = 0; i < n; i++) {            // O(n) inside O(n) => 0(n^2)
         if (arr[i] > arr[largest]) {        // O(1)
         largest = i;                    // O(1)
         }
         }
         // step 2: swap the elements
         int tmp = arr[largest];                 // O(1)
         arr[largest] = arr[n-1];                // O(1)
         arr[n-1] = tmp;                         // O(1)
         }
         }

         * public static void yokiSort(List<Book> bookList){
         for (int i = bookList.size(); i > 0; i--) {
         int lastObject = 0;
         for (int j = 0; j < i; j++) {
         if (bookList.get(j).getName().compareTo(bookList.get(lastObject).getName()) > 0) {
         lastObject = j;
         }
         Book temp = bookList.get(lastObject);
         bookList.set(lastObject, bookList.get(i - 1));
         bookList.set(i - 1, temp);
         }
         }
         }
         */
    }
}
