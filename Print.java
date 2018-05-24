package com.paypal.test.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Print {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        printNormally();
        printInFuture();
    }
    
    private static void printNormally() {
        System.out.println("printNormally\n----------------");
        for(int i=0; i<10; i++) {
            System.out.println(i);
        }
    }
    
    
    private static void printInFuture() throws InterruptedException, ExecutionException {
        System.out.println("printInFuture\n------------------");
        
        int numberOfThreads = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<Void>> futures = new ArrayList<Future<Void>>();
        for(int i=0; i<25; i++) {
            final int printNumber = i;
            Callable<Void> callable = new Callable<Void>() {

                public Void call() throws Exception {
                    System.out.println(printNumber);
                    return null;
                }
            };
            futures.add(threadPool.submit(callable));
        }
        for(Future<Void> future: futures) {
            future.get();
        }
     }
}
