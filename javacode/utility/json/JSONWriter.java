
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

import java.util.Scanner;

/**
 * @author Nathin Wascher
 * @version 0.1.3 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

public class JSONWriter {
    private Scanner scan = new Scanner(System.in);
    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter pw;

    private String fileName, input;

    public JSONWriter(String fileName) {
        if (!fileName.endsWith(".json")) {
            fileName = fileName + ".json";
        }
        this.fileName = fileName;
        pw = newFile();
    }

    public void closeFile() {
        pw.close();
    }

    private PrintWriter newFile() {
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            return new PrintWriter(bw);
        } catch (IOException e) {
            System.out.println("ERROR: FAILED TO CREATE " + fileName);
            return null;
        }
    }
}