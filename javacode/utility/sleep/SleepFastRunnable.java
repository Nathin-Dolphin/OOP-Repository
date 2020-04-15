
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since April 14, 2020
 */

package utility.sleep;

import java.util.ArrayList;

public class SleepFastRunnable {
    private ArrayList<SleeperRunnable> sleepList;
    private ArrayList<Thread> threadList;
    private long timeStart;
    private SleeperRunnable sleep1, sleep2;

    public SleepFastRunnable() {
        sleepList = new ArrayList<SleeperRunnable>();
        threadList = new ArrayList<Thread>();
        slowSleep();

        for (int i = 3; i < 15; i++) {
            sleepList.add(new SleeperRunnable(i));
        }

        System.out.println("Starting Non-Threaded Sleep Array List...");
        startTimer();
        for (SleeperRunnable s : sleepList) {
            s.sleep();
        }

        endTimer();
        System.out.println("Starting Threaded Sleep Array List...");
        startTimer();

        for (SleeperRunnable s : sleepList) {
            threadList.add(new Thread(s));
        }
        for (Thread t : threadList) {
            t.start();
        }

        try {
            for (Thread t : threadList) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR 4987: THREAD INTERRUPTED");
        }

        endTimer();
    }

    private void slowSleep() {
        new Sleep();
        System.out.println("Starting Threaded Sleep...");
        startTimer();

        sleep1 = new SleeperRunnable(1);
        sleep2 = new SleeperRunnable(2);
        Thread t1 = new Thread(sleep1);
        Thread t2 = new Thread(sleep2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("ERROR 2673: THREAD INTERRUPTED");
        }

        endTimer();
    }

    private void startTimer() {
        timeStart = System.nanoTime();
    }

    private void endTimer() {
        System.out.println("Elapsed Time: " + (System.nanoTime() - timeStart) / 1000000 + " Milliseconds\n");
    }

}