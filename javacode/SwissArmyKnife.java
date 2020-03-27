
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since March 25, 2020
 * 
 * Uses "HttpRequest.java" by {Copyright (C) 2019 Eric Pogue.} under the BSD-3-Clause License
 */

import utility.CommandLineHelp;

public class SwissArmyKnife {
    private static HttpRequest urlRequest;
    private static long startTime, endTime;

    private static String autoRequestedURL = "https://github.com/Nathin-Dolphin/OOP-Repository";

    public static void main(String[] args) {
        System.out.println("Executing Program (SwissArmyKnife)...");

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
            else
                urlRequest(args[1]);

        } else if (args[0].equalsIgnoreCase("-AutoRequest")) {
            System.out.println("Executing Command -AutoRequest...(WIP)...");
            urlRequest(autoRequestedURL);

        } else
            System.out.println("ERROR: INVALID COMMAND");

        System.out.println("\n...Terminating Program (SwissArmyKnife)");
    }

    private static void urlRequest(String url) {
        urlRequest = new HttpRequest(url);
        if (urlRequest.readURL()) {
            startTime = System.nanoTime();
            System.out.println(urlRequest);
            endTime = System.nanoTime();
            System.out.println("Elapsed Time: " + (endTime - startTime) + " nanoseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

}