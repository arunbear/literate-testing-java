package org.example;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.example.LeapYearCalculator.isLeapYear;

class StackTests {

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_new_stack {

        @Test
        void is_empty() {
            assertThat((new Stack<>()).depth()).isZero();
        }

    }


    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class An_empty_stack {

        @Test
        void throws_when_queried_for_its_top_item() {
            assertThatExceptionOfType
                (IllegalStateException.class)
                .isThrownBy(() -> { (new Stack<>()).top(); });
        }

    }
}