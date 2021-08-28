package de.noah.infoha.sortierung;

public class AsyncRun {

    private Thread thread;
    private Runnable runnable;

    public AsyncRun(Runnable runnable) {
        this.runnable = runnable;
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
