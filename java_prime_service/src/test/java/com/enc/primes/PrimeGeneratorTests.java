package com.enc.primes;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PrimeGeneratorTests {
    private PrimeGeneratorInterface primeGenerator;
    private List<Long> expected_primes_less_than_10;
    private List<Long> expected_primes_less_than_equal_to_7;

    public PrimeGeneratorTests() {
        primeGenerator = new EratosthenesPrimeGeneratorStrategy();
        expected_primes_less_than_10 = Arrays.asList(1L, 2L, 3L, 5L, 7L);
        expected_primes_less_than_equal_to_7 = expected_primes_less_than_10;
    }

    @Test
    public void basicFunctionalTests() {
        assertThat(primeGenerator.primesLessThan(10))
                .as("primes less than 10")
                .isEqualTo(expected_primes_less_than_10);
        assertThat(primeGenerator.primesLessThan(7))
                .as("primes less than equal to 7")
                .isEqualTo(expected_primes_less_than_equal_to_7);
    }

    @Test
    public void numberTypesTests() {
        assertThat(primeGenerator.primesLessThan(10))
                .as("Int 10")
                .isEqualTo(expected_primes_less_than_10);
        assertThat(primeGenerator.primesLessThan(10L))
                .as("Long 10")
                .isEqualTo(expected_primes_less_than_10);
        assertThat(primeGenerator.primesLessThan(10.0))
                .as("Double 10.0")
                .isEqualTo(expected_primes_less_than_10);
        assertThat(primeGenerator.primesLessThan(10.5))
                .as("Double 10.5")
                .isEqualTo(expected_primes_less_than_10);
        assertThat(primeGenerator.primesLessThan(10f))
                .as("Float 10")
                .isEqualTo(expected_primes_less_than_10);
        assertThat(primeGenerator.primesLessThan(10.5f))
                .as("Float 10.5")
                .isEqualTo(expected_primes_less_than_10);
    }

    @Test
    public void edgeCaseFunctionalTests() {
        assertThat(primeGenerator.primesLessThan(1))
                .as("primes less than 1")
                .containsOnly(1L);
        assertThat(primeGenerator.primesLessThan(0))
                .as("primes less than equal to 0")
                .hasSize(0);
        assertThatThrownBy(() -> primeGenerator.primesLessThan(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("limit may not be null");
        assertThatThrownBy(() -> primeGenerator.primesLessThan(-3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("limit must be positive");
        assertThatThrownBy(() -> primeGenerator.primesLessThan(100000001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("limit exceeds reasonable limit for algorithm. Limit is 100000000");
    }
}
