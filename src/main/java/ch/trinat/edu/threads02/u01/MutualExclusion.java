package ch.trinat.edu.threads02.u01;

class MutexThread extends Thread {
	private Semaphore mutex;

	public MutexThread(Semaphore mutex) {
		this.mutex = mutex;
		start();
	}

	public void run() {
		while (true) {
			mutex.p();
			System.out.println("kritischen Abschnitt betreten: " + Thread.currentThread().getName());
			try {
				sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
			}
			System.out.println("kritischer Abschnitt wird " + "verlassen: " + Thread.currentThread().getName());
			mutex.v();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {

			}
		}
	}
}

public class MutualExclusion {
	public static void main(String[] args) {
		Semaphore mutex = new Semaphore(1);
		for (int i = 1; i <= 10; i++) {
			new MutexThread(mutex);
		}
	}
}
