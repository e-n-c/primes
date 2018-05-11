package com.enc.primes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrimesService {

    @Autowired
    Map<String, PrimeGeneratorInterface> primeGeneratorStrategies = new HashMap<>();

    public List<Long> primesLessThan(
            Number limit,
            String algorithm)
    {
        if (!primeGeneratorStrategies.containsKey(algorithm)) {
            throw new IllegalArgumentException(
                    "Invalid algorithm supplied! options are: "
                            + String.join(", ", primeGeneratorStrategies.keySet()));
        }
        return primeGeneratorStrategies.get(algorithm).primesLessThan(limit);
    }
}
