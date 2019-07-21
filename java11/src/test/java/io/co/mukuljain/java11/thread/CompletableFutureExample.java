package io.co.mukuljain.java11.thread;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.security.cert.CollectionCertStoreParameters;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompletableFutureExample {

    private void randomSleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void enoughSleep() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("done");
        try {
            System.out.println(cf.get());;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> "hello")
                .thenApplyAsync(x -> x + " mukul")
                .thenAcceptAsync(System.out::println);

        cf.get();
    }

    @Test
    public void test3() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(()-> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        assertFalse(cf.isDone());
        enoughSleep();
        assertTrue(cf.isDone());
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        List<String> names = List.of("name1","name2","name3","name4","name5","name6");

        List<CompletableFuture<String>> namesCf = names.stream()
                .map(name -> CompletableFuture.supplyAsync(() -> {
                    randomSleep();
                    String value = name + ", " + Thread.currentThread().getName();
                    System.out.println(value);
                    enoughSleep();
                    return value;
                }, service))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(namesCf.toArray(new CompletableFuture[namesCf.size()]));

        CompletableFuture<List<String>> listCompletableFuture = allFutures.thenApplyAsync(v -> {
            return namesCf.stream().map(m -> {
                randomSleep();
                System.out.println("--- " + Thread.currentThread().getName());
                enoughSleep();
                return m.join();
            }).collect(Collectors.toList());
        }, service);

        CompletableFuture<Long> longCompletableFuture = listCompletableFuture.thenApplyAsync(v -> {
            return v.stream().count();
        });

        System.out.println(longCompletableFuture.get());
    }
}
