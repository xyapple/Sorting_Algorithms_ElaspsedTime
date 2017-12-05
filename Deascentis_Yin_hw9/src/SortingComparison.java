/*Reference:
*    Data Structures and Algorithms in Java, Sixth Edition
*    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
*    John Wiley & Sons, 2014
*
*    http://javabypatel.blogspot.in/2015/11/heap-sort-algorithm.html
*    http://tufangorel.blogspot.com/2015/10/generic-recursive-merge-sort-in-java.html
*
*/

/*
 * CS526 Homework 9
 *
 * Yin Deascentis
 *
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class SortingComparison {
  
    public static void main(String[] args){
    
    
        int n = 1000000; //number of values
        //This is the given ranges
        int[] range = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};

        // int Array for insertion sort
        int[] insertionArray ;
        int[] insertionArrayCopy;

        // Integer Array for merge sort
        Integer [] mergeArray;
        Integer[] mergeArrayCopy;

        // Integer Array for quick sort
        Integer[] quickArray;
        Integer[] quickArrayCopy;

        // int Array for heap sort
        int[] heapArray ;
        int[] heapArrayCopy ;

        //create random number
        Random r = new Random( );
        //calculate elapsed time
        long startTime, endTime, elapsedTime;
    
        // Insertion Sort
        System.out.println("========= Insertion Sort ==========");
    
        for(int num : range){
            insertionArray = new int[n];
            insertionArrayCopy = Arrays.copyOf(insertionArray, num);
            
            for(int i = 0; i< insertionArrayCopy.length; i ++){
                insertionArrayCopy[i] = r.nextInt()*num;
            }
        
            startTime = System.currentTimeMillis();
        
            insertionSort(insertionArrayCopy);
            endTime = System.currentTimeMillis();
        
            elapsedTime = endTime - startTime;
        
        
            System.out.println("Insertion Sort time "+ num +" = " + elapsedTime);
        
        }
        // Merge Sort
        System.out.println("========= Merge Sort ==========");
        for(int num : range){
            
            mergeArray = new Integer[n];
            mergeArrayCopy = Arrays.copyOf(mergeArray, num);
            
            for(int i = 0; i< mergeArrayCopy.length; i ++){
                mergeArrayCopy[i] = r.nextInt(num)+1;
            }

            startTime = System.currentTimeMillis();
          
            mergeSort(mergeArrayCopy);
           endTime = System.currentTimeMillis();
            
            elapsedTime = endTime - startTime;
            
           System.out.println("Merge Sort time "+ num +" = " + elapsedTime);
           
         
        } //end of merge sort
        
        
       //Quick Sort
        System.out.println("============= Quick Sort =============");
        for(int num : range){
            quickArray = new Integer[n];
            quickArrayCopy = Arrays.copyOf(quickArray, num);
            
            for(int i = 0; i < quickArrayCopy.length; i ++){
                quickArrayCopy[i] = r.nextInt(num)+1;
               
            }
            
            startTime = System.currentTimeMillis();
            
            quickSort(quickArrayCopy);
            endTime = System.currentTimeMillis();
          
            elapsedTime = endTime - startTime;
            
            System.out.println("Quick Sort time "+ num +" = " + elapsedTime);
        
        }
        //end of quick sort
        
        // Heap Sort
        System.out.println("========= Heap Sort ==========");
        for(int num : range){
            heapArray = new int[n];
            heapArrayCopy = Arrays.copyOf(heapArray, num);
            
            for(int i = 0; i< heapArrayCopy.length; i++){
                heapArrayCopy[i] = r.nextInt(num)+1;
            }
            
            startTime = System.currentTimeMillis();
        
            heapSort(heapArrayCopy);
           
            endTime = System.currentTimeMillis();
         
            elapsedTime = endTime - startTime;
           
            System.out.println("Heap Sort time "+ num +" = " + elapsedTime);
            
        }
        //end of heap sort
        
    
    }// end of main method
    
    
  

    /**
     * DefaultComparator from Data Structures and Algorithms in Java page 364
     */
    private static class DefaultComparator<T extends Comparable<T>> implements Comparator<T> {
        public int compare(T a, T b) {
            return a.compareTo(b);
        }
    }
    
    
    /**
     * Insertion-sort from Data Structures and Algorithms in Java page 111
     */
    public static void insertionSort(int[] data){
        int n = data.length;
        for(int k = 1; k < n; k++){
            int cur = data[k];
            int j = k;
            while(j >0 && data[j-1] > cur){
                data[j] = data[j-1];
                j-- ;
            } //end of while
            data[j] = cur;
            
        } //end of for
    } //end of insertion-sort
    
    /**
     * Array-based implementation of merge-sort Data Structures and Algorithms in Java page 537
     */
    /** Merge contents of arrays S1 and S2 into properly sized array S. */
    public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
                S[i+j] = S1[i++];                     // copy ith element of S1 and increment i
            else
                S[i+j] = S2[j++];                     // copy jth element of S2 and increment j
        }
    }
    /** Merge-sort contents of array S. */
    public static <K> void mergeSort(K[] S, Comparator<K> comp) {
        int n = S.length;
        if (n < 2) return;                        // array is trivially sorted
        // divide
        int mid = n/2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);   // copy of first half
        K[] S2 = Arrays.copyOfRange(S, mid, n);   // copy of second half
        // conquer (with recursion)
        mergeSort(S1, comp);                      // sort copy of first half
        mergeSort(S2, comp);                      // sort copy of second half
        // merge results
        merge(S1, S2, S, comp);               // merge sorted halves back into original
    }
    
    public static <T extends Comparable<T>> void mergeSort(T[] a) {
        
        mergeSort(a, new DefaultComparator<T>());
    }
    
    
    /**
     * Array-based implementation of quick-sort Data Structures and Algorithms in Java page 553
     */
    public static <K> void quickSort(K[] S, Comparator<K> comp, int a, int b) {
        if (a >= b) return;
        int left = a;
        int right = b - 1;
        K pivot = S[b];
        K temp;
        while (left <= right) {
    
            while (left <= right && comp.compare(S[left], pivot) < 0) {
                left++;
            }
            while (left <= right && comp.compare(S[right], pivot) > 0) {
                right--;
            }
            if (left <= right) {
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;
                left++;
                right--;
            }
    
        }
        temp = S[left];
        S[left] = S[b];
        S[b] = temp;
        quickSort(S, comp, a, left - 1);
        quickSort(S, comp, left + 1, b);
    }
    
    public static <T extends Comparable<T>> void quickSort(T[] a) {
        quickSort(a, new DefaultComparator<T>());
    }
    
    public static <T extends Comparable<T>> void quickSort(T[] a, Comparator<T> c) {
        quickSort(a, c, 0, a.length-1 );
    }
    /**
     * http://javabypatel.blogspot.in/2015/11/heap-sort-algorithm.html
     *
     * This is HeapSort algorithm for the Max Heap structure
     */
    public static void heapSort(int data[]) {
        int size = data.length;
        for (int i = size / (2 - 1); i >= 0; i--) {
            heapify(i, data, size);
        }//end of for loop
    
        for (int i = data.length - 1; i >= 0; i--) {
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
        
            size--;
            heapify(0, data, size);
        
        }
    }//end of heapSort
    
        private static int leftChild(int i){
            return 2*i + 1;
        }
        private static int rightChild(int i){
            return 2 * i + 2;
        
        }
        private static void heapify(int i, int[] data, int size){
            int largestElementIndex = i;
            
            int leftChildIndex = leftChild(i);
            if(leftChildIndex < size && data[leftChildIndex]> data[largestElementIndex]){
                largestElementIndex = leftChildIndex;
            }
            int rightChildIndex = rightChild(i);
            if(rightChildIndex < size && data[rightChildIndex] > data[largestElementIndex]){
                largestElementIndex = rightChildIndex;
            }
            if(largestElementIndex != i){
                int swap = data[i];
                data[i] = data[largestElementIndex];
                data[largestElementIndex] = swap;
                
                heapify(largestElementIndex,data,size);
            }
        }
    
    
    
    
}// end of class
