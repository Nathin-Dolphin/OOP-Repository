
/**
 * @author Nathin Wascher
 * @version 0.2
 * @since March 25, 2020
 */

package utility;

public class CommandLineHelp {
    public static void noArgument() {
        System.out.println("\nERROR: no argument found. Use \"-Help\"");
    }

    public static void httpHelp() {
        System.out.println("\njava SwissArmyKnife -HttpRequest [URL]");
        System.out.println(
                "    ex. java SwissArmyKnife -HttpRequest https://thunderbird-index.azurewebsites.net/w0a6zk195e.json");
        System.out.println("\njava SwissArmyKnife -HttpRequestIndex [URL]");
        System.out.println(
                "    ex. java SwissArmyKnife -HttpRequestIndex https://thunderbird-index.azurewebsites.net/w0a6zk195d.json");
        System.out.println("\njava SwissArmyKnife -AutoRequest");
        System.out.println("    ex. java SwissArmyKnife -AutoRequest");
    }

    public static void pokedexWriterHelp() {
        System.out.println("\n!!!CAUTION IS ADVISED: MAY OVERWRITE IF [FILE NAME].JSON ALREADY EXISTS!!!");
        System.out.println("\njava PokedexWriter -WriteNewPokedex [region name]");
        System.out.println("    ex. java PokedexWriter -WriteNewPokedex galarian");
        System.out.println("\njava PokedexWriter -AddToPokedex [region name]");
        System.out.println("    ex. java PokedexWriter -AddToPokedex alolan");
    }
}