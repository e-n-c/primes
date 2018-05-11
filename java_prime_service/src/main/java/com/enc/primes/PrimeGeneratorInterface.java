package com.enc.primes;

import java.util.List;

public interface PrimeGeneratorInterface {
    List<Long> primesLessThan(Number limit) throws IllegalArgumentException;
}
