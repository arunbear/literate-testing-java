package org.example;

/**
 * A Leap year calculator merely for demonstrating a literate style of unit testing.
 * The JDK already includes functionality for leap year checking.
 */
public class LeapYearCalculator {
    public static boolean isLeapYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException();
        }
        if ((year % 4 == 0) && (year % 100 != 0)) {
            return true;
        }
        else return year % 400 == 0;
    }
}
