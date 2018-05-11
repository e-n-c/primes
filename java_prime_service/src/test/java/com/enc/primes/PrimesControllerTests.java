package com.enc.primes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PrimesController.class)
public class PrimesControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private PrimesService primeService;

    @Test
    public void validApiCall() throws Exception {
        List<Long> expected_primes = Arrays.asList(1L, 2L);
        given(primeService.primesLessThan(any(), any())).willReturn(expected_primes);

        mvc.perform(get("/lessthan/2?algorithm=validalgorithm"))
                .andExpect(status().isOk())
                .andExpect(content().string("[1,2]"));
    }

    @Test
    public void validDefaultApiCall() throws Exception {
        List<Long> expected_primes = Arrays.asList(1L, 2L);
        given(primeService.primesLessThan(any(), eq("Eratosthenes"))).willReturn(expected_primes);

        mvc.perform(get("/lessthan/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("[1,2]"));
    }

    @Test
    public void invalidApiCall() throws Exception {
        given(primeService.primesLessThan(any(), any())).willThrow(new IllegalArgumentException("some reason message"));

        mvc.perform(get("/lessthan/2?algorithm=validalgorithm"))
                .andExpect(status().is(400))
                .andExpect(content().string("java.lang.IllegalArgumentException some reason message"));
    }

    @Test
    public void invalidApiCallParameter() throws Exception {
        //I could not figure out how to catch/handle this type of error

        mvc.perform(get("/lessthan/two?algorithm=validalgorithm"))
                .andExpect(status().is(400))
                .andExpect(content().string(""));
    }
}
