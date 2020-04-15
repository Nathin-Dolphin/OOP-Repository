
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since April 14, 2020
 */

import utility.URLReader;

import java.util.ArrayList;

public class SwissArmyKnife_Outline {
    private final String REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    private URLReader urlReader;
    private ArrayList<ArrayList<String>> urlDoubleList;
    private ArrayList<String> urlList, tempList;

    private String tempString;
    private long startTime;
    private int validationNum, listNum, stringNum;
    private boolean validURL;

    public SwissArmyKnife_Outline(URLReader urlReader) {
        this.urlReader = urlReader;
        validURL = false;
    }

    public boolean getValidURL() {
        return validURL;
    }

    public void urlRequest(String url, boolean printContents) {
        if (urlReader.isValidURL(url)) {
            validURL = true;
            startTime = System.nanoTime();
            urlReader.readURL(url, printContents);

            if (printContents)
                System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " Milliseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

    public void urlIndexRequest(String url) {
        urlReader = new URLReader();

        if (urlReader.isValidURL(url)) {
            startTime = System.nanoTime();
            tempList = urlReader.readURL(url, true);
            System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " Milliseconds");

            startTime = System.nanoTime();
            urlReader.readURLIndex(url, true);
            System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " Milliseconds");

            urlList = urlReader.getURLList();
            System.out.println("Number of URLs:  " + urlList.size());
            System.out.println("Average Elapsed Time Per URL: "
                    + (System.nanoTime() - startTime) / 1000000 / urlList.size() + " Milliseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

    public void validateJSON(String url, boolean threaded) {
        urlDoubleList = new ArrayList<ArrayList<String>>();
        tempList = new ArrayList<String>();
        urlReader = new URLReader();

        if (urlReader.isValidURL(url)) {
            startTime = System.nanoTime();

            if (urlReader.isValidURL(url)) {
                urlDoubleList.add(urlReader.getParsedURLContents(url));
                urlList = urlReader.getURLList();

                if (!threaded) {
                    for (String s : urlList) {
                        tempList = urlReader.readURL(s, false);
                        urlDoubleList.add(urlReader.parseJSON(tempList));
                    }
                    validateContents(url);

                } else {
                    // WORK IN PROGRESS
                    for (String s : urlList) {
                        tempList = urlReader.readURL(s, false);
                        urlDoubleList.add(urlReader.parseJSON(tempList));
                    }
                    validateContents(url);
                }

            } else
                System.out.println("\nERROR: INVALID URL");
            System.out.println("\nElapsed Time: " + (System.nanoTime() - startTime) / 1000000 + " Milliseconds");

        } else
            System.out.println("\nERROR: INVALID URL");
    }

    private void validateContents(String url) {
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

    private void nameTest(int stringNum, String namePos) {
        tempString = tempList.get(stringNum);

        if (tempString.length() < 2) {
            System.out.println("\nERROR: " + namePos + " NAME TOO SHORT");
            errorDetected();

        } else if (tempString.length() > 16) {
            System.out.println("\nERROR: " + namePos + " NAME TOO LONG");
            errorDetected();
        }
    }

    private void errorDetected() {
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

    public void help() {
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

    // WORK IN PROGRESS
    private class SAK_Threading extends Thread {
        public SAK_Threading() {
        }

        public void run() {
        }
    }
}