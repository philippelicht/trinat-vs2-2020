package ch.trinat.edu.threads01.u10;

public class StopThreadByInterrupt extends Thread {
	public StopThreadByInterrupt() {
		start();
	}

	public void run() {
		int i = 0;
		try {
			while (!isInterrupted()) {
				i++;
				System.out.println("Hallo Welt (" + i + ")");
				Thread.sleep(3000);
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException: " + e.getMessage());
		}
		System.out.println("thread terminating");
	}

	public static void main(String[] args) {
		StopThreadByInterrupt st = new StopThreadByInterrupt();
		try {
			Thread.sleep(9100);
		} catch (InterruptedException e) {
		}
		st.interrupt();
		try {
			st.join();
		} catch (InterruptedException e) {

		}
		System.out.println("main terminating");

	}
}
