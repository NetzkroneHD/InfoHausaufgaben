package de.noah.infoha.extraklassen;

public class AsyncRun {

    private final Thread thread;

    public AsyncRun(Runnable runnable) {
        thread = new Thread(runnable);
        start();
    }

    private void start() {
        thread.start();
    }

    public synchronized void stop() {
        thread.interrupt();
    }

    public static void runAsync(Runnable runnable) {
        new AsyncRun(runnable);
    }

}
