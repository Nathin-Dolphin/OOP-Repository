
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is part of the utility library and is under the MIT License.
 */

package utility.json;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Nathin Wascher
 * @version 1.1.2
 * @since March 28, 2020
 */
public class JSONWriter {
    private Scanner scan;
    private PrintWriter pw;

    private ArrayList<String> jsonContents, endBrackets;
    private String tabs, tempString;

    /**
     * 
     * @param fileName
     */
    public JSONWriter(JPanel panel, String fileName) {
        jsonContents = new ArrayList<String>();
        endBrackets = new ArrayList<String>();
        String warning = "The file " + fileName + " was found.\nDo you want to overwrite this file?";
        tabs = "\t";
        jsonContents.add("{");

        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }

        try {
            scan = new Scanner(new File(fileName));

            int output = JOptionPane.showInternalConfirmDialog(panel, warning, "WARNING", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (output == JOptionPane.NO_OPTION) {
                System.out.println("\n...Terminating Program From (JSONWriter)");
                // TODO: Have it close the frame/panel its attached too
                System.exit(0);
                scan.close();

            } else
                pw = newFile(fileName);

        } catch (FileNotFoundException e) {
            pw = newFile(fileName);
        }
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
        for (int f = 0; f < jsonContents.size() - 1; f++) {
            pw.println(jsonContents.get(f));
        }
        pw.print(jsonContents.get(jsonContents.size() - 1));
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