package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueSpecTests {

    @Nested
    class A_new_queue {

        @Test
        void is_empty() {
            assertThat((new Queue<>()).length()).isZero();
        }

    }


}