
/**
 * @author Nathin Wascher
 * @version 1.0.1
 * @since March 28, 2020
 */

public class PokedexSearch {
    public static void main(String[] args) {
        System.out.println("Executing Program (PokedexSearchEngine)...");
        new SimpleFrame("PokedexSearch", "Guess That Pokemon!", new PokedexSearch_Panel(), 800, 600);
    }
}