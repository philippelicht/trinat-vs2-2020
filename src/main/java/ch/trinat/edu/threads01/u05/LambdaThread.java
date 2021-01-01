package ch.trinat.edu.threads01.u05;

public class LambdaThread {

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("Lambda");
            for (int i = 0; i < 50; i++) {
                String name = Thread.currentThread().getName();
                System.out.println("Thread " + name + " hat Nummer " + i + " verarbeitet");
            }
        }, "Lambda").start();
    }

}
