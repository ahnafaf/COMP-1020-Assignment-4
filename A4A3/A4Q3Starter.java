/**
 * AhsanAhnafA3Q3
 *
 * COMP 1020 SECTION A01
 * INSTRUCTOR    Josaine Kroll
 * ASSIGNMENT    Assignment 4, question 3
 * @author       Ahnaf Ahsan, 7966487
 * @version      29-3-23
 *
 * PURPOSE: Attempt at recursion to receive longest duplicate sequence from an array. 
 * Not recursive. Regular solution used.
 */
import java.util.Arrays;

public class A4Q3Starter {
  public static void main(String[] args) {
    int[] array;
    
    array = new int[] { 1, 5, 3, 7, 9, 1, 3, 7, 5, 3, 7, 1, 9 };
    verify(3, longestDuplicateSubsequence(array), Arrays.toString(array));

    array = new int[] { 3, 4, 1, 2, 4, 1, 2, 4, 3 };
    verify(4, longestDuplicateSubsequence(array), Arrays.toString(array));

    array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    verify(0, longestDuplicateSubsequence(array), Arrays.toString(array));

    array = new int[] { 1, 2, 3, 4, 5, 10, 6, 7, 8, 9, 10 };
    verify(1, longestDuplicateSubsequence(array), Arrays.toString(array));

    array = new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 1, 2, 3, 4, 5, 1, 2, 3, 4 };
    verify(5, longestDuplicateSubsequence(array), Arrays.toString(array));

    array = new int[] { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
    verify(11, longestDuplicateSubsequence(array), Arrays.toString(array));

    array = new int[] { 1, 1, 1, 1, 1 };
    verify(4, longestDuplicateSubsequence(array), Arrays.toString(array));
    
    System.out.println("\n*** End of program. ***");
  }
  
  public static void verify(int expected, int result, String action) {
    if (expected == result) {
      System.out.printf("Test succeeded: %s\n", action);
    } else {
      System.out.printf("Test FAILED (expected %s, got %s): %s\n", expected, result, action);
    }
  }

  // Do not change the functions above here
  // ========================================================================
  // Modify/rewrite the function below, adding any other necessary functions

  // What this does is that it goes enters the first value, and j is set as the second value.
  // Where value is initialized as 0. The inner most for loop transverses through the array
  // as it transverses through the array, it adds to value if z is equal to i+value.
  // where i+value represents the original sequence that is being compared to.

  public static int longestDuplicateSubsequence(int[] array) {
    int highest = 0;
    for (int i = 0; i < array.length; i++) {
      for (int j = i+1; j < array.length; j++) {
        int value = 0;
        for (int z = j; z < array.length; z++) {
          if(array[z]==array[i+value]) {
            value++;
            if (value>highest) {
              highest = value;
            }
          } else {
            value = 0;
          }
        }
        }
      }
      return highest;
    }
}