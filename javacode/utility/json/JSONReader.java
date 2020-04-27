
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is part of the utility library and is under the MIT License.
 */

package utility.json;

import java.io.FileNotFoundException;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Locates, reads and stores the information from a {@code .json} file into an
 * {@code ArrayList<String>}. Also has a {@code get()} method to find specific
 * values.
 * 
 * <p>
 * <b>Planned Features:</b>
 * <p>
 * Implement a {@code toString()} method that calls the same from
 * {@code JSONParser}.
 * <p>
 * Add an option to the method {@code readJSON()} to print the contents of the
 * {@code .json} to the terminal.
 * <p>
 * Have 2 {@code ArrayList<String>}, where one includes brackets and the other
 * does not.
 * <p>
 * Implement threading.
 * 
 * <p>
 * <b>Known Issues:</b>
 * <p>
 * If {@code objectName} in method {@code get()} has the same name as a value,
 * it will add the next {@code String} to the {@code ArrayList<String>}.
 * 
 * @author Nathin Wascher
 * @version 1.4
 * @since March 28, 2020
 * 
 * @see JSONParser
 */

public class JSONReader extends JSONParser {
    private Scanner fileScan;
    private ArrayList<String> jsonContents, tempArray;
    private String nextLine, tempString;
    private int intPos;

    public JSONReader() {
    }

    /**
     * Looks for a file with the same name as {@code fileName}, then reads and
     * stores the data into an {@code ArrayList<String>}.
     * <p>
     * <b>[!] WARNING:</b>
     * <p>
     * If {@code includeBrackets} is {@code false}, {@code get()} may not function
     * as expected.
     * 
     * @param fileName        The name of the {@code .json} to read from
     * @param includeBrackets If the returning {@code ArrayList<String>} should
     *                        include the brackets from the {@code .json} file
     * @return An {@code ArrayList<String>} of the parsed {@code .json} data
     * @throws FileNotFoundException If {@code fileName} is not a {@code .json} or
     *                               can not be found
     * @see JSONParser
     */
    public ArrayList<String> readJSON(String fileName, boolean includeBrackets) throws FileNotFoundException {
        if (fileName.endsWith(".json"))
            fileScan = new Scanner(new File(fileName));
        else
            fileScan = new Scanner(new File(fileName + ".json"));

        jsonContents = new ArrayList<String>();
        while (fileScan.hasNextLine()) {
            nextLine = fileScan.nextLine();
            jsonContents.add(nextLine);
        }
        jsonContents = parseJSON(jsonContents, includeBrackets);
        return jsonContents;
    }

    /**
     * Overloaded Method
     * <p>
     * Looks for a file with the same name as {@code fileName}, then reads and
     * stores the data into an {@code ArrayList<String>}. Includes the brackets
     * found in a {@code .json}.
     * 
     * @param fileName The name of the {@code .json} to read from
     * @return An {@code ArrayList<String>} of the parsed {@code .json} data
     * @throws FileNotFoundException If {@code fileName} is not a {@code .json} or
     *                               can not be found
     * @see #readJSON(String, boolean)
     */
    public ArrayList<String> readJSON(String fileName) throws FileNotFoundException {
        return readJSON(fileName, true);
    }

    /**
     * Finds the value(s) associated with {@code ObjectName}.
     * 
     * @param objectName The name of an object or array in th {@code .json} file
     * @return An {@code ArrayList<String>} of values with the specified
     *         {@code objectName}
     * @throws NullPointerException If {@code readJSON} is not called before this
     *                              method, {@code jsonContents} equals null or the
     *                              {@code .json} file is completely empty
     * @see #readJSON(String, boolean)
     */
    public ArrayList<String> get(String objectName) throws NullPointerException {
        if (jsonContents == null) {
            throw new NullPointerException();

        } else {
            tempArray = new ArrayList<String>();
            for (intPos = 0; intPos < jsonContents.size(); intPos++) {
                tempString = jsonContents.get(intPos);

                if (tempString.equals(objectName)) {
                    tempString = jsonContents.get(++intPos);

                    if (!isNewArray()) {
                        tempArray.add(tempString);
                    }
                }
            }
        }
        return tempArray;
    }

    // Checks if a new json array or object is beginning
    private boolean isNewArray() {
        if (tempString.equals("[")) {
            endArrayCheck("]");
            return true;

        } else if (tempString.equals("{")) {
            endArrayCheck("}");
            return true;

        } else if (tempString.equals("[{")) {
            endArrayCheck("}]");
            return true;
        }
        return false;
    }

    // Checks if the current json array or object is ending
    private void endArrayCheck(String endBracket) {
        tempString = jsonContents.get(++intPos);

        while (!tempString.equals(endBracket)) {
            if (tempString.equals("}{")) {
                tempString = jsonContents.get(++intPos);

            } else {
                tempArray.add(tempString);
                tempString = jsonContents.get(++intPos);
                isNewArray();
            }
        }
        tempString = jsonContents.get(++intPos);
    }
}