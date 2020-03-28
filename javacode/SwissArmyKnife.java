
/**
 * @author Nathin Wascher
 * @version 1.1
 * @since March 25, 2020
 */

import utility.CommandLineHelp;
import utility.URLReader;

public class SwissArmyKnife {
    private static URLReader urlReader;
    private static long startTime, endTime;

    private static String autoRequestedURL = "https://github.com/Nathin-Dolphin/OOP-Repository";

    public static void main(String[] args) {
        System.out.println("Executing Program (SwissArmyKnife)...");
        urlReader = new URLReader();

        if (args.length == 0) {
            CommandLineHelp.noArgument();
            CommandLineHelp.httpHelp();

        } else if (args[0].equalsIgnoreCase("-Help")) {
            System.out.println("Executing Command -Help...");
            CommandLineHelp.httpHelp();

        } else if (args[0].equalsIgnoreCase("-HttpRequest")) {
            System.out.println("Executing Command -HttpRequest...");
            if (args.length == 1)
                System.out.println("\nERROR: MISSING PARAMETERS");
            else
                urlRequest(args[1]);

        } else if (args[0].equalsIgnoreCase("-HttpRequestIndex")) {
            System.out.println("Executing Command -HttpRequestIndex...");
            if (args.length == 1)
                System.out.println("\nERROR: MISSING PARAMETERS");
            else {
                urlReader = new URLReader(args[1]);
                urlRequest(args[1]);
            }

        } else if (args[0].equalsIgnoreCase("-AutoRequest")) {
            System.out.println("Executing Command -AutoRequest...(WIP)...");
            urlRequest(autoRequestedURL);

        } else
            System.out.println("ERROR: INVALID COMMAND");

        System.out.println("\n...Terminating Program (SwissArmyKnife)");
    }

    private static void urlRequest(String url) {
        if (urlReader.isValidURL()) {
            startTime = System.nanoTime();
            urlReader.readURL(true);
            endTime = System.nanoTime();
            System.out.println("Elapsed Time: " + (endTime - startTime) / 1000 + " microseconds");
            
            startTime = System.nanoTime();
            urlReader.readURLIndex(true);
            endTime = System.nanoTime();
            System.out.println("Elapsed Time: " + (endTime - startTime) / 1000 + " microseconds");

        } else if (urlReader.isValidURL(url)) {
            startTime = System.nanoTime();
            urlReader.readURL(url, true);
            endTime = System.nanoTime();
            System.out.println("Elapsed Time: " + (endTime - startTime) / 1000 + " microseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

}