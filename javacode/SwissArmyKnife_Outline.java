
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

import utility.json.URLReader;

import java.util.ArrayList;

/**
 * @author Nathin Wascher
 * @version 1.0.3
 * @since April 14, 2020
 */

public class SwissArmyKnife_Outline {
    private final static String REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    private static ArrayList<ArrayList<String>> urlDoubleList;
    private static ArrayList<String> urlList, tempList;

    private static String tempString;
    private static long startTime;
    private static int validationNum, listNum, stringNum;

    public static URLReader urlReader;
    public static boolean validURL;

    public SwissArmyKnife_Outline() {
        validURL = false;
    }

    public static void urlRequest(String url, boolean printContents) {
        urlReader = new URLReader();
        if (urlReader.isValidURL(url)) {
            validURL = true;
            startTime = System.nanoTime();
            urlReader.readURL(url, printContents);

            if (printContents)
                System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " Milliseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

    public static void urlIndexRequest(String url) {
        urlReader = new URLReader();

        if (urlReader.isValidURL(url)) {
            startTime = System.nanoTime();
            tempList = urlReader.readURL(url, true);
            System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " Milliseconds");

            startTime = System.nanoTime();
            urlReader.readURLIndex(url, true);
            System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " Milliseconds");

            // [!] Currently broken [!]
            urlList = urlReader.getURLList();
            System.out.println("Number of URLs:  " + urlList.size());
            System.out.println("Average Elapsed Time Per URL: "
                    + (System.nanoTime() - startTime) / 1000000 / urlList.size() + " Milliseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

    public static void validateJSON(String url, boolean threaded) {
        urlDoubleList = new ArrayList<ArrayList<String>>();
        tempList = new ArrayList<String>();
        urlReader = new URLReader();

        if (urlReader.isValidURL(url)) {
            startTime = System.nanoTime();

            if (urlReader.isValidURL(url)) {
                urlDoubleList.add(urlReader.readURL(url));
                urlList = urlReader.getURLList();

                if (!threaded) {
                    for (String s : urlList) {
                        tempList = urlReader.readURL(s, false);
                        urlDoubleList.add(urlReader.parseJSON(tempList, false));
                    }
                    validateContents(url);

                } else {
                    // WORK IN PROGRESS
                    for (String s : urlList) {
                        tempList = urlReader.readURL(s, false);
                        urlDoubleList.add(urlReader.parseJSON(tempList, false));
                    }
                    validateContents(url);
                }

            } else
                System.out.println("\nERROR: INVALID URL");
            System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " Milliseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

    private static void validateContents(String url) {
        listNum = 1;

        while (listNum < urlDoubleList.size()) {
            tempList = urlDoubleList.get(listNum);
            validationNum = 0;

            for (stringNum = 0; stringNum < tempList.size(); stringNum++) {
                tempString = tempList.get(stringNum);

                if (tempString.equalsIgnoreCase("firstName")) {
                    validationNum = validationNum + 1;
                    nameTest(++stringNum, "FIRST");

                } else if (tempString.equalsIgnoreCase("lastName")) {
                    validationNum = validationNum + 2;
                    nameTest(++stringNum, "LAST");

                } else if (tempString.equalsIgnoreCase("preferedName")) {
                    nameTest(++stringNum, "PREFFERED");

                } else if (tempString.equalsIgnoreCase("email")) {
                    validationNum = validationNum + 4;
                    tempString = tempList.get(++stringNum);

                    if (!tempString.matches(REGEX)) {
                        System.out.println("\nERROR: INVALID EMAIL");
                        errorDetected();

                    }
                }
            }
            if (validationNum == 7) {

            } else if (validationNum != 1 & validationNum != 3 & validationNum != 5) {
                System.out.println(validationNum);
                System.out.println("\nERROR: MISSING FIRST NAME");
                errorDetected(); // 0 2 4 6

            } else if (validationNum != 2 & validationNum != 3 & validationNum != 6) {
                System.out.println("\nERROR: MISSING LAST NAME");
                errorDetected(); // 0 1 4 5

            } else if (validationNum != 4 & validationNum != 5 & validationNum != 6) {
                System.out.println("\nERROR: MISSING EMAIL");
                errorDetected(); // 0 1 2 3
            }
            listNum++;
        }
    }

    private static void nameTest(int stringNum, String namePos) {
        tempString = tempList.get(stringNum);

        if (tempString.length() < 2) {
            System.out.println("\nERROR: " + namePos + " NAME TOO SHORT");
            errorDetected();

        } else if (tempString.length() > 16) {
            System.out.println("\nERROR: " + namePos + " NAME TOO LONG");
            errorDetected();
        }
    }

    private static void errorDetected() {
        validationNum = -1;
        stringNum = tempList.size();
        tempList = urlDoubleList.get(0);

        for (int f = 1 + (listNum - 1) * 7; f < tempList.size(); f++) {
            tempString = tempList.get(f);

            if (tempString.equalsIgnoreCase("name")) {
                System.out.println("Author: " + tempList.get(++f));

            } else if (tempString.equalsIgnoreCase("email")) {
                System.out.println("Email: " + tempList.get(++f));

            } else if (tempString.equalsIgnoreCase("contactURL")) {
                System.out.println("Contact URL: " + tempList.get(++f));
                f = tempList.size();
            }
        }
    }
}