
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since April 14, 2020
 */

package utility.sleep;

public class SleeperRunnable implements Runnable {
    private int sleepNum;

    public SleeperRunnable(int sleepNum) {
        this.sleepNum = sleepNum;
    }

    public void sleep() {
        sleep(1000);
    }

    public void sleep(int sleepLength) {
        System.out.println("Going to sleep for " + (sleepLength / 1000) + " Second(s)..." + sleepNum);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("ERROR 0157: THREAD INTERRUPTED");
        }

        System.out.println(sleepNum + "...Slept for " + (sleepLength / 1000) + " Second(s)");
    }

    public void run() {
        sleep();
    }
}