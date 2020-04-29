
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.sleep.Sleep;
import utility.sleep.SleepFast;
import utility.sleep.SleepFastRunnable;

/**
 * @author Nathin Wascher
 * @version SwissArmyKnife v2.0.2
 * @since March 25, 2020
 */

public class SwissArmyKnife extends SwissArmyKnife_Outline {
    private static String autoRequestedURL = "https://thunderbird-index.azurewebsites.net/w0a6zk195d.json";

    public static void main(String[] args) {
        System.out.println("Executing Program (SwissArmyKnife)...");

        // TODO: Modify 'if' statements
        if (args.length == 0) {
            System.out.println("\nERROR: NO ARGUMENT FOUND. USE \"-Help\"");
            help();

        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("-Help")) {
                System.out.println("Executing Command -Help...");
                help();

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
                validateJSON(autoRequestedURL, false);

            } else if (args[0].equalsIgnoreCase("-JSONValidateIndexThreaded")) {
                System.out.println("Executing Command -JSONValidateIndexThreaded...{WIP}...");
                validateJSON(autoRequestedURL, true);

            } else if (args[0].equalsIgnoreCase("-AutoRequest")) {
                System.out.println("Executing Command -AutoRequest...");
                urlRequest(autoRequestedURL, false);

                if (validURL) {
                    int num = 1;

                    for (String s : urlReader.readURLIndex(autoRequestedURL)) {
                        System.out.println("String " + num + ":  " + s);
                        num++;
                    }
                }
            } else
                System.out.println("\nERROR: INVALID COMMAND");

        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("-HttpRequest")) {
                System.out.println("Executing Command -HttpRequest...");
                urlRequest(args[1], true);

            } else if (args[0].equalsIgnoreCase("-HttpRequestIndex")) {
                System.out.println("Executing Command -HttpRequestIndex...");
                urlIndexRequest(args[1]);

            } else
                System.out.println("\nERROR: INVALID COMMAND");

        } else
            System.out.println("\nERROR: INVALID COMMAND AND/OR MISSING PARAMETERS \"-Help\"");

        System.out.println("\n...Terminating Program (SwissArmyKnife)");
    }

    private static void help() {
        System.out.println("\njava SwissArmyKnife -HttpRequest [URL]");
        System.out.println(
                "   (ex.) java SwissArmyKnife -HttpRequest https://thunderbird-index.azurewebsites.net/w0a6zk195e.json");

        System.out.println("\njava SwissArmyKnife -HttpRequestIndex [URL]");
        System.out.println(
                "   (ex.) java SwissArmyKnife -HttpRequestIndex https://thunderbird-index.azurewebsites.net/w0a6zk195d.json");

        System.out.println("\njava SwissArmyKnife -JSONValidateIndex");
        System.out.println("\njava SwissArmyKnife -JSONValidateIndexThreaded");
        System.out.println("\njava SwissArmyKnife -Sleep");
        System.out.println("\njava SwissArmyKnife -SleepFast");
        System.out.println("\njava SwissArmyKnife -SleepFastImplementsRunnable");
        System.out.println("\njava SwissArmyKnife -AutoRequest");
    }
}