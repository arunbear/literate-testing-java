package org.example;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;

class QueueSpecTests {

    @Nested
    @IndicativeSentencesGeneration(
        separator = " -> ",
        generator = DisplayNameGenerator.ReplaceUnderscores.class
    )
    class A_new_queue {

        @Test
        void is_empty() {
            // given
            var aNewQueue = new Queue<>(1);
            then(aNewQueue.length()).isZero();
        }

        @Test
        void preserves_positive_bounding_capacity() {
            // given
            final int capacity = 3;
            var aNewQueue = new Queue<>(capacity);
            then( aNewQueue.capacity() ).isEqualTo(capacity);
        }

        @Test
        void rejects_a_zero_bounding_capacity() {
            // given
            final int capacity = 0;
            thenExceptionOfType
                (IllegalArgumentException.class)
                    .isThrownBy(() -> new Queue<>(capacity));

        }

        @Test
        void rejects_a_negative_bounding_capacity() {
            // given
            final int capacity = -1;
            thenExceptionOfType
                (IllegalArgumentException.class)
                    .isThrownBy(() -> new Queue<>(capacity));

        }

    }

    @Nested
    class An_empty_queue {

        @Test
        void dequeues_an_empty_optional() {
            // given
            var anEmptyQueue = new Queue<>(1);

            then(anEmptyQueue.dequeue()).isEmpty();
        }

        @Test
        void becomes_non_empty_when_value_enqueued() {
            // given
            var queue = new Queue<String>(1);

            // when
            queue.enqueue("rock");
            then(queue.length()).isEqualTo(1);
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
                then(queue.length()).isGreaterThan(lengthBefore);
            }

            @Test
            void becomes_full_when_enqueued_up_to_capacity() {
              // given ->
                var queue = new Queue<String>(2);
                queue.enqueue("rock");
              // when ->
                queue.enqueue("paper");
              // then ->
                then(queue.length()).isEqualTo(queue.capacity());
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
                then(queue.length()).isEqualTo(1);
                then(queue.dequeue()).isNotEmpty().get().isEqualTo(rock);
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
                then(queue.length()).isLessThan(queue.capacity());
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
            then(queue.length()).isLessThan(lengthBefore);
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
            then(queue.dequeue()).isNotEmpty().get().isEqualTo(rock);
            then(queue.dequeue()).isNotEmpty().get().isEqualTo(paper);
        }

    }

}