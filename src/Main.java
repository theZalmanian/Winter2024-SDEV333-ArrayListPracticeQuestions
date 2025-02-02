import java.util.Arrays;

/*
 URLify: Write a method to replace all spaces (" ") in
 a character buffer (array) with "%20". You may assume
 that the buffer has sufficient space at the end to hold
 the additional characters, and that you are given the "true"
 size of the buffer (which is different from the length or capacity
 of the buffer).

 Constraint: perform the operation in place (in the existing
 buffer), without creating a new or temporary buffer or string.

 Example:
            buffer                             size
            -------------------------------    ----
 Input      Hello, world!                      13
 Output     Hello,%20world!                    15

 Input      Dr Martin Luther King              21
 Output     Dr%20Martin%20Luther%20King        27


 Learning Objectives:
  * To practice an interview question.
  * To recognize the difference between size and length when working
    with a buffer (similar to how a resizable ArrayList, and/or a
    StringBuilder's internals would work).
  * To create and implement an algorithm to manipulate contents of an array.
  * To analyze the runtime performance of the algorithm.

 Source:
 Gayle Laakmann McDowell. 2016. Cracking the Coding Interview (6th ed.)
 CareerCup, Palo Alto, CA.
 */
public class Main {
    /**
     * The maximum capacity of the current buffer
     */
    public static final int BUFFER_CAPACITY = 100; //32768;

    public static void main(String[] args) {
        // set up empty buffer
        char[] buffer = new char[BUFFER_CAPACITY];

        // add some data to buffer
        String temp = "Dr Martin Luther King";
        for (int i = 0; i < temp.length(); i++) {
            buffer[i] = temp.charAt(i);
        }

        // track buffer length
        int size = temp.length();

        // check the "before" buffer and size via println
        System.out.println("Before");
        System.out.println(Arrays.toString(buffer));
        System.out.println("size: " + size);
        System.out.println();

        // call your method here
        size = urlify(buffer, size);

        // check the "after" buffer contents via println
        // check to see if the new buffer's size is correct
        System.out.println("After");
        System.out.println(Arrays.toString(buffer));
        System.out.println("size: " + size);
    }

    // write your method here

    private static int urlify(char[] buffer, int size) {
        // calculate new size prior to modification
        int newSize = size;

        // run through given buffer backwards
        for (int i = size; i > 0; i--) {
            // if the current character is a space
            if(buffer[i] == ' ') {
                // run through buffer backwards, up till index where space was found
                for(int j = size + 5; j >= i; j--) {
                    // get value from two indexes back and place in current index,
                    // thus creating two vacant spots next to the space
                    buffer[j] = buffer[j - 2];
                }

                // set replacement values in the now three vacant slots: '%' '2' '0'
                buffer[i] = '%';
                buffer[i + 1] = '2';
                buffer[i + 2] = '0';

                // update size counter as two more values are now tracked
                newSize += 2;
            }
        }

        // return updated size
        return newSize;
    }
}