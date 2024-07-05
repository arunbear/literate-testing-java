package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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

    @Nested
    class An_empty_queue {

        @Test
        void dequeues_an_empty_optional() {
            assertThat(
                (new Queue<String>(1)).dequeue() )
                    .isEmpty();

        }

        @Test
        void becomes_non_empty_when_value_enqueued() {
            var queue = new Queue<String>(1);
            queue.enqueue("rock");
            assertThat(queue.length()).isEqualTo(1);
        }

    }


}