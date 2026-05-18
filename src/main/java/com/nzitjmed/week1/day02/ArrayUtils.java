package com.nzitjmed.week1.day02;
/**
 * Utility class for array operations.
 * All methods are pure functions unless otherwise noted.
 */


public class ArrayUtils {
    // Prevent instantiation — this is a true utility class
    private ArrayUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }
    /**
     * Returns a new array with elements in reverse order.
     * The input array is not modified.
     *
     * @param arr the array to reverse; must not be null
     * @return a new array containing the elements in reverse order
     * @throws IllegalArgumentException if arr is null
     */
    public static int[] reverse(final int[]arr){
        // TODO: Validation, create new array, fill in reverse order
        if(arr==null){
            throw new IllegalArgumentException("Array cannot be null.");
        }
        int[]result=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            result[i]=arr[arr.length-1-i];
        }
        return result;
    }

    /**
     * Finds the maximum value using recursion.
     *
     * @param arr the array; must not be null or empty
     * @return the maximum value
     * @throws IllegalArgumentException if arr is null or empty
     */

    public static  int findMax( final int[] arr){
        // TODO: Base case + recursive case
        // Hint: compare arr[0] with max of rest of array
        if(arr==null || arr.length==0){
            throw new IllegalArgumentException("Array cannot be null.or empty");
        }
        return findMaxRecursive(arr,0);
    }


    private static int findMaxRecursive(final int[] arr, final int index){
        if(index==arr.length -1){
            return arr[index]; // base case : last

        }
        int maxOfRest=findMaxRecursive(arr,index+1);
        return Math.max(arr[index],maxOfRest);

    }



    public static boolean isSorted(final int[] arr){
        if(arr==null ){
            throw new IllegalArgumentException("Array cannot be null.");
        }
        for(int i=0;i<arr.length -1;i++){
            if(arr[i]>arr[i+1]){
                return false;
            }
        }
        return true;
    }




    public static int[] mergeSorted(final int[] a, final int[] b){
        if(a==null || b==null )
            throw new IllegalArgumentException("Array cannot be null.");

        int[]result=new int[a.length+b.length];
        int i=0,j=0,k=0;
        while(i<a.length && j<b.length){
            if(a[i]<b[j]){
                result[k++]=a[i++];
            }else {
                result[k++]=b[j++];
            }

        }
        while(i<a.length){
            result[k++]=a[i++];
        }
        while(j<b.length){
            result[k++]=b[j++];

        }
        return result;
    }






}
