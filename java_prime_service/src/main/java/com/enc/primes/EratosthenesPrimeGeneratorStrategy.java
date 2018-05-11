package com.enc.primes;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("Eratosthenes")
public class EratosthenesPrimeGeneratorStrategy implements PrimeGeneratorInterface {

    private static final int MAX = 100000000;

    @Override
    public List<Long> primesLessThan(Number limit) throws IllegalArgumentException {
        if (limit == null) {
            throw new IllegalArgumentException(
                    "limit may not be null"
            );
        }
        if (limit.intValue() < 0L) {
            throw new IllegalArgumentException(
                    "limit must be positive"
            );
        }
        if (limit.intValue() > MAX) {
            throw new IllegalArgumentException(
                    String.format("limit exceeds reasonable limit for algorithm. Limit is %d", MAX)
            );
        }

        int limit_value = limit.intValue() + 1; //+1 so algorithm matches primes less than or equal to
        boolean[] array = new boolean[limit_value];

        for (int i = 2; i < limit_value; i++) {
            if (!array[i-1]) {
                int j = i + i;
                while (j < limit_value) {
                    array[j-1] = true;
                    j += i;
                }
            }
        }

        List<Long> primes = new ArrayList<>();
        for (int i = 1; i < limit_value; i++) {
            if (!array[i-1]) {
                primes.add(Long.valueOf(i));
            }
        }
        return primes;
    }
}
