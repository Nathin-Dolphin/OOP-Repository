
/**
 * @author Nathin Wascher
 * @version 1.2
 * @since March 25, 2020
 */

import utility.CommandLineHelp;
import utility.URLReader;

public class SwissArmyKnife {
    private static URLReader urlReader;
    private static long startTime, endTime;
    private static boolean validURL;

    private static String autoRequestedURL = "https://thunderbird-index.azurewebsites.net/w0a6zk195d.json";

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
                urlRequest(args[1], true);

        } else if (args[0].equalsIgnoreCase("-HttpRequestIndex")) {
            System.out.println("Executing Command -HttpRequestIndex...");
            if (args.length == 1)
                System.out.println("\nERROR: MISSING PARAMETERS");
            else {
                urlReader = new URLReader(args[1]);
                urlIndexRequest();
            }

        } else if (args[0].equalsIgnoreCase("-AutoRequest")) {
            System.out.println("Executing Command -AutoRequest...");
            urlRequest(autoRequestedURL, false);
            if (validURL) {
                int stringNum = 1;
                for (String s : urlReader.getParsedURLContents()) {
                    System.out.println("String " + stringNum + ":  " + s);
                    stringNum++;
                }
            }

        } else
            System.out.println("ERROR: INVALID COMMAND");

        System.out.println("\n...Terminating Program (SwissArmyKnife)");
    }

    private static void urlRequest(String url, boolean printContents) {
        if (urlReader.isValidURL(url)) {
            validURL = true;
            startTime = System.nanoTime();
            urlReader.readURL(url, printContents);
            endTime = System.nanoTime();
            if (printContents)
                System.out.println("\nElapsed Time: " + (endTime - startTime) / 1000 + " microseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

    private static void urlIndexRequest() {
        if (urlReader.isValidURL()) {
            startTime = System.nanoTime();
            urlReader.readURL(true);
            endTime = System.nanoTime();
            System.out.println("\nElapsed Time: " + (endTime - startTime) / 1000 + " microseconds");

            startTime = System.nanoTime();
            urlReader.readURLIndex(true);
            endTime = System.nanoTime();
            System.out.println("\nElapsed Time: " + (endTime - startTime) / 1000 + " microseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }
}