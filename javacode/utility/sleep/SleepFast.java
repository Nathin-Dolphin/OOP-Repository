
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since April 14, 2020
 */

package utility.sleep;

import java.util.ArrayList;

public class SleepFast {
    private ArrayList<Sleeper> sleepList;
    private long timeStart;
    private Sleeper sleep1, sleep2;

    public SleepFast() {
        sleepList = new ArrayList<Sleeper>();
        slowSleep();

        for (int i = 3; i < 15; i++) {
            sleepList.add(new Sleeper(i));
        }

        System.out.println("Starting Non-Threaded Sleep Array List...");
        startTimer();
        for (Sleeper s : sleepList) {
            s.sleep();
        }

        endTimer();
        System.out.println("Starting Threaded Sleep Array List...");
        startTimer();

        for (Sleeper s : sleepList) {
            s.start();
        }

        try {
            for (Sleeper s : sleepList) {
                s.join();
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

        sleep1 = new Sleeper(1);
        sleep2 = new Sleeper(2);
        sleep1.start();
        sleep2.start();

        try {
            sleep1.join();
            sleep2.join();
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