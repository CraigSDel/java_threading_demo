package app.web.craigstroberg.readwrite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ReadModifyWriteExample {
    private static int counter;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Callable<Integer>> callables = new ArrayList<>();

        int maxCounter = 200;
        for (int i = 0; i < maxCounter; i++) {
            callables.add(() -> counter++);
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
        System.out.println("The counter we expected " + maxCounter);
        System.out.println("The counter actual counter value " + counter);
    }
}
