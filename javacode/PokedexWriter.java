
/**
 * Copyright (c) 2020 Nathin-Dolphin.
 * 
 * This file is under the MIT License.
 */

/**
 * @author Nathin Wascher
 * @version PokedexWriter v1.0.1
 * @since March 28, 2020
 */

public class PokedexWriter {

    public static void main(String[] args) {
        System.out.println("Excecuting Program (PokedexWriter)...");
        System.out.println("\n!!!WARNING: THIS PROGRAM CAN AND WILL OVERWRITE FILES!!!");

        if (args.length == 0) {
            System.out.println("\nERROR: NO ARGUMENT FOUND. USE \"-Help\"");
            help();

        } else if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("-WriteNewPokedex")) {
                System.out.println("Executing Command -WriteNewPokedex...");
                new PokedexWriter_Panel(args[1], false);

            } else if (args[0].equalsIgnoreCase("-WriteNewPokedexAssisted")) {
                System.out.println("Executing Command -WriteNewPokedexAssisted...");
                new PokedexWriter_Panel(args[1], true);

            } else if (args[0].equalsIgnoreCase("-AddToPokedex")) {
                System.out.println("Executing Command -AddToPokedex...");
                System.out.println("THIS FEATURE IS CURRENTLY NOT IMPLEMENTED");

            } else
                System.out.println("\nERROR: INVALID COMMAND. USE \"-Help\"");

        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("-Help")) {
                System.out.println("Executing Command -Help...");
                help();

            } else
                System.out.println("\nERROR: INVALID COMMAND AND/OR MISSING PARAMETERS. USE \"-Help\"");
        }
    }

    public static void help() {
        System.out.println("DO NOT USE THESE EXAMPLES!");
        System.out.println("\njava PokedexWriter -WriteNewPokedex [region name]");
        System.out.println("    ex. java PokedexWriter -WriteNewPokedex galarian");

        System.out.println("\njava PokedexWriter -WriteNewPokedexAssisted [region name]");
        System.out.println("    ex. java PokedexWriter -WriteNewPokedexAssisted galarian");

        System.out.println("\njava PokedexWriter -AddToPokedex [region name]");
        System.out.println("    ex. java PokedexWriter -AddToPokedex alolan");
    }
}