
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file  is under the MIT License.
 */

//import utility.JSONWriter;
import utility.json.JSONReader;

/**
 * @author Nathin Wascher
 * @version 0.3 CAUTION: EXPERIMENTAL VERSION [!]
 * @since March 28, 2020
 */

public class NewJSON {
    private static String[] stringList;

    public static void main(String[] args) {
        System.out.println("Excecuting Program (NewJSON)...");
        JSONReader parse = new JSONReader();
        parse.parseJSON(null, false);
        System.out.println("\n...Terminating Program (NewJSON)");
    }
}