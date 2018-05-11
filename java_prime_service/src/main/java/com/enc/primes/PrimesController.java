package com.enc.primes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class PrimesController {

    @Autowired
    private PrimesService service;

    @RequestMapping("/lessthan/{limit}")
    public List<Long> primesLessThan(
            @PathVariable Long limit,
            @RequestParam(value="algorithm", defaultValue="Eratosthenes") String algorithm)
    {
        return service.primesLessThan(limit, algorithm);
    }
}
