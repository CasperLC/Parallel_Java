package com.example;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.LongStream;

public class DataParallelism {
	
	ReentrantLock lock = new ReentrantLock();
	
	public void DoStuffParallel(long arraySize) {
		
		Instant starts = Instant.now();
		List<Long> primes = new ArrayList<>();
		
		LongStream.range(1, arraySize).parallel().forEach(number -> {
			Boolean isPrime = IsPrime(number);
			if(isPrime) {
				lock.lock();
				try {
					primes.add(number);
				} finally {
					lock.unlock();
				}
			}
		});
		Instant ends = Instant.now();
		
		System.out.println("Parallel: " + Duration.between(starts, ends).toMillis() / 1000f + " Seconds");
	}
	
	public Boolean IsPrime(long number)
    {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;


        long boundary = number / 2;

        for (long i = 3; i <= boundary; i += 2)
            if (number % i == 0)
                return false;

        return true;
    }

}
