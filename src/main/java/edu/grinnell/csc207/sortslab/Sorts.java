package edu.grinnell.csc207.sortslab;

/**
 * A collection of sorting algorithms over generic arrays.
 */
public class Sorts {
    /**
     * Swaps indices <code>i</code> and <code>j</code> of array <code>arr</code>.
     * @param <T> the carrier type of the array
     * @param arr the array to swap
     * @param i the first index to swap
     * @param j the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorts the array according to the bubble sort algorithm:
     * <pre>
     * [ unprocessed | i largest elements in order ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */

    /* note: i know the swapped bool is an optimzation not involved in classic 
     * bubblesort, i just couldn't help myself. */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length-1; j++) {
                // true when j is of higher order than j+1
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    swap(arr, j, j+1);
                    swapped = true;
                }
            }
            // stop walk when whole arr is ordered 
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Sorts the array according to the selection sort algorithm:
     * <pre>
     * [ i smallest elements in order | unprocessed ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min_idx = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[min_idx].compareTo(arr[j]) > 0) {
                    min_idx = j;
                }
            }
            swap(arr, i, min_idx);
        }
    }

    /**
     * Sorts the array according to the insertion sort algorithm:
     * <pre>
     * [ i elements in order | unprocessed ] 
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i].compareTo(arr[j]) < 1) {
                    T tmp = arr[i];
                    System.arraycopy(arr, j, arr, j+1, i-j);
                    arr[j] = tmp;
                }
            }
        }
    }

    /**
     * Sorts the array according to the merge sort algorithm:
     * <ol>
     * <li>Sort the left half of the array recursively.</li>
     * <li>Sort the right half of the array recursively.</li>
     * <li>Merge the two sorted halves into a sorted whole.</li>
     * </ol>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        if (arr == null || arr.length <= 1) return;
        T[] new_arr = java.util.Arrays.copyOf(arr, arr.length);

        mergeHelper(arr, new_arr, 0, arr.length-1);
    }

    public static <T extends Comparable<? super T>> void mergeHelper(T[] arr, T[] new_arr, int fst, int lst) {
        if (fst == lst) return;

        int mid = (fst + lst) / 2;

        mergeHelper(arr, new_arr, fst, mid);
        mergeHelper(arr, new_arr, mid+1, lst);
        sortHelper(arr, new_arr, fst, mid+1, fst);
    }


    public static <T extends Comparable<? super T>> void sortHelper(T[] arr, T[] new_arr, int cur1, int cur2, int new_cur) {
        int mid = cur2;
        if (new_cur == new_arr.length) return;

        while((cur1 <= mid) && (cur2 < new_arr.length)) {
            if (arr[cur1].compareTo(arr[cur2]) < 0) {
                new_arr[new_cur] = arr[cur1];
                cur1++;
            } else {
                new_arr[new_cur] = arr[cur2];
                cur2++;
            } 
            new_cur++;
        }

        // catch cursors up after 1 half is fully sorted
        while (cur1 <= mid) {
            new_arr[new_cur] = arr[cur1];
            new_cur++;
            cur1++;
        }
        while (cur2 < new_arr.length) {
            new_arr[new_cur] = arr[cur2];
            new_cur++;
            cur2++;
        }

        // write back to old array
        System.arraycopy(new_arr, 0, arr, 0, arr.length);
    }

    /**
     * Sorts the array according to the quick sort algorithm:
     * <ol>
     * <li>Choose a pivot value and partition the array according to the pivot.</li>
     * <li>Sort the left half of the array recursively.</li>
     * <li>Sort the right half of the array recursively.</li>
     * </ol>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        // TODO: fill me in!
    }
}
