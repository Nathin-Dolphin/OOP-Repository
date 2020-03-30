
/**
 * @author Nathin Wascher
 * @version 0.2 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

package utility;

import utility.json.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

public class JSONReader extends JSONParser {
    private Scanner fileScan;

    private ArrayList<String> jsonContents;
    private String nextLine;
    private int i;

    public JSONReader() {
        readJSON("test");
    }

    public void readJSON(String fileName) {
        try {
            fileScan = new Scanner(new File(fileName + ".json"));
            jsonContents = new ArrayList<String>();
            while (fileScan.hasNextLine()) {
                nextLine = fileScan.nextLine();
                jsonContents.add(nextLine);
            }
            jsonContents = parseJSON(jsonContents);
            i = 1;
            for (String s : jsonContents) {
                System.out.println("String " + i + ":  " + s);
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FILE NOT FOUND");
        }
    }
}