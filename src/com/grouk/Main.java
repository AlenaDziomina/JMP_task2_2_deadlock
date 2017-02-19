package com.grouk;

public class Main extends Thread {
    private final Object resource1;
    private final Object resource2;
    private int i;

    public Main(int i, Object resource1, Object resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("Thread " + i + "started");
        synchronized(resource1){
            System.out.println("Thread " + i + ": locked resource " + resource1);
            try{
                Thread.sleep(50);
            } catch (InterruptedException e) {}

            synchronized(resource2){
                System.out.println("Thread " + i + ": locked resource " + resource2);
            }
        }
    }

    public static void main(String args[]) {
        final Object resource1 = "resource1";
        final Object resource2 = "resource2";

        for (int i = 0; i < 10; i++) {
            Thread t1 = new Main(i, resource1, resource2);
            t1.start();
            Thread t2 = new Main(++i, resource2, resource1);
            t2.start();
        }
    }
}