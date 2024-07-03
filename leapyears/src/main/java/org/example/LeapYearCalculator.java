package org.example;

public class LeapYearCalculator {
    public static boolean isLeapYear(int i) {
        return i % 4 == 0 && i % 100 != 0;
    }
}
