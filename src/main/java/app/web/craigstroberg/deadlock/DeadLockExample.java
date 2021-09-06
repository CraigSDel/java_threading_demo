package app.web.craigstroberg.deadlock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DeadLockExample {
    private final Object left = new Object();
    private final Object right = new Object();
    private int counter;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final DeadLockExample deadLockExample = new DeadLockExample();
        CompletableFuture<Void> left = CompletableFuture.runAsync(() -> {
            try {
                deadLockExample.getLeft();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> right = CompletableFuture.runAsync(() -> {
            try {
                deadLockExample.getRight();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        left.get();
        right.get();
    }

    public void getLeft() throws InterruptedException {
        synchronized (left) {
            Thread.sleep(10);
            System.out.println("getLeft is waiting for right");
            synchronized (right) {
                System.out.println("getLeft has acquired right");
                ++counter;
            }
        }
    }

    public void getRight() throws InterruptedException {
        synchronized (right) {
            Thread.sleep(10);
            System.out.println("getRight is waiting for left");
            synchronized (left) {
                System.out.println("getRight has acquired left");
                ++counter;
            }
        }
    }
}
