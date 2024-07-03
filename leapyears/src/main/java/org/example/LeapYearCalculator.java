package org.example;

public class LeapYearCalculator {
    public static boolean isLeapYear(int year) {
        if (year == 0) {
            throw new IllegalArgumentException();
        }
        if ((year % 4 == 0) && (year % 100 != 0)) {
            return true;
        }
        else return year % 400 == 0;
    }
}
