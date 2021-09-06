package app.web.craigstroberg.readwrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ReadModifyWriteCollectionExample {
    private static int counter;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        List<Callable<Integer>> callables = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
//        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());
//        List<Integer> integers = new CopyOnWriteArrayList<>();
        int maxCounter = 200;
        for (int i = 0; i < maxCounter; i++) {
            callables.add(() -> {
                integers.add(ThreadLocalRandom.current().nextInt());
                return 0;
            });
        }
        List<Future<Integer>> futures = executorService.invokeAll(callables);
        futures.forEach(integerFuture -> {
            try {
                integerFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("The expected size of the list " + maxCounter);
        System.out.println("The actual size of the list " + integers.size());
    }
}
