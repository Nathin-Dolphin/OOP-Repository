
/**
 * @author Nathin Wascher
 * @version 0.1
 * @since March 25, 2020
 */

package utility;

public class CommandLineHelp {
    public static void noArgument() {
        System.out.println("\nERROR: no argument found. Use \"-Help\"");
    }

    public static void httpHelp() {
        System.out.println("\njava [.java file] -HttpRequest [URL]");
        System.out.println(
                "    ex. java SwissArmyKnife -HttpRequest https://thunderbird-index.azurewebsites.net/w0a6zk195e.json");
        System.out.println("\njava [.java file] -HttpRequestIndex [URL]");
        System.out.println(
                "    ex. java SwissArmyKnife -HttpRequestIndex https://thunderbird-index.azurewebsites.net/w0a6zk195d.json");
        System.out.println("\njava [.java file] -AutoRequest");
        System.out.println("    ex. java SwissArmyKnife -AutoRequest");
    }
}