
/**
 * @author Nathin Wascher
 * @version 0.1.1 CAUTION: EXPERIMENTAL VERSION
 * @since March 28, 2020
 */

import utility.CommandLineHelp;

public class PokedexWriter {

    public static void main(String[] args) {
        System.out.println("Excecuting Program (NewJSON)...");
        System.out.println("\n!!!CAUTION IS ADVISED: MAY OVERWRITE IF [FILE NAME].JSON ALREADY EXISTS!!!");

        if (args.length == 0) {
            CommandLineHelp.noArgument();
            CommandLineHelp.pokedexWriterHelp();

        } else if (args[0].equalsIgnoreCase("-Help")) {
            CommandLineHelp.pokedexWriterHelp();

        } else if (args[0].equalsIgnoreCase("-WriteNewPokedex")) {
            if (args.length == 1)
                System.out.println("\nERROR: MISSING PARAMETERS");
            else
                new PokedexWriter_Frame(args[1]);

        } else if (args[0].equalsIgnoreCase("-AddToPokedex")) {
            System.out.println("THIS FEATURE IS A WORK IN PROGRESS (CURRENTLY UNUSABLE)");
            if (args.length == 1)
                System.out.println("\nERROR: MISSING PARAMETERS");

        } else
            System.out.println("\nERROR: INVALID COMMAND");

        System.out.println("\n...Terminating Program (NewJSON)");
    }
}