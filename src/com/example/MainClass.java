package com.example;

public class MainClass {

	public static void main(String[] args) {
		DataParallelism dp = new DataParallelism();
		long arraySize = 100_000;
		//dp.DoStuff(arraySize);
		dp.DoStuffParallel(arraySize);
		
		TaskParallelism tp = new TaskParallelism();
		tp.DoStuff();
	}

}
