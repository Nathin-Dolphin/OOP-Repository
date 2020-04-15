
/**
 * @author Nathin Wascher
 * @version 1.3.2
 * @since March 28, 2020
 */

// [?] What happens if get() detects a value that is equal to objectName [?]

package utility.json;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

public class JSONReader extends JSONParser {
    private Scanner fileScan;
    private ArrayList<String> jsonContents, tempArray;
    private String nextLine, nextString;
    private int intPos;
    private boolean oneJSON;

    //
    public JSONReader() {
        oneJSON = false;
    }

    // Creating a new JSONReader object with this constructor bases all other
    // methods on 'fileName'
    public JSONReader(String fileName) {
        readJSON(fileName);
        oneJSON = true;
    }

    public ArrayList<String> readJSON(String fileName) {
        if (!oneJSON) {
            try {
                if (fileName.endsWith(".json"))
                    fileScan = new Scanner(new File(fileName));
                else
                    fileScan = new Scanner(new File(fileName + ".json"));
                jsonContents = new ArrayList<String>();

                while (fileScan.hasNextLine()) {
                    nextLine = fileScan.nextLine();
                    jsonContents.add(nextLine);
                }
                jsonContents = parseJSON(jsonContents);

            } catch (FileNotFoundException e) {
                System.out.println("\nERROR: FILE NOT FOUND");
            }
            return jsonContents;

        } else
            return null;
    }

    // Outputs an array that INCLUDES brackets
    public ArrayList<String> getRaw(String objectName) {
        return jsonContents;
    }

    // [?] What happens if get() detects a value that is equal to objectName [?]
    public ArrayList<String> get(String objectName) {
        if (jsonContents == null || !oneJSON) {
            System.out.println("\nERROR: NO JSON FILE TO READ FROM");

        } else if (jsonContents.contains(objectName)) {
            tempArray = new ArrayList<String>();

            for (intPos = 1; intPos < jsonContents.size() - 1; intPos++) {
                nextString = jsonContents.get(intPos);

                if (nextString.equals(objectName)) {
                    nextString = jsonContents.get(++intPos);

                    // Checks if the value is an object or array
                    if (!startArrayCheck()) {
                        tempArray.add(nextString);
                    }
                }
            }
            // [?] If objectName was detected as a value instead of a name [?]
            if (tempArray == null) {
                System.out.println("\nERROR: FILE DOES NOT CONTAIN OBJECT");
            }
        } else {
            System.out.println("\nERROR: FILE DOES NOT CONTAIN OBJECT");
        }
        return tempArray;
    }

    // Checks if a new array or object is beginning
    private boolean startArrayCheck() {
        if (nextString.equals("[")) {
            endArrayCheck("]");
            return true;

        } else if (nextString.equals("{")) {
            endArrayCheck("}");
            return true;

        } else if (nextString.equals("[{")) {
            endArrayCheck("}]");
            return true;
        }
        return false;
    }

    // Checks if the current array or object is ending
    private void endArrayCheck(String endBracket) {
        nextString = jsonContents.get(++intPos);

        while (!nextString.equals(endBracket)) {
            if (nextString.equals("}{")) {
                nextString = jsonContents.get(++intPos);

            } else {
                tempArray.add(nextString);
                nextString = jsonContents.get(++intPos);
                startArrayCheck();
            }
        }
        nextString = jsonContents.get(++intPos);
    }
}