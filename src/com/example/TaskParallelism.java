package com.example;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class TaskParallelism {
	ForkJoinPool pool = ForkJoinPool.commonPool();
	ExecutorService exec = Executors.newWorkStealingPool(); 
	
	public void DoStuff() {
		List<Callable<String>> callables = new ArrayList<>();
		
		Callable<String> c1 = () -> {
			Thread.sleep(5000);
			System.out.println("First Method");
			return null;
		};
		Callable<String> c2 = () -> {
			Thread.sleep(10000);
			System.out.println("Second Method");
			return null;
		};
		
		callables.add(c1);
		callables.add(c2);
		
		Instant starts = Instant.now();
		
		try {
			exec.invokeAll(callables);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Instant ends = Instant.now();
		System.out.println("Parallel: " + Duration.between(starts, ends).toMillis() / 1000f + " Seconds");
		
		
		
	}
	
}


