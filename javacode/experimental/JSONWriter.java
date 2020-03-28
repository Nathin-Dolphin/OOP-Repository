
/**
 * @author Nathin Wascher
 * @version 0.1
 * @since March 28, 2020
 * CAUTION: EXPERIMENTAL VERSION
 */

package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

public class JSONWriter {
    private Scanner scan = new Scanner(System.in);
    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter pw;

    private String fileName, input;

    public JSONWriter(String fileName) {
        this.fileName = fileName;
        pw = newFile(fileName);
    }

    public void closeFile() {
        pw.close();
    }

    private PrintWriter newFile(String fileName) {
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