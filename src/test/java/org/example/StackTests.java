package org.example;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;

class StackTests {

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_new_stack {

        @Test
        void is_empty() {
            then((new Stack<>()).depth()).isZero();
        }

    }


    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class An_empty_stack {

        @Test
        void throws_when_queried_for_its_top_item() {
            thenExceptionOfType
                (IllegalStateException.class)
                .isThrownBy(() -> { (new Stack<>()).top(); });
        }

        @Test
        void throws_when_popped() {
            thenExceptionOfType
                (IllegalStateException.class)
                .isThrownBy(() -> { (new Stack<>()).pop(); });
        }

        @Test
        void acquires_depth_by_retaining_a_pushed_item_as_its_top() {
            Stack<String> stack = new Stack<>();
            final String item = "rock";

            stack.push(item);

            then(stack.depth()).isEqualTo(1);
            then(stack.top()).isEqualTo(item);
        }

    }

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class A_non_empty_stack {

        @Test
        void becomes_deeper_by_retaining_a_pushed_item_as_its_top() {
          // given ->
            Stack<String> stack = new Stack<>();
            stack.push("paper");

            final String item = "rock";

          // when ->
            stack.push(item);

          // then ->
            then(stack.depth()).isEqualTo(2);
            then(stack.top()).isEqualTo(item);
        }

        @Test
        void on_popping_reveals_tops_in_reverse_order_of_pushing() {
          // given ->
            Stack<String> stack = new Stack<>();
            var rock = "rock";
            var paper = "paper";
            var scissors = "scissors";

            stack.push(rock);
            stack.push(paper);
            stack.push(scissors);

          // when -> then
            stack.pop();
            then(stack.top()).isEqualTo(paper);

            stack.pop();
            then(stack.top()).isEqualTo(rock);
        }

    }
}