package ch.trinat.edu.threads02.u06;


import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecStart {
  
  public static void main(String[] args) {
    System.out.println("Start main: " + Instant.now());
    
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
    Runnable1 r1 = new Runnable1("Start nach einer Sekunde");
    Runnable1 r2 = new Runnable1("Start nach zwei Sekunden, dann alle 3 Sekunden. --> A");
    Runnable1 r3 = new Runnable1("Start nach drei Sekunden, dann alle 3 Sekunden. ---> B");
    
    executor.schedule(r1, 1, TimeUnit.SECONDS);
    executor.scheduleAtFixedRate(r2, 2, 3, TimeUnit.SECONDS);
    executor.scheduleWithFixedDelay(r3, 3, 3, TimeUnit.SECONDS);
  }
}
