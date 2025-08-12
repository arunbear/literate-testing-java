package org.example;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SimpleStackTest {

    @Nested
    class Given_an_empty_stack {

        @Test
        void it_has_depth_zero() {
            var stack = SimpleStack.empty();
            then(stack.depth()).isZero();
        }

        @Test
        void it_has_no_elements() {
            var stack = SimpleStack.empty();
            then(stack.isEmpty()).isTrue();
            then(stack.asList()).isEmpty();
        }

        @Test
        void pushing_makes_it_non_empty() {
            var stack = SimpleStack.empty();
            stack.push("item");
            then(stack.isEmpty()).isFalse();
        }

        @Test
        void popping_is_not_allowed() {
            var stack = SimpleStack.empty();
            thenThrownBy(stack::pop).isInstanceOf(IllegalStateException.class);
        }

        @Test
        void retrieving_the_top_element_is_not_allowed() {
            var stack = SimpleStack.empty();
            thenThrownBy(stack::top).isInstanceOf(IllegalStateException.class);
        }
    }

    @Nested
    class Given_any_stack {

        @Test
        void pushing_increases_depth() {
            var stack = SimpleStack.empty();
            stack.push("item");
            then(stack.depth()).isEqualTo(1);
        }

        @Test
        void pushing_places_new_item_on_top_and_preserves_existing_items() {
            var stack = SimpleStack.of("first");
            stack.push("second");
            then(stack.asList()).containsExactly("first", "second");
        }
    }

    @Nested
    class Given_a_non_empty_stack {

        @Test
        void popping_returns_the_top_element() {
            var stack = SimpleStack.of("A", "B");
            then(stack.pop()).isEqualTo("B");
        }

        @Test
        void popping_decreases_depth_by_one() {
            var stack = SimpleStack.of("A", "B");
            stack.pop();
            then(stack.depth()).isEqualTo(1);
        }

        @Test
        void the_popped_item_is_removed() {
            var stack = SimpleStack.of("A", "B");
            var popped = stack.pop();
            then(stack.asList()).doesNotContain(popped);
        }

        @Test
        void popping_removes_only_the_top_item() {
            var stack = SimpleStack.of("A", "B", "C");
            var popped = stack.pop();
            then(popped).isEqualTo("C");
            then(stack.asList()).containsExactly("A", "B");
        }

        @Test
        void items_are_popped_in_reverse_order() {
            var stack = SimpleStack.of("A", "B", "C");
            then(stack.pop()).isEqualTo("C");
            then(stack.pop()).isEqualTo("B");
            then(stack.pop()).isEqualTo("A");
        }

        @Test
        void its_top_item_is_the_last_pushed() {
            var stack = SimpleStack.of("A", "B");
            then(stack.top()).isEqualTo("B");
            then(stack.depth()).isEqualTo(2);
        }

        @Test
        void its_list_view_is_unmodifiable() {
            var stack = SimpleStack.of("A", "B");
            then(stack.asList()).isUnmodifiable();
        }

        @Test
        void its_list_view_contains_items_in_insertion_order() {
            var stack = SimpleStack.of("A", "B", "C");
            then(stack.asList()).containsExactly("A", "B", "C");
        }
    }

    static final class SimpleStack<T> {
        private final List<T> elements = new ArrayList<>();

        private SimpleStack() {}

        static <T> SimpleStack<T> empty() {
            return new SimpleStack<>();
        }

        @SafeVarargs
        static <T> SimpleStack<T> of(T... items) {
            var stack = new SimpleStack<T>();
            for (var item : items) {
                stack.push(item);
            }
            return stack;
        }

        void push(T item) {
            elements.add(item);
        }

        T pop() {
            if (elements.isEmpty()) throw new IllegalStateException("Cannot pop from empty stack");
            return elements.removeLast();
        }

        T top() {
            if (elements.isEmpty()) throw new IllegalStateException("Cannot access top of empty stack");
            return elements.getLast();
        }

        boolean isEmpty() {
            return elements.isEmpty();
        }

        int depth() {
            return elements.size();
        }

        List<T> asList() {
            return List.copyOf(elements);
        }
    }
}
