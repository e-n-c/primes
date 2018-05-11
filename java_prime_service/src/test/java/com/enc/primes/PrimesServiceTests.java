package com.enc.primes;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class PrimesServiceTests {

    private PrimesService primesService;
    private List<Long> expected_primes;
    private PrimeGeneratorInterface mockalgorithm;
    private Map<String, PrimeGeneratorInterface> primeGeneratorStrategies;

    public PrimesServiceTests() {
        expected_primes = new ArrayList<>();
        expected_primes.add(1L);

        primesService = new PrimesService();
        mockalgorithm = Mockito.mock(PrimeGeneratorInterface.class);
        primeGeneratorStrategies = Mockito.mock(primesService.primeGeneratorStrategies.getClass());
        primesService.primeGeneratorStrategies = primeGeneratorStrategies;
    }

    @Test
    public void validCall() {
        given(primeGeneratorStrategies.containsKey("validalgorithm")).willReturn(true);
        given(primeGeneratorStrategies.get("validalgorithm")).willReturn(mockalgorithm);
        given(mockalgorithm.primesLessThan(2)).willReturn(expected_primes);

        assertThat(primesService.primesLessThan(2, "validalgorithm")).isEqualTo(expected_primes);
    }

    @Test
    public void unknownAlgorithm() {
        given(primeGeneratorStrategies.containsKey("invalidalgorithm")).willReturn(false);
        given(primeGeneratorStrategies.keySet()).willReturn(Stream.of("validalgorithm", "othervalidalgorithm").collect(Collectors.toSet()));
        assertThatThrownBy(() -> primesService.primesLessThan(2L, "invalidalgorithm"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid algorithm supplied! options are: validalgorithm, othervalidalgorithm");
    }

    @Test
    public void invalidLimit() {
        given(primeGeneratorStrategies.containsKey("validalgorithm")).willReturn(true);
        given(primeGeneratorStrategies.get("validalgorithm")).willReturn(mockalgorithm);
        given(mockalgorithm.primesLessThan(-2)).willThrow(new IllegalArgumentException("some reason"));

        assertThatThrownBy(() -> primesService.primesLessThan(-2, "validalgorithm"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("some reason");
    }
}
