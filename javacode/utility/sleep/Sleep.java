
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since April 14, 2020
 */

package utility.sleep;

public class Sleep {
    private long timeStart;
    private Sleeper sleep1, sleep2;

    public Sleep() {
        System.out.println("\nStarting Non-Threaded Sleep...");
        timeStart = System.nanoTime();

        sleep1 = new Sleeper(1);
        sleep2 = new Sleeper(2);
        sleep1.sleep();
        sleep2.sleep();

        System.out.println("Elapsed Time: " + (System.nanoTime() - timeStart) / 1000000 + " Milliseconds\n");
    }
}