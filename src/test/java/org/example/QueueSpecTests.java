package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class QueueSpecTests {

    @Nested
    class A_new_queue {

        @Test
        void is_empty() {
            assertThat((new Queue<>(1)).length()).isZero();
        }

        @Test
        void preserves_positive_bounding_capacity() {
            final int capacity = 3;
            assertThat(
                (new Queue<>(capacity)).capacity()
            ).isEqualTo(capacity);
        }

        @Test
        void rejects_a_zero_bounding_capacity() {
            assertThatExceptionOfType
                (IllegalArgumentException.class)
                    .isThrownBy(() -> { new Queue<>(0); });

        }

        @Test
        void rejects_a_negative_bounding_capacity() {
            assertThatExceptionOfType
                (IllegalArgumentException.class)
                    .isThrownBy(() -> { new Queue<>(-1); });

        }

    }


}