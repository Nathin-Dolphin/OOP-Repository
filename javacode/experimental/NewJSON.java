
/**
 * @author Nathin Wascher
 * @version 0.3 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

//temporarly being used to debug JSONReader

import utility.JSONWriter;
import utility.json.JSONReader;

public class NewJSON {
    private static String[] stringList;

    public static void main(String[] args) {
        System.out.println("Excecuting Program (NewJSON)...");
        if (args.length == 2) {
            JSONReader read = new JSONReader(args[0]);
            stringList = read.getString(args[1]);
            for (String s : stringList) {
                System.out.println(s);
            }
        } else {
            JSONReader read = new JSONReader("test");
            stringList = read.getString("a1");
            for (String s : stringList) {
                System.out.println(s);
            }
        }
        System.out.println("\n...Terminating Program (NewJSON)");
    }
}