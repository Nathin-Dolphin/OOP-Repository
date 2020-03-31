
/**
 * @author Nathin Wascher
 * @version 1.0
 * @since March 28, 2020
 */

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
    private int tempInt;

    public JSONReader() {
    }

    public JSONReader(String fileName) {
        readJSON(fileName);
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
            tempInt = 0;
            for (String s : jsonContents) {
                System.out.println("String " + tempInt + ":  " + s);
                tempInt++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FILE NOT FOUND");
        }
    }

    public String[] getString(String objectName) {
        if (jsonContents.contains(objectName)) {
            tempArray = new ArrayList<String>();
            System.out.println(0 + "\t" + jsonContents.get(0));
            for (int k = 1; k < jsonContents.size(); k++) {
                nextString = jsonContents.get(k);
                System.out.println(k + "\t" + nextString);

                if (nextString.equals(objectName)) {
                    k++;
                    nextString = jsonContents.get(k);
                    tempArray.add(nextString);
                } else if (nextString.equals("[") || nextString.equals("{")) {

                } else if (nextString.equals("]") || nextString.equals("}")) {

                }
            }
            stringList = new String[tempArray.size()];
            for (int j = 0; j < tempArray.size(); j++) {
                stringList[j] = tempArray.get(j);
            }
            if (stringList == null) {
                System.out.println("ERROR: FILE DOES NOT CONTAIN OBJECT");
            }
        } else {
            System.out.println("ERROR: FILE DOES NOT CONTAIN OBJECT");
        }
        System.out.println();
        return stringList;
    }
}