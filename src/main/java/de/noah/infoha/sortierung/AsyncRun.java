package de.noah.infoha.sortierung;

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

}
