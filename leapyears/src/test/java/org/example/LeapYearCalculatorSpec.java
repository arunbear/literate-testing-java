package org.example;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.LeapYearCalculator.isLeapYear;

class LeapYearCalculatorSpec {

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_a_leap_year {

        @ParameterizedTest
        @ValueSource(ints = { 2004, 1984, 4 })
        void if_it_is_divisible_by_4_but_not_by_100(int year) {
            assertThat(isLeapYear(year)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(ints = { 2000, 1600, 400 })
        void if_it_is_divisible_by_400(int year) {
            assertThat(isLeapYear(year)).isTrue();
        }

    }

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_not_a_leap_year {

        @ParameterizedTest
        @ValueSource(ints = { 2022, 2019, 1999, 1 })
        void if_it_is_not_divisible_by_4(int year) {
            assertThat(isLeapYear(year)).isFalse();
        }

        @ParameterizedTest
        @ValueSource(ints = { 2100, 1900, 100 })
        void if_it_is_divisible_by_100_but_not_by_400(int year) {
            assertThat(isLeapYear(year)).isFalse();
        }


    }

}