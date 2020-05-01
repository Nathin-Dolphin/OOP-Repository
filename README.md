# OOP-Repository

Programs for Object-Oriented Programming.

Executable java file names (refered to as "MM" or Main Method):

	FaceDraw
	Mosaic
	PokemonSearch
	SwissArmyKnife
	ShapeCreator

	[!] Caution is advised when using the program(s) below [!]
	PokedexWriter


NOTES: 

	'...' is the path from your storage device to the location of the repository.
	To run a java file, the terminal's directory must be in the 'class' folder. ( ex. ...\javacode\class> )


## Create Class Files:
At the specified locations in the terminal, enter these commands in order,

	...> cd ...\OOP-Repository\javacode
	...\OOP-Repository\javacode> javac -d '...\OOP-Repository\javacode\class' *.java
	...\OOP-Repository\javacode> cd class

## Execute Program

	...\OOP-Repository\javacode\class> java "MM"

## PokemonSearch
	## Version 1.0 Features:
		(Implemented) - Search Pokemon by name, number, type and/or region
		(Implemented) - Implement a list for type and region
		(Implemented) - Print the results to the terminal (for now)
		(Implemented) - Create PokedexWriter to assist in producing the necessary json files

	## Version 2.0 Features:
		(In Progress) - Print more information about each pokemon
		Implement option to search by evolution
		Use threading while reading the many json files
		(Implemented) - Print the output to the frame instead of the terminal
		(Implemented) - Finish adding pokemon to the json files
		(Implemented) - Implement method to automatically find and download the needed json files via URLs

	## Version 3.0 Features:
		Put PokemonSearch into its own repository
		Implement option to search by weaknessess
		Allow PokedexWriter to modify already existing files and add size and weight
		Implement option to search by size and weight
