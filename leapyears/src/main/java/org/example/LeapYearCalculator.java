package org.example;

public class LeapYearCalculator {
    public static boolean isLeapYear(int i) {
        if ((i % 4 == 0) && (i % 100 != 0)) {
            return true;
        }
        else return i % 400 == 0;
    }
}
