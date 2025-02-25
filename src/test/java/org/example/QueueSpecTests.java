package org.example;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class QueueSpecTests {

    @Nested
    @IndicativeSentencesGeneration(
        separator = " -> ",
        generator = DisplayNameGenerator.ReplaceUnderscores.class
    )
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

    @Nested
    @IndicativeSentencesGeneration(
        separator = " -> ",
        generator = DisplayNameGenerator.ReplaceUnderscores.class
    )
    class A_non_empty_queue {

        @Nested
        class that_is_not_full {

            @Test
            void becomes_longer_when_value_enqueued() {
              // given ->
                var queue = new Queue<String>(2);
                queue.enqueue("rock");
                var lengthBefore = queue.length();
              // when ->
                queue.enqueue("paper");
              // then ->
                assertThat(queue.length()).isGreaterThan(lengthBefore);
            }

            @Test
            void becomes_full_when_enqueued_up_to_capacity() {
              // given ->
                var queue = new Queue<String>(2);
                queue.enqueue("rock");
              // when ->
                queue.enqueue("paper");
              // then ->
                assertThat(queue.length()).isEqualTo(queue.capacity());
            }
        }

        @Nested
        class that_is_full {

            @Test
            void ignores_further_enqueued_values() {
              // given ->
                var queue = new Queue<String>(1);
                String rock = "rock";
                queue.enqueue(rock);
              // when ->
                queue.enqueue("paper");
              // then ->
                assertThat(queue.length()).isEqualTo(1);
                assertThat(queue.dequeue().get()).isEqualTo(rock);
            }

            @Test
            void becomes_non_full_when_dequeued() {
              // given ->
                var queue = new Queue<String>(2);
                queue.enqueue("rock");
                queue.enqueue("paper");
              // when ->
                queue.dequeue();
              // then ->
                assertThat(queue.length()).isLessThan(queue.capacity());
            }

        }

        @Test
        void becomes_shorter_when_dequeued() {
          // given ->
            var queue = new Queue<String>(1);

            queue.enqueue("rock");
            var lengthBefore = queue.length();
          // when ->
            queue.dequeue();
          // then ->
            assertThat(queue.length()).isLessThan(lengthBefore);
        }

        @Test
        void dequeues_values_in_order_enqueued() {
          // given ->
            var queue = new Queue<String>(2);
            String rock = "rock";
            String paper = "paper";

            queue.enqueue(rock);
            queue.enqueue(paper);
          // when / then ->
            assertThat(queue.dequeue().get()).isEqualTo(rock);
            assertThat(queue.dequeue().get()).isEqualTo(paper);
        }

    }

}