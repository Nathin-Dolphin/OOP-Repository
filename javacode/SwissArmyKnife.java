
/**
 * @author Nathin Wascher
 * @version 2.0
 * @since March 25, 2020
 */

// [?] put some methods into another class [?]

import utility.URLReader;

import utility.sleep.Sleep;
import utility.sleep.SleepFast;
import utility.sleep.SleepFastRunnable;

public class SwissArmyKnife {
    // SwissArmyKnife shares the same urlReader object with it's support classes
    // resulting in shared information within URLReader
    private static URLReader urlReader;
    private static SwissArmyKnife_Outline sakO;
    private static String autoRequestedURL = "https://thunderbird-index.azurewebsites.net/w0a6zk195d.json";

    public static void main(String[] args) {
        System.out.println("Executing Program (SwissArmyKnife)...");
        urlReader = new URLReader();
        sakO = new SwissArmyKnife_Outline(urlReader);

        if (args.length == 0) {
            System.out.println("\nERROR: NO ARGUMENT FOUND. USE \"-Help\"");
            sakO.help();

        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("-Help")) {
                System.out.println("Executing Command -Help...");
                sakO.help();

            } else if (args[0].equalsIgnoreCase("-Sleep")) {
                System.out.println("Executing Command -Sleep...");
                new Sleep();

            } else if (args[0].equalsIgnoreCase("-SleepFast")) {
                System.out.println("Executing Command -SleepFast...");
                new SleepFast();

            } else if (args[0].equalsIgnoreCase("-SleepFastImplementsRunnable")) {
                System.out.println("Executing Command -SleepFastImplementsRunnable...");
                new SleepFastRunnable();

            } else if (args[0].equalsIgnoreCase("-JSONValidateIndex")) {
                System.out.println("Executing Command -JSONValidateIndex...");
                sakO.validateJSON(autoRequestedURL, false);

            } else if (args[0].equalsIgnoreCase("-JSONValidateIndexThreaded")) {
                System.out.println("Executing Command -JSONValidateIndexThreaded...{WIP}...");
                sakO.validateJSON(autoRequestedURL, true);

            } else if (args[0].equalsIgnoreCase("-AutoRequest")) {
                System.out.println("Executing Command -AutoRequest...");
                sakO.urlRequest(autoRequestedURL, false);

                if (sakO.getValidURL()) {
                    int num = 1;

                    for (String s : urlReader.getParsedURLContents(autoRequestedURL)) {
                        System.out.println("String " + num + ":  " + s);
                        num++;
                    }
                }
            } else
                System.out.println("\nERROR 4018: INVALID COMMAND");

        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("-HttpRequest")) {
                System.out.println("Executing Command -HttpRequest...");
                sakO.urlRequest(args[1], true);

            } else if (args[0].equalsIgnoreCase("-HttpRequestIndex")) {
                System.out.println("Executing Command -HttpRequestIndex...");
                sakO.urlIndexRequest(args[1]);

            } else
                System.out.println("\nERROR 1074: INVALID COMMAND");

        } else
            System.out.println("\nERROR: INVALID COMMAND AND/OR MISSING PARAMETERS");

        System.out.println("\n...Terminating Program (SwissArmyKnife)");
    }
}