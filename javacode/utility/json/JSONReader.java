
/**
 * @author Nathin Wascher
 * @version 1.2
 * @since March 28, 2020
 */

// [!] get() can still output '}{' into the array [!]
// objectArrayCheck() can do with some cleaning up

package utility.json;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

public class JSONReader extends JSONParser {
    private Scanner fileScan;

    private ArrayList<String> jsonContents, tempArray;
    private String[] stringList;
    private String nextLine, nextString;
    private int tempInt, k;

    public JSONReader() {
    }

    public JSONReader(String fileName) {
        readJSON(fileName);
    }

    public ArrayList<String> readJSON(String fileName) {
        try {
            fileScan = new Scanner(new File(fileName + ".json"));
            jsonContents = new ArrayList<String>();

            while (fileScan.hasNextLine()) {
                nextLine = fileScan.nextLine();
                jsonContents.add(nextLine);
            }
            jsonContents = parseJSON(jsonContents);
            /*
             * {debug} tempInt = 0; for (String s : jsonContents) {
             * System.out.println("String " + tempInt + ": " + s); tempInt++; }
             */
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FILE NOT FOUND");
        }
        return jsonContents;
    }

    public ArrayList<String> get(String objectName) {
        if (jsonContents.contains(objectName)) {
            tempArray = new ArrayList<String>();

            for (k = 1; k < jsonContents.size(); k++) {
                nextString = jsonContents.get(k);

                if (nextString.equals(objectName)) {
                    nextString = jsonContents.get(++k);

                    // checks if the value is an object or array
                    if (!objectArrayCheck()) {
                        tempArray.add(nextString);
                    }

                    if (nextString.equals("]") || nextString.equals("}")) {
                        // [?]
                    }
                }
            }

            if (tempArray == null) {
                System.out.println("ERROR: FILE DOES NOT CONTAIN OBJECT");
            }
        } else {
            System.out.println("ERROR: FILE DOES NOT CONTAIN OBJECT");
        }
        System.out.println();
        return tempArray;
    }

    private boolean objectArrayCheck() {
        if (nextString.equals("[")) {
            nextString = jsonContents.get(++k);
            while (!nextString.equals("]")) {
                // {debug} System.out.println(nextString + " " + k);
                tempArray.add(nextString);
                nextString = jsonContents.get(++k);
                objectArrayCheck();

            }
            return true;
        } else if (nextString.equals("{")) {
            nextString = jsonContents.get(++k);
            while (!nextString.equals("}")) {
                // {debug} System.out.println(nextString + " " + k);
                tempArray.add(nextString);
                nextString = jsonContents.get(++k);
                objectArrayCheck();

            }
            return true;
        } else if (nextString.equals("[{")) {
            nextString = jsonContents.get(++k);
            while (!nextString.equals("}]")) {
                // {debug} System.out.println(nextString + " " + k);
                tempArray.add(nextString);
                nextString = jsonContents.get(++k);
                objectArrayCheck();

            }
            return true;

            // WORK IN PROGRESS
        } else if (nextString.equals("[]")) {
            nextString = jsonContents.get(++k);
            while (!nextString.equals("}{")) {
                // {debug} System.out.println(nextString + " " + k);
                tempArray.add(nextString);
                nextString = jsonContents.get(++k);
                objectArrayCheck();

            }
            return true;
        }
        return false;
    }
}