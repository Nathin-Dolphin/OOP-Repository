
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is part of the utility library and is under the MIT License.
 */

package utility.json;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Nathin Wascher
 * @version 1.0
 * @since March 28, 2020
 */
public class JSONWriter {
    private Scanner scan = new Scanner(System.in);
    private PrintWriter pw;

    private ArrayList<String> jsonContents, endBrackets;
    private String tabs, tempString;

    /**
     * 
     * @param fileName
     */
    public JSONWriter(String fileName) {
        jsonContents = new ArrayList<String>();
        endBrackets = new ArrayList<String>();
        tabs = "\t";
        jsonContents.add("{");

        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }
        pw = newFile(fileName);
    }

    /**
     * 
     * @param input
     */
    public void newObject(ArrayList<String> input) {
        if (jsonContents.get(jsonContents.size() - 1).endsWith("}")) {
            jsonContents.remove(jsonContents.size() - 1);
            jsonContents.add(tabs + "},{");
        } else
            jsonContents.add(tabs + "{");

        for (int i = 0; i < input.size(); i++) {
            tempString = tabs + "\t\"" + input.get(i) + "\": \"" + input.get(++i) + "\"";
            if (i < input.size() - 1)
                tempString = tempString + ",";

            jsonContents.add(tempString);
        }
        jsonContents.add(tabs + "}");
    }

    public void newObject(ArrayList<String> input, String name) {
        // TODO: Implement newObject(ArrayList<String>, String)
    }

    /**
     * 
     * @param name
     */
    public void newArray(String name) {
        if (jsonContents.get(jsonContents.size() - 1).endsWith("}")) {
            jsonContents.remove(jsonContents.size() - 1);
            jsonContents.add(tabs + "},");

        } else if (jsonContents.get(jsonContents.size() - 1).endsWith("]")) {
            jsonContents.remove(jsonContents.size() - 1);
            jsonContents.add(tabs + "],");
        }

        jsonContents.add(tabs + "\"" + name + "\": [");
        tabs = tabs + "\t";
        endBrackets.add("]");
    }

    /** */
    public void endArray() {
        tabs = "";
        for (int i = 0; i < endBrackets.size(); i++)
            tabs = tabs + "\t";

        jsonContents.add(tabs + endBrackets.get(endBrackets.size() - 1));
        endBrackets.remove(endBrackets.size() - 1);
    }

    /** */
    public void closeFile() {
        jsonContents.add("}");

        // TODO: Add loading bar here
        for (String s : jsonContents) {
            pw.println(s);
        }
        pw.close();
    }

    private PrintWriter newFile(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            return new PrintWriter(bw);
        } catch (IOException e) {
            System.out.println("ERROR: FAILED TO CREATE \"" + fileName + "\"");
            return null;
        }
    }
}